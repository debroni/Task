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
        int i = 1;
        for (Task t: taskList
             ) {
            if (t.getTitle().isEmpty()) {
                System.out.println("Task list is empty!");
            } else {
                System.out.println(i + ") ->" + t.getTitle());
                i++;
            }
        }
    }
}
