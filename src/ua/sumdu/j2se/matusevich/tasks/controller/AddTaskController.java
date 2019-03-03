package ua.sumdu.j2se.matusevich.tasks.controller;

import ua.sumdu.j2se.matusevich.tasks.model.Task;
import ua.sumdu.j2se.matusevich.tasks.model.TaskList;
import ua.sumdu.j2se.matusevich.tasks.view.AddTaskView;
import ua.sumdu.j2se.matusevich.tasks.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddTaskController implements Controller {
    TaskList taskList = null;
    AddTaskView view = new AddTaskView();
    private BufferedReader bufferedReader = new BufferedReader (new InputStreamReader(System.in));

    public AddTaskController(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void go() {
        view.show();
        String newTitle = null;
        SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");

        Date newStart2 = null;
        Date newEnd2 = null;
        String newStart = null;
        String newEnd = null;
        int newInterval = 0;

        try {
            newTitle = bufferedReader.readLine();

            view.show2();

            newStart = bufferedReader.readLine();
            newEnd = bufferedReader.readLine();


            newStart2 = format.parse(newStart);
            newEnd2 = format.parse(newEnd);

            view.show3();

            newInterval = Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Task newTask = new Task(newTitle, newStart2, newEnd2, newInterval);
        taskList.add(newTask);
    }
}
