package ru.step.korostylev;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Task implements Serializable {

        String name = "";
        String description = "";
        int priority = 0;
        String startDate = "н/у";
        String endDate = "н/у";;
        String responsiblePerson = "н/у";;
        boolean isFinish = false;

        public static void fileCreate(File taskFileAdres ) {
            // Создаем реальный файл где будт храниться объекты.
            // taskFile.getAbsolutePath() - адрес файла в ОС
            File newTask = new File(taskFileAdres.getAbsolutePath());
            try {
                if(newTask.createNewFile()){
                    System.out.println("Файл для записи tасков успешно создан");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static Task taskCreate(){
            Scanner sc = new Scanner(System.in);
            Task newTask = new Task();
            System.out.println("Ведите заголовок задачи");
            newTask.name =  sc.nextLine();
            System.out.println("Ведите описание задачи");
            newTask.description = sc.nextLine();
            System.out.println("Ведите приоритет задачи");
            newTask.priority = sc.nextInt();;
            System.out.println("Ведите дату начала выполнения задачи");
            newTask.startDate = sc.nextLine();
            System.out.println("Ведите дату окончания выполнения задачи");
            newTask.endDate = sc.nextLine();
            System.out.println("Ведите кто будет выполнять задачу");
            newTask.responsiblePerson = sc.nextLine();
            return newTask;
        };

        public static void taskSave(File taskFileAdres, ArrayList<Task> tasks){
            try {
                // FileOutputStream - записывает биты в файл
                FileOutputStream outStream = new FileOutputStream(taskFileAdres);
                System.out.println("Создали поток и файл для записи в этот файл");
                // ObjectOutputStream - преобразовывает объекты в биты
                ObjectOutputStream objectOut = new ObjectOutputStream(outStream);
                objectOut.writeObject(tasks);
                objectOut.close();
                System.out.println("Сериализация объектов ArrayList прошла успешно");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static ArrayList<Task> readTasks(File taskFileAdres) {
            ArrayList<Task> desTask= new ArrayList<Task>();
            // Создаем поток чтения из файла
            System.out.println("Чтение объекта из файла, десериализация");
            try {
                //ObjectInputStream ois = new ObjectInputStream(new FileInputStream(taskFileAdres));
                //desTask = ((ArrayList<Task>)ois.readObject());
                //ois.close();
                // создаем объект потока FileOutputStream для чтения из файла
                FileInputStream inStream = new FileInputStream(taskFileAdres);
                // Создаем обертку над потоком для десереализации объекта из потока
                ObjectInputStream objInput = new ObjectInputStream(inStream);
                // Чтаем данные и десереализуем
                desTask = ((ArrayList<Task>)objInput.readObject());
                objInput.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Какие-то проблемы с подключеним к файлу," +
                        " возможно файл не существует");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Какие-то проблемы с десериализацией файла");
            }
            return desTask;
        }

        public static Task taskDetail(ArrayList<Task> desTaskArrayDetail){
            int taskArraySize = desTaskArrayDetail.size();
            System.out.println("Количество задач в списке = "+taskArraySize);
            System.out.println("Введите номер задачи");
            Scanner sc = new Scanner(System.in);
            int taskNum = sc.nextInt();
            Task taskDetail = desTaskArrayDetail.get((taskNum-1));
            System.out.println("Подробности задачи номир " + taskNum );
            System.out.println("Название: " + taskDetail.name);
            System.out.println("Описание: " + taskDetail.description);
            System.out.println("Ответственный: " + taskDetail.responsiblePerson);
            System.out.println("Приоритет (0-10): " + taskDetail.priority);
            System.out.println("Старт: " + taskDetail.startDate +
                    "Финиш задачи: " + taskDetail.endDate);
            System.out.println("Выполнена?: " + taskDetail.isFinish);
            return taskDetail;
        }

        public static void taskAll(ArrayList<Task> desTaskArray){
            for (int i=0; i<desTaskArray.size(); i++){
                int taskNum= i+1;
                System.out.println("Задача номер " + taskNum+". " +
                        desTaskArray.get(i).name);
            }
        }

}
