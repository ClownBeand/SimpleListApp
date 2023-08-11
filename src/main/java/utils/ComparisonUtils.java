package utils;

public class ComparisonUtils {
    public static <T> boolean isEqual(T element1, T element2) {
        return (element1 == null && element2 == null) || (element1 != null && element1.equals(element2));
    }
}
