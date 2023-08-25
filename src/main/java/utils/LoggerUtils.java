package utils;

public class LoggerUtils {
    public static void printl(String s, Object... args)
    {
        System.out.println(s.formatted(args));
    }

    public static void print(String s, Object... args)
    {
        System.out.print(s.formatted(args));
    }

}
