package ua.sumdu.j2se.matusevich.tasks.controller;

import ua.sumdu.j2se.matusevich.tasks.model.TaskList;
import ua.sumdu.j2se.matusevich.tasks.view.*;

public class Controller {
    private TaskList taskList;
    public Controller(TaskList t) {
        this.taskList = t;
    }

    public void handleAction(int action) {
        View view = null;

        switch (action) {
            case 1: view = new AddTaskView(taskList);
                break;
            case 2: view = new EditTaskView(taskList);
                break;
            case 3: view = new RemoveTaskView(taskList);
                break;
            case 4: view = new AllView(taskList);
                break;
            case 5: System.exit(1);
        }
        view.show();
    }
}
