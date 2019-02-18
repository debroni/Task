package ua.sumdu.j2se.matusevich.tasks;

import ua.sumdu.j2se.matusevich.tasks.controller.Controller;
import ua.sumdu.j2se.matusevich.tasks.model.ArrayTaskList;
import ua.sumdu.j2se.matusevich.tasks.model.TaskList;
import ua.sumdu.j2se.matusevich.tasks.view.ClassMenuView;
import ua.sumdu.j2se.matusevich.tasks.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StartApplication {
    public static void main(String[] args) {
        TaskList t = new ArrayTaskList();
        Controller controller = new Controller(t);
        View view = new ClassMenuView();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int choice = 0;
        boolean flag = true;

        while(flag) {

            try {
                view.show();
                System.out.println("Enter your choice ");
                choice = Integer.parseInt(bufferedReader.readLine());
                if (choice > 0 && choice < 6) {
                    controller.handleAction(choice);
                }
                else System.out.println("Enter correct variant!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
