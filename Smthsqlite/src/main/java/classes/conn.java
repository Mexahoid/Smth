package classes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class conn {
    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    // --------ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ--------
    public static void Conn() throws ClassNotFoundException, SQLException
    {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:D:/Projects/Smth/db/sqlitedatabase.s3db");

        System.out.println("База Подключена!");
    }

    public static void InsertCourse(int nbr) throws SQLException
    {
        statmt.execute("INSERT INTO main.COURSE ('NUMBER') VALUES (nbr)");
    }

    public static void InsertGroup(int nbr, int course) throws SQLException{
        statmt.execute("INSERT INTO main.GROUP ('NUMBER', 'COURSE_ID') " +
                "VALUES (nbr, (SELECT 'ID' FROM 'COURSE' WHERE 'NUMBER' = course))");
    }

    public static void InsertStudent(String name, int grp, int course) throws SQLException{
        statmt.execute("INSERT INTO main.STUDENT ('NAME', 'GROUP_ID') " +
                "VALUES (name, " +
                "(SELECT 'ID' FROM " +
                    "(SELECT * FROM main.GROUP WHERE 'COURSE_ID' =" +
                        " (SELECT 'ID' FROM main.COURSE WHERE 'NUMBER' = course)" +
                ") WHERE 'NUMBER' = grp))");
    }

    public static void PrintCourses()  throws ClassNotFoundException, SQLException
    {
        resSet = statmt.executeQuery("SELECT * FROM main.COURSE");

        while(resSet.next())
        {
            int num = resSet.getInt("")
            String  name = resSet.getString("name");
            String  phone = resSet.getString("phone");
            System.out.println( "ID = " + id );
            System.out.println( "name = " + name );
            System.out.println( "phone = " + phone );
            System.out.println();
        }
    }


    // -------- Вывод таблицы--------
    public static void ReadDB() throws ClassNotFoundException, SQLException
    {
        resSet = statmt.executeQuery("SELECT * FROM users");

        while(resSet.next())
        {
            int id = resSet.getInt("id");
            String  name = resSet.getString("name");
            String  phone = resSet.getString("phone");
            System.out.println( "ID = " + id );
            System.out.println( "name = " + name );
            System.out.println( "phone = " + phone );
            System.out.println();
        }

        System.out.println("Таблица выведена");
    }

    // --------Закрытие--------
    public static void CloseDB() throws ClassNotFoundException, SQLException
    {
        conn.close();
        statmt.close();
        resSet.close();

        System.out.println("Соединения закрыты");
    }

}