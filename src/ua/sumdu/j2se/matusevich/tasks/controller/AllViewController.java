package ua.sumdu.j2se.matusevich.tasks.controller;

import ua.sumdu.j2se.matusevich.tasks.model.Task;
import ua.sumdu.j2se.matusevich.tasks.model.TaskList;
import ua.sumdu.j2se.matusevich.tasks.view.AllView;

public class AllViewController implements Controller {
    TaskList taskList = null;
    AllView view = new AllView();

    public AllViewController(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void go() {
        view.show();
        for (Task t: taskList
        ) {
            if (t.getTitle().isEmpty()) {
                view.show2();
            } else {
                System.out.println(" -> " + t.getTitle());
            }
        }
    }
}
