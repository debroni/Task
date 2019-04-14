package ua.sumdu.j2se.matusevich.tasks.controller;

import ua.sumdu.j2se.matusevich.tasks.model.Task;
import ua.sumdu.j2se.matusevich.tasks.model.TaskList;

public class AllViewController implements Controller {
    TaskList taskList;

    public AllViewController(TaskList taskList) {
        this.taskList = taskList;
    }

    public void go() {
        System.out.println("All tasks:");
        for (Task t: taskList
        ) {
            if (t.getTitle().isEmpty()) {
                System.out.println("Task list is empty!");
            } else {
                System.out.println(" -> " + t);
            }
        }
    }
}
