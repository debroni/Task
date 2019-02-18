package ua.sumdu.j2se.matusevich.tasks.view;

import ua.sumdu.j2se.matusevich.tasks.model.Task;
import ua.sumdu.j2se.matusevich.tasks.model.TaskList;

public class AllView implements View {
    private TaskList taskList;

    public AllView(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void show() {
        System.out.println("show all");
        for (Task t: taskList
             ) {
            System.out.println(t.getTitle());
        }
    }
}
