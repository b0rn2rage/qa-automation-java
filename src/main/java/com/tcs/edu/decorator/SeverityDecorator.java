package com.tcs.edu.decorator;

/**
 * Содержит методы по декорированию enum'a
 */
public class SeverityDecorator {
    /**
     * Возвращает строковое значение для enum Severity
     *
     * @param severityLevel - уровень важности сообщения
     * @return - декорированный уровень важности сообщения в строковом формате
     */
    public static String toString(Severity severityLevel) {
        String stringSeverity;
        switch (severityLevel) {
            case MAJOR:
                stringSeverity = "(!!!)";
                break;
            case MINOR:
                stringSeverity = "()";
                break;
            case REGULAR:
                stringSeverity = "(!)";
                break;
            default:
                stringSeverity = "";
        }
        return stringSeverity;
    }
}
