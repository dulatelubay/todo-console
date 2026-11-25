public class Task {
    private int id;
    private String taskName;
    private boolean done;
    public Task(int id, String taskName) {
        this.id = id;
        this.taskName = taskName;
    }
    public int getId() {
        return id;
    }
    public String getTaskName() {
        return taskName;
    }
    public void markDone(){
        this.done = true;
    }
    public boolean isDone() {
        return done;
    }
    @Override
    public String toString() {
        return id + ". [" + (done ? "x" : " ") + "] " + taskName;
    }
}
