package impl.template;

import impl.PrinterElement;
import impl.SimpleList;

import java.util.stream.IntStream;

public class PrinterElementImpl<T> implements PrinterElement<T> {
    @Override
    public void printElements(SimpleList list) {
        IntStream.range(0, list.size())
                .mapToObj(index -> list.get(index).orElse(null))
                .forEach(item -> System.out.print(item + " "));

        System.out.println();
    }


}
