package ua.sumdu.j2se.matusevich.tasks;

import ua.sumdu.j2se.matusevich.tasks.controller.MainController;
import ua.sumdu.j2se.matusevich.tasks.model.ArrayTaskList;
import ua.sumdu.j2se.matusevich.tasks.model.CheckTasks;
import ua.sumdu.j2se.matusevich.tasks.model.Task;
import ua.sumdu.j2se.matusevich.tasks.model.TaskList;
import ua.sumdu.j2se.matusevich.tasks.controller.ClassMenuView;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class StartApplication {
    final static Logger logger = Logger.getLogger("StartApplication.class");
    private static TaskList t = new ArrayTaskList();
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static int choice2;
    private static boolean flag = false;

    private static void start() throws IOException, ParseException {
        System.out.println("1. Create new list. \n" +
                "2. Choose default list \n" +
                "3. Load your file");
        choice2 = Integer.parseInt(bufferedReader.readLine());
        check();
    }

    private static void check() throws IOException, ParseException {
        if (choice2 == 1) {
            flag = true;
        }

        if (choice2 == 2) {
            t.add(new Task("task1", new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSS" ).parse( "2019-04-12 09:00:00.000" ), new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSS" ).parse( "2019-04-20 09:00:00.000" ), 3));
            t.add(new Task("task2", new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSS" ).parse( "2019-04-13 09:00:00.000" ), new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSS" ).parse( "2019-04-21 09:00:00.000" ), 4));
            t.add(new Task("task3", new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSS" ).parse( "2019-04-14 09:00:00.000" ), new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSS" ).parse( "2019-04-22 09:00:00.000" ), 5));
            t.add(new Task("task4", new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSS" ).parse( "2019-04-15 09:00:00.000" ), new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSS" ).parse( "2019-04-23 09:00:00.000" ), 6));
            flag = true;
        }

        if (choice2 == 3) {
            System.out.println("Example of the load file. \n" +
                    "First line is the title. Example: taskList \n" +
                    "Second line is the start of reminder. Example: 2019-04-11 09:30:00.000 \n" +
                    "Third line is the end of reminder. Example: 2019-04-12 10:30:00.000 \n" +
                    "Fourth line is the interval of reminder. Example: 3 \n");
            System.out.println("Enter the path to the file.");
            String fileName = bufferedReader.readLine();

            FileInputStream fstream = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;

            String[] mas1 = new String[50];

            int k = 0;
            while ((strLine = br.readLine()) != null) {
                mas1[k] = strLine;
                k++;
            }

            String[] mas2 = new String[k];

            System.arraycopy(mas1, 0, mas2, 0, k);

            for (int i = 0; i < k; i = i + 4) {
                addTask(mas2[i], mas2[i+1],mas2[i+2], mas2[i+3]);
            }

            System.out.println("Successfully! \n");
            flag = true;
        }

        else {
            System.out.println("Enter correct variant! \n");
            start();
        }
    }

    private static void addTask(String title2, String start2, String end2, String interval2) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        Date start = format.parse(start2);
        Date end = format.parse(end2);
        int interval = Integer.parseInt(interval2);

        Task newTask = new Task(title2, start, end, interval);
        t.add(newTask);
    }

    public static void main(String[] args) throws IOException, ParseException {
        int choice;

        logger.info("Logger is work! Welcome to the application!");

        start();

        CheckTasks ct = new CheckTasks(t);
        ct.start();
        MainController controller = new MainController(t,ct);
        ClassMenuView view = new ClassMenuView();

        while(flag) {
                view.show();
                System.out.println("Enter your choice ");
                choice = Integer.parseInt(bufferedReader.readLine());

                if (choice > 0 && choice < 6) {
                    controller.setChoice(choice);
                    controller.go();
                }
                else System.out.println("Enter correct variant! \n");
        }
    }
}
