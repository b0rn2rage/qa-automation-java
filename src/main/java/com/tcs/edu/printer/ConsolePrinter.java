package com.tcs.edu.printer;

/**
 * Служит для вывода текста в консоль
 * @author Смирнов Антон
 */
public class ConsolePrinter {
    /**
     * Процедура, позволяющая выводить переданную строку текста в консоль
     * @param decoratedMessage Строка текста, которая будет выведена в консоль
     */
    public static void print(String decoratedMessage) {
        System.out.println(decoratedMessage);
    }
}
