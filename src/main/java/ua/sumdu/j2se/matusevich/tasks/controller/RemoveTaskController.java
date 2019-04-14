package ua.sumdu.j2se.matusevich.tasks.controller;

import ua.sumdu.j2se.matusevich.tasks.model.Task;
import ua.sumdu.j2se.matusevich.tasks.model.TaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RemoveTaskController implements Controller {
    private TaskList taskList;
    private BufferedReader bufferedReader = new BufferedReader (new InputStreamReader(System.in));

    RemoveTaskController(TaskList taskList) {
        this.taskList = taskList;
    }

    public void go() {
        System.out.println("Enter title to delete:");
        String search = null;
        try {
            search = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int k = 0;
        for (Task t: taskList) {
            if (t.getTitle().equals(search)) {
                Task newTask = new Task(t.getTitle(), t.getStartTime(), t.getEndTime(), t.getRepeatInterval());
                taskList.remove(newTask);
                System.out.println("Remove successful!");
                k++;
            }
        }

        if (k == 0) {
            System.out.println("Not found!");
        }
    }
}
