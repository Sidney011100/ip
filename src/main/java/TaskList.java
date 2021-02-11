import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.function.Consumer;

public class TaskList {
    private ArrayList<Task> list;

    TaskList() {
        this.list = new ArrayList<Task>();
    }

    protected ArrayList<Task> getList() {
        return this.list;
    }

    public void add(Task task) {
        this.list.add(task);
    }

    public void addTodo(String task) {
        this.list.add(new Todo(task));
    }

    public void addDeadline(String task, LocalDateTime date) {
        this.list.add(new Deadline(task, date));
    }

    public void addEvent(String task, LocalDateTime date) {
        this.list.add(new Event(task, date));
    }

    /**
     * Deletes Task located at provided index
     * @param index
     * @return Task that was deleted
     */
    public Task deleteTaskAtIndex(int index) {
        Task toDelete = this.list.get(index);
        this.list.remove(index);
        return toDelete;
    }

    public int size() {
        return this.list.size();
    }

    public String write(int index) {
        return this.list.get(index).saveInStorageAs();
    }

    public void markDone(int index) {
        this.list.get(index).setCompleted();
    }

    /**
     * Returns Task at given index
     * @param index
     * @return Task at index
     */
    public Task getTaskAtIndex(int index) {
        return this.list.get(index);
    }

    public Task getLastAddedTask() {
        return this.list.get(this.list.size() - 1);
    }

    public void forEach(Consumer<Task> t) {
        list.forEach(t);
    }

    /**
     * Prints the list of all task
     */
    public String listAllTasks() {
        String result = "";
        if (list.size() == 0) {
            result = result.concat("You have 0 tasks in your list. ");
        } else {
            result = result.concat("Here are the tasks in your list:\n");
            for (int i = 0; i < list.size(); i++) {
                Task current = list.get(i);
                result = result.concat(i + 1 + ". " + current + "\n");
            }
        }
        return result;
    }

    /**
     * Finds all the Tasks in the list that have the keyword input
     * @param keyword
     * @return TaskList of Tasks that contains the keyword
     */
    public TaskList filterFind(String keyword) {
        TaskList filteredList = new TaskList();
        this.forEach(x -> {
            if (x.doesTaskMatchKeyword(keyword)) {
                filteredList.add(x);
            }
        });
        return filteredList;
    }
}
