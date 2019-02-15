package ua.sumdu.j2se.matusevich.tasks.view;

import ua.sumdu.j2se.matusevich.tasks.model.LinkedTaskList;
import ua.sumdu.j2se.matusevich.tasks.model.Task;
import ua.sumdu.j2se.matusevich.tasks.model.TaskList;

public class AllView implements View {
    private TaskList taskList = new LinkedTaskList();

    @Override
    public void show() {
        for (Task t: taskList
             ) {
            System.out.println(t.getTitle());
        }
    }
}
