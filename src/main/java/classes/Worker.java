package classes;

import daointerfaces.ModuleInterface;

import java.util.Scanner;

class Worker {

    private ModuleInterface mi;
    private Scanner s = new Scanner(System.in);

    void Start(){
        LoadLibrary();
        mi.StartSession();

        int action = -1;

        while(action != 2){
            System.out.println("Next action:");
            System.out.println("1. Course menu.");
            System.out.print("2. Exit.\n>> ");
            action = s.nextInt();

            switch (action){
                case 1:
                    CourseMenuHandler();
                    break;


                case 2:
                    mi.StopSession();
                    break;
            }
        }

    }

    private void CourseMenuHandler(){
        int num = 0;
        while(num != 5){
            System.out.println("==== Course menu ====");
            System.out.println("--------");
            System.out.println("Next action:");
            System.out.println("1. List courses.");
            System.out.println("2. Add new course.");
            System.out.println("3. Remove course.");
            System.out.println("4. Course group menu.");
            System.out.println("5. Return.");
            System.out.println("--------");
            System.out.print(">> ");
            num = s.nextInt();

            switch (num){
                case 1:
                    System.out.println("== Courses list ==");
                    mi.SeeCourses();
                    System.out.println("== ==");
                    break;

                case 2:
                    System.out.print("Enter new course number >> ");
                    num = s.nextInt();
                    if(mi.CreateCourse(num))
                        System.out.println("Successful.");
                    num = -1;
                    break;

                case 3:
                    System.out.print("Enter removing course number >> ");
                    num = s.nextInt();
                    if(mi.DeleteCourse(num))
                        System.out.println("Successful.");
                    num = -1;
                    break;

                case 4:
                    System.out.print("Select course >> ");
                    GroupMenuHandler(s.nextInt());
                    break;
                case 5:
                    break;
            }
            System.out.println("==== ====");
        }

    }

    private void GroupMenuHandler(int course){
        int num = 0;
        while(num != 4){
            System.out.println("==== Group menu for Course " + course + " ====");
            System.out.println("--------");
            System.out.println("Next action:");
            System.out.println("1. List groups.");
            System.out.println("2. Add new group.");
            System.out.println("3. Remove group.");
            System.out.println("4. Group student menu.");
            System.out.println("5. Return.");
            System.out.println("--------");
            System.out.print(">> ");
            num = s.nextInt();

            switch (num){
                case 1:
                    System.out.println("== Groups list ==");
                    mi.SeeCourseGroups(course);
                    System.out.println("== ==");
                    break;

                case 2:
                    System.out.print("Enter new group number >> ");
                    num = s.nextInt();
                    if(mi.CreateGroup(num, course))
                        System.out.println("Successful.");
                    num = -1;
                    break;

                case 3:
                    System.out.print("Enter removing group number >> ");
                    num = s.nextInt();
                    if(mi.DeleteGroup(num, course))
                        System.out.println("Successful.");
                    num = -1;
                    break;

                case 4:
                    System.out.print("Select group >> ");
                    StudentMenuHandler(course, s.nextInt());
                    break;
                case 5:
                    break;
            }
            System.out.println("==== ====");
        }
    }

    private void StudentMenuHandler(int course, int group){
        int num = 0;
        while(num != 4){
            System.out.println("==== Student menu for Group " + group + " of Course " + course + " ====");
            System.out.println("--------");
            System.out.println("Next action:");
            System.out.println("1. List students.");
            System.out.println("2. Add new student.");
            System.out.println("3. Remove student.");
            System.out.println("4. Return.");
            System.out.println("--------");
            System.out.print(">> ");
            num = s.nextInt();

            switch (num){
                case 1:
                    System.out.println("== Student list ==");
                    mi.SeeGroupStudents(group, course);
                    System.out.println("== ==");
                    break;

                case 2:
                    System.out.print("Enter new student name >> ");
                    String name = s.next();
                    if(mi.CreateStudent(name, group, course))
                        System.out.println("Successful.");
                    num = -1;
                    break;

                case 3:
                    System.out.print("Enter removing student name >> ");
                    name = s.next();
                    if(mi.DeleteStudent(name, group, course))
                        System.out.println("Successful.");
                    num = -1;
                    break;

                case 4:
                    break;
            }
            System.out.println("==== ====");
        }
    }

    private void LoadLibrary(){
        Loader ld = new Loader();
        try
        {
            mi = ld.loadLibrary();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
