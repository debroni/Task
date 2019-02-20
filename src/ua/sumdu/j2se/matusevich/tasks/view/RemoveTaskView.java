package ua.sumdu.j2se.matusevich.tasks.view;

import ua.sumdu.j2se.matusevich.tasks.model.Task;
import ua.sumdu.j2se.matusevich.tasks.model.TaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RemoveTaskView implements View {
    private TaskList taskList;
    public RemoveTaskView(TaskList taskList) {
        this.taskList = taskList;
    }
    private BufferedReader bufferedReader = new BufferedReader (new InputStreamReader(System.in));

    @Override
    public void show() {
        System.out.println("Enter title to delete:");
        String search = null;
        try {
            search = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Task t: taskList) {
            if (t.getTitle().equals(search)) {
                Task newTask = new Task(t.getTitle(), t.getStartTime(), t.getEndTime(), t.getRepeatInterval());
                taskList.remove(newTask);
            }
        }
    }
}
