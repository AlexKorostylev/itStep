package ru.step.korostylev;

import java.io.*;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        // Прописываем Путь к файлу где будут объекты храниться.
        File taskFileAdres = new File("I:"+File.separator+"TaskFile.txt");
        System.out.println("Абсолютный адрес файла: " + taskFileAdres.getAbsolutePath());
        // Создаем реальный файл в ОС
        Task.fileCreate(taskFileAdres);
        // Как(какой) передать параметр в метод Task.createTask(????)
        // чтобы введенное пользователем значение стало "Именем" ОБЪЕКТА сереализации?
        // (строка кода 27), и ((строка кода 30 Task.java)
        // Т.е хотел бы получить Task.createTask(task1) или (task2) или (task2) и т.д,
        // но чтобы номер таска менялся от 1 до N с помощью декримента (чтобы вручную
        // не писать)))
        // Мне это нужно, чтобы иметь возможность десерилизовать объект обратно по
        // уникальному имени или номеру объекта.
        Scanner sc = new Scanner(System.in);
        System.out.println("Ведите заголовок задачи");
        String taskName = sc.nextLine();
        Task.createTask(taskName);
        //Task.taskSave(taskFileAdres, task1);
        //Task.taskSave(taskFileAdres, task2);
        //Task.readTaskNum(taskFileAdres, task1);
        //Task.readTaskNum(taskFileAdres, task2);
    }
}
