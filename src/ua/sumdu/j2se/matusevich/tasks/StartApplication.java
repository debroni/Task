package ua.sumdu.j2se.matusevich.tasks;

import ua.sumdu.j2se.matusevich.tasks.controller.Controller;
import ua.sumdu.j2se.matusevich.tasks.controller.MainController;
import ua.sumdu.j2se.matusevich.tasks.model.ArrayTaskList;
import ua.sumdu.j2se.matusevich.tasks.model.TaskList;
import ua.sumdu.j2se.matusevich.tasks.view.ClassMenuView;
import ua.sumdu.j2se.matusevich.tasks.view.View;

import java.io.*;

public class StartApplication {
    public static void main(String[] args) {
        TaskList t = new ArrayTaskList();
        File f = null;
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("taskslist.txt", true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        View view = new ClassMenuView();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int choice = 0;
        int choice2 = 0;
        boolean flag = false;
        String fileName = null;


            System.out.println("1. Create new list. \n" +
                    "2. Choose old list");
            try {
                choice2 = Integer.parseInt(bufferedReader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("Invalid data");
                System.exit(2);
            }

        if (choice2 == 1) {
            System.out.println("Enter file name.");
            try {
                fileName = bufferedReader.readLine() + ".txt";
                f = new File(fileName);
                fileWriter.write(fileName + "\n");
                fileWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (choice2 == 2) {

        }

        Controller controller = null;
        flag = true;

        while(flag) {

            try {
                view.show();
                System.out.println("Enter your choice ");
                choice = Integer.parseInt(bufferedReader.readLine());

                if (choice > 0 && choice < 6) {
                    controller = new MainController(t, f, choice);
                    controller.go();
                }
                else System.out.println("Enter correct variant!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
