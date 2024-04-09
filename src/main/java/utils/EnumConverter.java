package utils;

public class EnumConverter {

    public <T extends Enum<T>> String getNamesFromEnum(Class<T> clazz, String delimiter) {
        T[] values = clazz.getEnumConstants();
        String[] commandsConsole = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            commandsConsole[i] = values[i].name().toLowerCase();
        }
        return String.join(delimiter, commandsConsole);

    }
}
