package ua.sumdu.j2se.matusevich.tasks.view;

public class ClassMenuView implements View {

    @Override
    public void show() {
        System.out.println("Select option: \n" +
                "1. Add \n" +
                "2. Edit \n" +
                "3. Remove \n" +
                "4. View all \n" +
                "5. Exit");
    }
}
