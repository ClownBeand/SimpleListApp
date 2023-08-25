package utils;

public class CheckIndex {
    public static void validate(int index, int size) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
    }
}
