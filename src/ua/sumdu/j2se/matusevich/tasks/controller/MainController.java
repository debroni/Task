package ua.sumdu.j2se.matusevich.tasks.controller;

import ua.sumdu.j2se.matusevich.tasks.model.TaskList;
import ua.sumdu.j2se.matusevich.tasks.view.ClassMenuView;
import ua.sumdu.j2se.matusevich.tasks.view.View;

import java.io.File;
import java.io.IOException;

import static ua.sumdu.j2se.matusevich.tasks.model.TaskIO.writeText;

public class MainController implements Controller {
    private TaskList taskList;
    private File file;
    int choice;
    public MainController(TaskList t, File f, int choice) {
        this.taskList = t;
        this.file = f;
        this.choice = choice;
    }

    @Override
    public void go() {
        Controller controller = null;
        View view = new ClassMenuView();

        switch (choice) {
            case 1: controller = new AddTaskController(taskList);
                break;
            case 2: controller = new EditTaskController(taskList);
                break;
            case 3: controller = new RemoveTaskController(taskList);
                break;
            case 4: controller = new AllViewController(taskList);
                break;
            case 5:
                try {
                    writeText(taskList,file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.exit(1);
        }
        view.show();
    }
}
