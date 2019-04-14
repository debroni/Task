package ua.sumdu.j2se.matusevich.tasks.controller;

import ua.sumdu.j2se.matusevich.tasks.model.CheckTasks;
import ua.sumdu.j2se.matusevich.tasks.model.Task;
import ua.sumdu.j2se.matusevich.tasks.model.TaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddTaskController implements Controller {
    private TaskList taskList;
    private CheckTasks ct;
    private BufferedReader bufferedReader = new BufferedReader (new InputStreamReader(System.in));

    AddTaskController(TaskList taskList, CheckTasks ct) {
        this.taskList = taskList;
        this.ct = ct;
    }

    public void go() {
        System.out.println("Enter title");
        String newTitle = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        Date newStart2 = null;
        Date newEnd2 = null;
        String newStart = null;
        String newEnd = null;
        int newInterval = 0;

        try {
            newTitle = bufferedReader.readLine();

            System.out.println("Enter start, end in yyyy-MM-dd HH:mm:ss.SSS");

            newStart = bufferedReader.readLine();
            newEnd = bufferedReader.readLine();


            newStart2 = format.parse(newStart);
            newEnd2 = format.parse(newEnd);

            System.out.println("Enter interval");

            newInterval = Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Task newTask = new Task(newTitle, newStart2, newEnd2, newInterval);

        taskList.add(newTask);
        ct.setList(taskList);
    }
}
