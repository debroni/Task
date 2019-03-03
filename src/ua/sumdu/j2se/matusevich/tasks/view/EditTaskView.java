package ua.sumdu.j2se.matusevich.tasks.view;

public class EditTaskView implements View {
    @Override
    public void show() { System.out.println("Enter title to edit: "); }

    public void show2() { System.out.println("Enter new title:"); }

    public void show3() { System.out.println("Enter start, end in format dd-mm-yyyy"); }

    public void show4() { System.out.println("Enter interval"); }
}
