package ua.sumdu.j2se.matusevich.tasks.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddTaskView implements View {

    private BufferedReader bufferedReader = new BufferedReader (new InputStreamReader(System.in));

    @Override
    public void show() {
        System.out.println("Enter title");
        String newTitle;
        try {
            newTitle = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Enter start, end in format dd-mm-yyyy");
        String newStart = null;
        String newEnd = null;

        try {
            newStart = bufferedReader.readLine();
            newEnd = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");

        try {
            Date newStart2 = format.parse(newStart);
            Date newEnd2 = format.parse(newEnd);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("Enter interval");
        int newInterval = 0;
        try {
            newInterval = Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
