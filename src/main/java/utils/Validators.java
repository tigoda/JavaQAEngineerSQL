package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {
    public <T extends Enum<T>> boolean checkValueInEnum(Class<T> enumType, String value) {
        for (T val : enumType.getEnumConstants()) {
            if (val.name().equals(value)) {
                return true;
            }
        }
        return false;
    }

    public boolean isStringChars(String str) {
        Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
        Matcher matcher = pattern.matcher(str);

        return matcher.find();
    }

    public boolean isStringNumber(String str) {
        Pattern pattern = Pattern.compile("^[0-9]+$");
        Matcher matcher = pattern.matcher(str);

        return matcher.find();
    }

    public boolean isStringFloat(String str) {
        Pattern pattern = Pattern.compile("^[0-9]*[.,]?[0-9]+$");
        Matcher matcher = pattern.matcher(str);

        return matcher.find();
    }
}
