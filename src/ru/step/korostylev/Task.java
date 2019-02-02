package ru.step.korostylev;

import java.io.*;
import java.util.Scanner;

public class Task implements Serializable {

        String name = "";
        String description = "";
        int priority = 0;
        String startDate;
        String endDate;
        String responsiblePerson;
        boolean isFinish = false;

        public static void fileCreate(File taskFile) {
            // Создаем реальный файл где будт храниться объекты.
            // taskFile.getAbsolutePath() - адрес файла в ОС
            File newTask = new File(taskFile.getAbsolutePath());
            try {
                if(newTask.createNewFile()){
                    System.out.println("Файл для записи tасков успешно создан");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // ????????????????????????????????????????????
        public static void createTask(Task taskName){
            Scanner sc = new Scanner(System.in);
            Task taskName = new Task();
            System.out.println("Ведите заголовок задачи");
            taskName.name = sc.nextLine();
            System.out.println("Ведите описание задачи");
            taskName.description = sc.nextLine();
            System.out.println("Ведите приоритет задачи");
            taskName.priority = sc.nextInt();;
            System.out.println("Ведите дату начала выполнения задачи");
            taskName.startDate = sc.nextLine();
            System.out.println("Ведите дату окончания выполнения задачи");
            taskName.endDate = sc.nextLine();
            System.out.println("Ведите кто будет выполнять");
            taskName.responsiblePerson = sc.nextLine();
        };

        public static void taskSave(File taskFileAdres, Task taskNum){
            try {
                // FileOutputStream - записывает биты в файл
                FileOutputStream outStream = new FileOutputStream(taskFileAdres);
                System.out.println("Создали поток и файл для записи в этот файл");
                // ObjectOutputStream - преобразовывает объекты в биты
                ObjectOutputStream objectOut = new ObjectOutputStream(outStream);
                objectOut.writeObject(taskNum);
                objectOut.close();
                System.out.println("Сериализация прошла успешно");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void readTaskNum(File taskFileAdres, Task taskNum) {
            // Создаем поток чтения из файла
            System.out.println("Чтение объекта из файла, десериализация");
            try {
                // создаем объект потока FileOutputStream для чтения из файла
                FileInputStream inStream = new FileInputStream(taskFileAdres);
                // Создаем обертку над потоком для десереализации объекта из потока
                ObjectInputStream objInput = new ObjectInputStream(inStream);
                // Чтаем данные и десереализуем
                Object desTask = objInput.readObject();
                Task task = (Task) desTask;
                objInput.close();
                System.out.println(task.name);
                System.out.println(task.description);
                System.out.println(task.priority);
                System.out.println(task.responsiblePerson);
                System.out.println("Старт: " + task.startDate +
                        " Окончание: " + task.endDate);
                System.out.println(task.isFinish);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Какие-то проблемы с подключеним к файлу," +
                        " возможно файл не существует");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Какие-то проблемы с десериализацией файла");
            }
        }


}
