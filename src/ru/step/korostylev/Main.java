package ru.step.korostylev;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static ru.step.korostylev.Task.taskAll;
import static ru.step.korostylev.Task.taskDetail;


public class Main {
    public static boolean nextTask = true;

    public static void main(String[] args) {

        // укажите файл и папку для сохранения заметок
        Scanner sc = new Scanner(System.in);

        instructions();
        System.out.println("Выберите номер команды");
        System.out.print(">> ");
        int command = sc.nextInt();
        // Создаем реальный файл в ОС
        File taskFileAdres = new File("I:"+File.separator+"TaskFile.txt");


        switch (command){
            case 1:
                // Прописываем Путь к файлу где будут объекты храниться.
                System.out.println("Абсолютный адрес файла: " + taskFileAdres.getAbsolutePath());
                Task.fileCreate(taskFileAdres);
                ArrayList<Task> tasks = new ArrayList<Task>();
                while (nextTask){
                    tasks.add(Task.taskCreate());
                    System.out.println("Создать еще один таск? [true/false]");
                    nextTask = sc.nextBoolean();
                    if(!nextTask){
                        Task.taskSave(taskFileAdres, tasks);
                    }
                }
                break;
            case 2:
                System.out.println("Десериализация");
                ArrayList<Task> desTaskArray = Task.readTasks(taskFileAdres);
                System.out.println("Вывод названий всех задач в записной книжке");
                taskAll(desTaskArray);
                break;
            case 3:
                ArrayList<Task> desTaskArrayDetail = Task.readTasks(taskFileAdres);
                taskDetail(desTaskArrayDetail);
                break;
        }
    }
    public static void instructions(){
        System.out.println("Программа для записи списка задач");
        System.out.println("Список комманд");
        System.out.println("1 - создать новую заметку");
        System.out.println("2 - вывести список всеx заметок");
        System.out.println("3 - вывести детали конкретной заметки");
    }
}
