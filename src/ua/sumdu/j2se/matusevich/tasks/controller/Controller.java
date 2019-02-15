package ua.sumdu.j2se.matusevich.tasks.controller;

import ua.sumdu.j2se.matusevich.tasks.model.Task;
import ua.sumdu.j2se.matusevich.tasks.model.TaskList;
import ua.sumdu.j2se.matusevich.tasks.view.AddTaskView;
import ua.sumdu.j2se.matusevich.tasks.view.AllView;
import ua.sumdu.j2se.matusevich.tasks.view.ClassMenuView;
import ua.sumdu.j2se.matusevich.tasks.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller {
    private TaskList taskList;
    private BufferedReader bufferedReader = new BufferedReader (new InputStreamReader(System.in));

    public Controller(TaskList t) {
        this.taskList = t;
    }

    public void handleAction(int action) {
        View view = null;

        switch (action) {
            case 1: view = new AddTaskView();
                break;
            case 2: view = new ClassMenuView();
                break;
            case 3: view = new ClassMenuView();
                break;
            case 4: view = new AllView();
                break;
            case 5: System.exit(1);

            default: view = new ClassMenuView();
        }
        view.show();
    }

    public String getTasks() {
        return taskList.toString();
    }

    public void addTask() throws ParseException {

        System.out.println("Enter date dd-mm-yyyy");
        String start2 = null;
        String end2 = null;
        try {
            start2 = bufferedReader.readLine();
            end2 = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");

        Date start = format.parse(start2);
        Date end = format.parse(end2);

        System.out.println("Enter title");

        String title = null;
        try {
            title = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Task t:taskList
        ) {
            if(t.getTitle().equals(title)) {
                System.out.println("This name is used");
                return;
            }
        }

        System.out.println("Enter interval");
        int interval = 0;
        try {
            interval = bufferedReader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Task newTask;

        newTask = new Task(title, start, end, interval);
        taskList.add(newTask);
        System.out.println("Task was add successful!");
    }

    public void removeTask() {
        System.out.println("Enter name to delete");
        String title = null;
        try {
            title = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Task task:taskList
             ) {
            if (task.getTitle().equals(title)) {
                taskList.remove(task);
                System.out.println("Task was remove successful!");
            }
        }
    }

    public void editTask() throws ParseException {
        System.out.println("Enter name to edit");
        String title = null;
        try {
            title = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Task task:taskList
        ) {
            if (task.getTitle().equals(title)) {
                System.out.println("Enter new title, start, end, interval");

                String newTitle = null;
                String newStart = null;
                String newEnd = null;
                int newInterval = Integer.parseInt(null);
                try {
                    newTitle = bufferedReader.readLine();
                    newStart = bufferedReader.readLine();
                    newEnd = bufferedReader.readLine();
                    newInterval = bufferedReader.read();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");

                Date start = format.parse(newStart);
                Date end = format.parse(newEnd);

                Task newTask = new Task(newTitle, start, end, newInterval);
                taskList.remove(task);
                taskList.add(newTask);

                System.out.println("Task was edit successful!");
            }
        }
    }
}
