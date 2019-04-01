package ua.sumdu.j2se.matusevich.tasks.view;

public class RemoveTaskView implements View {

    @Override
    public void show() { System.out.println("Enter title to delete:"); }

    public void show2() { System.out.println("Remove successful!"); }

    public void show3() { System.out.println("Not found!"); }
}
