package com.tcs.edu.decorator;

/**
 * Класс, позволяющий разбивать сообщения на "страницы", при помощи разделителя "---"
 * Количество сообщений на одной странице определяется переменной PAGE_SIZE
 */
public class PageSeparator implements PageDecorator {
    private static int messageCount = 0;
    private static final int PAGE_SIZE = 2;

    /**
     * Разбивает сообщения по страницам при помощи разделителя "---"
     * если messageCount делится без остатка на PAGE_SIZE, то добавляет после сообщения разделитель
     *
     * @param message - строковое сообщение
     * @return - если условие true, то возвращает сообщение с разделителем,
     * если false, то возвращает сообщение без разделителя
     */
    public String decorate(String message) {
        messageCount++;
        if (messageCount % PAGE_SIZE == 0) {
            return String.format("%s %s", message, "\n ---");
        }
        return message;
    }


}
