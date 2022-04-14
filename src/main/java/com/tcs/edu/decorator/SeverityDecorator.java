package com.tcs.edu.decorator;

/**
 * Класс, который декорирует уровень кажности сообщения
 */
public class SeverityDecorator {
    /**
     * Принимает на вход уровень важности сообщения
     * и декорирует этот уровень, возвращая строку
     * @param severityLevel - уровень важности сообщения
     * @return - декорированный уровень важности сообщения в строковом формате
     */
    public static String toString(Severity severityLevel) {
        String stringSeverity;
        switch (severityLevel) {
            case MAJOR: stringSeverity = "(!!!)"; break;
            case MINOR: stringSeverity = "()"; break;
            case REGULAR: stringSeverity = "(!)"; break;
            default: stringSeverity = "";
        }
        return stringSeverity;
    }
}
