package ua.sumdu.j2se.matusevich.tasks;

import ua.sumdu.j2se.matusevich.tasks.controller.Controller;
import ua.sumdu.j2se.matusevich.tasks.model.ArrayTaskList;
import ua.sumdu.j2se.matusevich.tasks.model.TaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StartApplication {
    public static void main(String[] args) {
        TaskList t = new ArrayTaskList();
        Controller controller = new Controller(t);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int choice = 0;
        controller.handleAction(choice);
        boolean flag = true;

        while(flag) {

            try {
                choice = Integer.parseInt(bufferedReader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }

            controller.handleAction(choice);
        }
    }
}
