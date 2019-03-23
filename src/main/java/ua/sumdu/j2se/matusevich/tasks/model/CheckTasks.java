package ua.sumdu.j2se.matusevich.tasks.model;

public class CheckTasks implements Runnable {
    public LinkedTaskList list;

    public CheckTasks(TaskList list) {
        this.list = (LinkedTaskList)list;
    }

    public void run() {
        for (int i = 0; i >= 0; i++) {
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("New thread");

            if (list != null) {
                Task.checkList(list);
            }
        }
    }
}
