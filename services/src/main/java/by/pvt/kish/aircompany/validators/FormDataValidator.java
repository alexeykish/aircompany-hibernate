package by.pvt.kish.aircompany.validators;

import java.util.regex.Pattern;

/**
 * Проверяет введеные данные
 *
 * @author Kish Alexey
 */
class FormDataValidator {

    /**
     * Только латинские или только русские буквы вплоть до 15-и символов
     * <br/>
     * Only latin or only russian letters up to 15 symbols
     */
    public static final Pattern namePattern =
            Pattern.compile("(\\A[A-Z]?[a-z]{1,15}\\z)|(\\A[А-Я]?[а-я]{1,15}\\z)");
    /**
     * Только латинские буквы вплоть до 15-и символов
     * <br/>
     * Only latin or only russian letters up to 10 symbols
     */
    public static final Pattern loginPattern =
            Pattern.compile("\\A[a-zA-Z0-9]{3,10}\\z");

    /**
     * Латинские буквы, цифры, *, ! или ^,<br/>от 6-и до 15-и символов
     * <br/>
     * Latin letters, digits, *, ! or ^ at least 6<br/>symbols up to 15
     */
    public static final Pattern passwordPattern =
            Pattern.compile("\\A[a-zA-Z0-9_\\!\\^]{6,15}\\z");

    public static final Pattern emailPattern =
            Pattern.compile("\\A[a-z0-9\\.]{3,25}@[a-z\\.]{3,10}\\.[a-z]{2,5}\\z");

}
