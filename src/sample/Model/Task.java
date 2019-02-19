package sample.Model;

public class Task {
    private long datetime;
    private String description;
    private String task;

    public Task(){
    }

    public Task(String description, String task) {
        this.description = description;
        this.task = task;
    }

    public Task(long datetime, String description, String task) {
        this.datetime = datetime;
        this.description = description;
        this.task = task;
    }

    public long getDatetime() {
        return datetime;
    }

    public void setDatetime(long datetime) {
        this.datetime = datetime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
