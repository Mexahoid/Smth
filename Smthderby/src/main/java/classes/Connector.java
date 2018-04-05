package classes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;


public class Connector {
    private Connection conn;
    private Statement statmt;
    private ResultSet resSet;

    Connector() throws ClassNotFoundException, SQLException
    {
        conn = null;
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        conn = DriverManager.getConnection("jdbc:derby:" + System.getProperty("user.dir") +"\\db\\derbydatabase");

        //Class.forName("org.hsqldb.jdbc.JDBCDriver");
        //conn = DriverManager.getConnection("jdbc:hsqldb:file:" + System.getProperty("user.dir") +"\\db\\hsqldb");
        statmt = conn.createStatement();
        //System.out.println("База Подключена!");
    }

    void InsertCourse(int nbr) throws SQLException
    {
        statmt.execute("INSERT INTO COURSE (NUMBER) VALUES (" + nbr + ")");
    }

    void DeleteCourse(int nbr) throws SQLException
    {
        statmt.execute("DELETE FROM COURSE WHERE NUMBER = " + nbr);
    }

    public void InsertGroup(int nbr, int course) throws SQLException{
        statmt.execute("INSERT INTO GROUPS (NUMBER, COURSE_ID) " +
                "VALUES (" + nbr + ", (SELECT COURSE.ID FROM COURSE WHERE COURSE.NUMBER = " + course + "))");
    }

    public void DeleteGroup(int nbr, int course) throws SQLException
    {
        statmt.execute("DELETE FROM GROUPS WHERE NUMBER = " + nbr +
                " AND COURSE_ID = (SELECT ID FROM COURSE WHERE NUMBER = " + course + ")");
    }

    public void InsertStudent(String name, int grp, int course) throws SQLException
    {
        statmt.execute("INSERT INTO STUDENT (NAME, GROUP_ID) " +
                "VALUES (\'" + name + "\', (SELECT GROUPS.ID FROM GROUPS WHERE GROUPS.COURSE_ID = " +
                "(SELECT COURSE.ID FROM COURSE WHERE COURSE.NUMBER = " + course + ") AND GROUPS.NUMBER = " + grp + "))");
    }

    public void DeleteStudent(String name, int grp, int course) throws SQLException
    {
        statmt.execute("DELETE FROM STUDENT " +
                "WHERE NAME = " + name + " AND GROUP_ID = (SELECT ID FROM " +
                "(SELECT * FROM GROUPS WHERE COURSE_ID =" +
                " (SELECT COURSE.ID FROM COURSE WHERE NUMBER = " + course + ")" +
                " WHERE NUMBER = " + grp + "))");
    }

    public List<Integer> PrintCourses() throws SQLException
    {
        resSet = statmt.executeQuery("SELECT * FROM COURSE");
        List<Integer> l = new LinkedList<Integer>();
        while(resSet.next())
        {
            int num = resSet.getInt("NUMBER");
            l.add(num);
        }
        return l;
    }

    public List<Integer> PrintCourseGroups(int coursenum) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT * FROM GROUPS WHERE COURSE_ID = (SELECT ID FROM COURSE WHERE COURSE.NUMBER = " + coursenum + ")");
        List<Integer> l = new LinkedList<Integer>();
        while(resSet.next())
        {
            int num = resSet.getInt("NUMBER");
            l.add(num);
        }
        return l;
    }

    public List<String> PrintGroupStudents(int groupnum, int coursenum) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT * FROM STUDENT WHERE GROUP_ID = (SELECT ID FROM GROUPS WHERE " +
                "GROUPS.NUMBER = " + groupnum +" AND GROUPS.COURSE_ID = (SELECT ID FROM COURSE WHERE COURSE.NUMBER = " + coursenum + "))");
        List<String> l = new LinkedList<String>();
        while(resSet.next())
        {
            String name = resSet.getString("NAME");
            l.add(name);
        }
        return l;
    }

    // --------Закрытие--------
    public void CloseDB() throws ClassNotFoundException, SQLException
    {
        conn.close();
        statmt.close();
        resSet.close();

        System.out.println("Database stopped.");
    }

}