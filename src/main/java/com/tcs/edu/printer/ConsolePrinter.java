package com.tcs.edu.printer;

import com.tcs.edu.domain.Printer;

/**
 * Служит для вывода текста в консоль
 *
 * @author Смирнов Антон
 */
public class ConsolePrinter implements Printer {
    /**
     * Печатает в консоль переданное сообщение
     *
     * @param message Строка текста, которая будет напечатана в консоли
     */
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
