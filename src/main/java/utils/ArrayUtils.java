package utils;

public class ArrayUtils {
    public static Object[] increaseSize(Object[] elements, int newSize) {
        if (elements.length == newSize) {
            Object[] newElements = new Object[newSize * 2];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            return newElements;
        }
        return elements;
    }

    public static Object[] decreaseSizeOne(Object[] elements, int indexRemove) {
        Object[] newElements = new Object[elements.length - 1];
        int targetIndex = 0;

        for (int i = 0; i < elements.length; i++) {
            if (i == indexRemove) {
                continue;
            }
            newElements[targetIndex] = elements[i];
            targetIndex++;
        }

        return newElements;
    }

    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

}
