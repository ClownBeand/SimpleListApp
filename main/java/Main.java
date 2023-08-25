import service.PrinterElement;
import service.SimpleList;
import service.impl.PrinterElementImpl;
import service.impl.SimpleListImpl;


public class Main {
    public static void main(String[] args) {
        SimpleList<Integer> list = new SimpleListImpl<>();
        SimpleList<Integer> listTo = new SimpleListImpl<>();

        PrinterElement<Integer> printerElement = new PrinterElementImpl();
        printerElement.printElements(list, listTo);
    }
}
