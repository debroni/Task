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
            case 1: view = new AddTaskView(taskList);
                break;
            case 2: view = new ClassMenuView();
                break;
            case 3: view = new ClassMenuView();
                break;
            case 4: view = new AllView(taskList);
                break;
            case 5: System.exit(1);
        }
        view.show();
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
