package ua.sumdu.j2se.matusevich.tasks.controller;

import ua.sumdu.j2se.matusevich.tasks.model.Task;
import ua.sumdu.j2se.matusevich.tasks.model.TaskList;
import ua.sumdu.j2se.matusevich.tasks.view.AllView;
import ua.sumdu.j2se.matusevich.tasks.view.View;

public class AllViewController implements Controller {
    TaskList taskList = null;
    View view = new AllView();

    public AllViewController(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void go() {
        view.show();
        for (Task t: taskList
        ) {
            if (t.getTitle().isEmpty()) {
                System.out.println("Task list is empty!");
            } else {
                System.out.println(" -> " + t.getTitle());
            }
        }
    }
}
