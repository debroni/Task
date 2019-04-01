package ua.sumdu.j2se.matusevich.tasks.view;

public class AllView implements View {

    @Override
    public void show() {
        System.out.println("All tasks:");
    }

    public void show2() {System.out.println("Task list is empty!");}
}
