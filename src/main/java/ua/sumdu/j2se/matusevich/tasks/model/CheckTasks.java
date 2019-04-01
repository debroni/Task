package ua.sumdu.j2se.matusevich.tasks.model;

public class CheckTasks extends Thread {
    public TaskList list;

    public CheckTasks(TaskList list) {
        this.list = list;
    }

    public void setList(TaskList list) {
        this.list = list;
    }

    public void run() {
        for (int i = 0; i >= 0; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("New thread");

          // if (list != null) {
          //      TaskList.checkList(list);
            // }
        }
    }
}
