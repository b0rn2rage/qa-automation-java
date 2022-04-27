package com.tcs.edu.decorator;

/**
 * Содержит методы по проверкам
 */
public class Inspector {
    /**
     * Проверяет уникальность строки message относительно массива messages
     *
     * @param message  - сообщение, проверяемое на уникальность
     * @param messages - массив, по которому будет происходить проверка
     * @return false если элемент не уникальный (входит в массив messages),
     * true - если уникальный (такого элемента в массиве нет)
     */
    public static boolean checkUnique(String message, String[] messages) {
        if (messages != null) {
            for (String s : messages) {
                if (message.equals(s)) {
                    return false;
                }
            }
        }
        return true;
    }
}
