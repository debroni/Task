package ua.sumdu.j2se.matusevich.tasks.controller;

import ua.sumdu.j2se.matusevich.tasks.model.CheckTasks;
import ua.sumdu.j2se.matusevich.tasks.model.TaskList;

import static ua.sumdu.j2se.matusevich.tasks.model.TaskIO.writeText;

public class MainController implements Controller {
    private TaskList taskList;
    private CheckTasks ct;

    private int choice;


    public MainController(TaskList t, CheckTasks ct) {
        this.taskList = t;
        this.ct = ct;
    }


    public void setChoice(int choice) {
        this.choice = choice;
    }

    public void go () {
        Controller controller = null;

        switch (choice) {
            case 1: controller = new AddTaskController(taskList, ct);
                break;
            case 2:
                if (taskList.size() == 0) {
                    System.out.println("-Empty list! \n");

                } else {
                    controller = new EditTaskController(taskList);
                }
                break;
            case 3:
                if (taskList.size() == 0) {
                    System.out.println("-Empty list! \n");

                } else {
                    controller = new RemoveTaskController(taskList);
                }
                break;
            case 4:
                if (taskList.size() == 0) {
                    System.out.println("-Empty list! \n");

                } else {
                    controller = new AllViewController(taskList);
                }
                break;
            case 5:
                System.out.println("Program is closed! ");
                System.exit(1);
        }
        if (controller != null) {
            controller.go();
        }
    }
}
