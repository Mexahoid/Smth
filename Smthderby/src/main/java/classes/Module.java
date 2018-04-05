package classes;

import daointerfaces.ModuleInterface;

import java.util.List;

public class Module implements ModuleInterface {
    Connector c;

    public boolean CreateStudent(String name, int groupnum, int coursenum) {
        try{
            c.InsertStudent(name, groupnum, coursenum);
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean DeleteStudent(String name, int groupnum, int coursenum) {
        try{
            c.DeleteStudent(name, groupnum, coursenum);
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean CreateGroup(int groupnum, int coursenum) {
        try{
            c.InsertGroup(groupnum, coursenum);
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean DeleteGroup(int groupnum, int coursenum) {
        try{
            c.DeleteGroup(groupnum, coursenum);
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean CreateCourse(int coursenum) {
        try{
            c.InsertCourse(coursenum);
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean DeleteCourse(int coursenum) {
        try{
            c.DeleteCourse(coursenum);
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void SeeCourses() {
        try{
            List<Integer> crss = c.PrintCourses();
            for(Integer num : crss){
                System.out.println(num + " course.");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void SeeCourseGroups(int coursenum) {
        try{
            List<Integer> crss = c.PrintCourseGroups(coursenum);
            for(Integer num : crss){
                System.out.println(num + " group.");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void SeeGroupStudents(int groupnum, int coursenum) {
        try{
            List<String> crss = c.PrintGroupStudents(groupnum, coursenum);
            for(String num : crss){
                System.out.println(num);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public boolean StartSession(){
        try{
            c = new Connector();
            return true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean StopSession() {
        try{
            c.CloseDB();
            return true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
