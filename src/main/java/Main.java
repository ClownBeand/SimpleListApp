import impl.PrinterElement;
import impl.SimpleList;
import impl.template.PrinterElementImpl;
import impl.template.SimpleListImpl;

import java.util.Comparator;
import java.util.stream.IntStream;


public class Main {
    public static void main(String[] args) {
        SimpleList<Integer> list = new SimpleListImpl<>();
        SimpleList<Integer> listTo = new SimpleListImpl<>();

        PrinterElement<Integer> printer = new PrinterElementImpl<>();

        System.out.println("Заполняем значениями для list:");
        IntStream.range(0, 12).forEach(i -> list.add(i));
        printer.printElements(list);


        System.out.println("\nЗаполняем значениями для listTo:");
        IntStream.range(12, 24).forEach(i -> listTo.add(i));
        printer.printElements(listTo);

        System.out.println("\nРазмерность listTo: " + listTo.size());
        System.out.println("\nРазмерность list: " + list.size());

        try {
            System.out.println("\nДобавили значение по index = 3");
            list.insert(3, 11);
            System.out.println(list.get(3));
            System.out.println("\nУдаляем значение по index = 3");
            list.remove(3);
            System.out.println(list.get(3));

        } catch (Exception e) {
            throw new RuntimeException("Произошли неполадки!");
        }

        System.out.print("\nПроверка addAll(Добавление в list , listTo): ");
        list.addAll(listTo);
        System.out.println(list.size());
        System.out.println(list.get(14));

        System.out.println("\nПроверка first(12) для listTo: " + listTo.first(12));
        System.out.println("Проверка last(13) для listTo: " + listTo.last(13));

        System.out.println("\nПроверкаа shuffle для list:");
        listTo.shuffle();
        printer.printElements(listTo);

        System.out.println("\nПроверкаа reverseOrder для list:");
        listTo.sort(Comparator.reverseOrder());
        printer.printElements(listTo);

        System.out.println("\nПроверка contains(13) для listTo: " + listTo.contains(13));
        System.out.println("Проверка contains(11) для listTo: " + listTo.contains(11));
    }
}
