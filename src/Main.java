import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager tk = new TaskManager();
        tk.load("tasks.txt");
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1. Добавить задачу");
            System.out.println("2. Показать все");
            System.out.println("3. Отметить выполнение задачи");
            System.out.println("4. Удалить задачу");
            System.out.println("5. Поиск задачи");
            System.out.println("6. Показать невыполненные");
            System.out.println("7. Показать выполненные");
            System.out.println("0. Выход");
            String line = sc.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(line);
            }catch(NumberFormatException e){
                System.out.println("Нужно ввести число");
                continue;
            }
            switch (choice) {
                case 1:
                    String title;
                    while (true) {
                        System.out.println("Напишите задачу: ");
                        title = sc.nextLine();
                        if (!title.isBlank()) {
                            break;
                        }
                        System.out.println("Название не может быть пустым");
                    }
                    tk.add(title);
                    tk.save("tasks.txt");
                    System.out.println("Задача добавлена");
                    break;
                case 2:
                    if(tk.getAll().isEmpty()){
                        System.out.println("Список задач пуст, заполните его");
                        break;
                    }
                    System.out.println("Список задач: ");
                    for(Task t : tk.getAll()){
                        System.out.println(t);
                    }
                    break;
                case 3: {

                    int id;
                    while (true) {
                        System.out.println("Введи номер задачи которую выполнил : ");
                        try {
                            id = Integer.parseInt(sc.nextLine());
                            break;
                        }catch(NumberFormatException e){
                            System.out.println("Нужно ввести число");
                        }
                    }
                    boolean ok = tk.markDone(id);
                    if (ok) {
                        tk.save("tasks.txt");
                        System.out.println("Готово");
                    } else {
                        System.out.println("Задача по такому ИД не найдено йоу😢");
                    }
                    break;
                }
                case 4: {
                    int id;
                    while (true) {
                        System.out.println("Введи номер задачи которую хочешь удалить : ");
                        try {
                            id = Integer.parseInt(sc.nextLine());
                            break;
                        }catch(NumberFormatException e){
                            System.out.println("Нужно ввести число");
                        }
                    }
                    boolean ok = tk.remove(id);
                    if (ok) {
                        System.out.println("Удалено");
                    } else {
                        System.out.println("ID не найдено");
                    }
                    tk.save("tasks.txt");
                    break;
                }
                case 5:
                    System.out.println("Что искать?");
                    String find = sc.nextLine();
                    ArrayList<Task> found = tk.search(find);
                    if(found.isEmpty()){
                        System.out.println("Ничего не найдено");
                    }else{
                        for(Task t : found){
                            System.out.println(t);
                        }
                    }
                    break;
                case 6:

                    List<Task> undone = tk.getUndone();
                    if(undone.isEmpty()){
                        System.out.println("Все задачи выполнены");
                    }else{
                        System.out.println("Не выполенные задачи : ");
                        for(Task t : undone){
                            System.out.println(t);
                        }
                    }
                    break;
                case 7:
                    List<Task> done = tk.getDone();
                    if(done.isEmpty()){
                        System.out.println("Все задачи еще не выполнены");
                    }else{
                        System.out.println("Выполенные задачи : ");
                        for(Task t : done){
                            System.out.println(t);
                        }
                    }
                    break;
                case 0:
                    System.out.println("Выход");
                    return;
                default:
                    System.out.println("Неизвестная команда");
            }
        }
    }
}

