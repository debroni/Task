package ua.sumdu.j2se.matusevich.tasks;

import ua.sumdu.j2se.matusevich.tasks.controller.Controller;
import ua.sumdu.j2se.matusevich.tasks.controller.MainController;
import ua.sumdu.j2se.matusevich.tasks.model.ArrayTaskList;
import ua.sumdu.j2se.matusevich.tasks.model.TaskList;
import ua.sumdu.j2se.matusevich.tasks.view.ClassMenuView;
import ua.sumdu.j2se.matusevich.tasks.view.View;

import java.io.*;

public class StartApplication {
    public static void main(String[] args) throws IOException {
        TaskList t = new ArrayTaskList();
        File f = null;
        FileWriter fileWriter = null;
        fileWriter = new FileWriter("taskslist.txt", true);

        View view = new ClassMenuView();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int choice = 0;
        int choice2 = 0;
        boolean flag = false;
        String fileName = null;
        flag = true;


        System.out.println("1. Create new list. \n" +
                    "2. Choose old list");
        choice2 = Integer.parseInt(bufferedReader.readLine());

        if (choice2 == 1) {
            System.out.println("Enter file name.");
                fileName = bufferedReader.readLine() + ".txt";
                f = new File(fileName);
                fileWriter.write(fileName + "\n");
                fileWriter.close();
        }

        if (choice2 == 2) {
            BufferedReader in = new BufferedReader(new FileReader("taskslist.txt"));
            String line = in.readLine();
            while(line != null)
            {
                System.out.println(line);
                line = in.readLine();
            }
            in.close();

            flag = false;
        }

        Controller controller = null;

        while(flag) {
                view.show();
                System.out.println("Enter your choice ");
                choice = Integer.parseInt(bufferedReader.readLine());

                if (choice > 0 && choice < 6) {
                    controller = new MainController(t, f, choice);
                    controller.go();
                }
                else System.out.println("Enter correct variant!");
        }
    }
}
