import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TaskManager{
    public int nextId=1;
    ArrayList<Task> tasks = new ArrayList<>();
    public void add (String title){
        tasks.add(new Task(nextId,title));
        nextId++;
    }
    public ArrayList<Task> getAll(){
        return tasks;
    }
    private Task findById(int id){
        for(Task task : tasks){
            if(task.getId()==id){
                return task;
            }
        }
        return null;
    }
    public boolean markDone(int id){
        Task t = findById(id);
        if(t==null){
            return false;
        }
        t.markDone();
        return true;
    }
    public boolean remove(int id){
        Task t = findById(id);
        if(t==null){
            return false;
        }
        return tasks.remove(t);
    }
    public ArrayList<Task> search(String text){
        ArrayList<Task> result = new ArrayList<>();
        for(Task task : tasks){
            if(task.getTaskName().toLowerCase().contains(text.toLowerCase())){
                result.add(task);
            }
        }
        return result;
    }
    public List<Task> getUndone(){
        return tasks.stream()
                .filter(task -> !task.isDone())
                .toList();
    }
    public List<Task> getDone(){
        return tasks.stream()
                .filter(task -> task.isDone())
                .toList();
    }
    public void save(String fileName){
        List<String> lines = new ArrayList<>();
        for(Task task : tasks){
            lines.add(task.getId() + ";" + task.getTaskName() + ";" + task.isDone());
        }
        try{
            Files.write(Path.of(fileName), lines);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void load(String fileName){
        Path path = Path.of(fileName);
        if(!Files.exists(path)){
            return;
        }
        try{
            List<String> lines = Files.readAllLines(path); for (String line : lines) {
                if (line.isBlank()) {
                    continue;
                }

                String[] parts = line.split(";");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                boolean done = Boolean.parseBoolean(parts[2]);

                Task task = new Task(id, name);
                if (done) {
                    task.markDone();
                }
                tasks.add(task);

                if (id >= nextId) {
                    nextId = id + 1;
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения: " + e.getMessage());
        }
    }
}
