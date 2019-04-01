package ua.sumdu.j2se.matusevich.tasks.controller;

import ua.sumdu.j2se.matusevich.tasks.model.Task;
import ua.sumdu.j2se.matusevich.tasks.model.TaskList;
import ua.sumdu.j2se.matusevich.tasks.view.EditTaskView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditTaskController implements Controller {
    private TaskList taskList = null;
    private EditTaskView view = new EditTaskView();
    private BufferedReader bufferedReader = new BufferedReader (new InputStreamReader(System.in));

    EditTaskController(TaskList taskList) {
        this.taskList = taskList;
    }

    public void go() {
        view.show();
        String search = null;
        try {
            search = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Task t: taskList
        ) {
            if (t.getTitle().equals(search)) {
                view.show2();
                String newTitle = null;
                try {
                    newTitle = bufferedReader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                t.setTitle(newTitle);

                view.show3();
                String newStart = null;
                String newEnd = null;

                try {
                    newStart = bufferedReader.readLine();
                    newEnd = bufferedReader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");

                Date newStart2 = null;
                Date newEnd2 = null;
                try {
                    newStart2 = format.parse(newStart);
                    newEnd2 = format.parse(newEnd);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                view.show4();
                int newInterval = 0;
                try {
                    newInterval = Integer.parseInt(bufferedReader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                t.setTime(newStart2, newEnd2, newInterval);
            }
        }
    }
}
