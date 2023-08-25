package service.impl;

import service.PrinterElement;
import service.SimpleList;

import java.util.Comparator;
import java.util.Random;
import java.util.stream.IntStream;

import static utils.LoggerUtils.print;
import static utils.LoggerUtils.printl;

public class PrinterElementImpl implements PrinterElement<Integer> {
    @Override
    public void printElements(SimpleList<Integer> list, SimpleList<Integer> listTo) {

        printl("Заполняем значениями для list:");
        randListInt(list);
        printList(list);

        printl("\nЗаполняем значениями для listTo:");
        randListInt( listTo);
        printList(listTo);

        printl("\nРазмерность listTo: " + listTo.size());
        printl("\nРазмерность list: " + list.size());

        try {
            list.insert(3, 11 );
            printl("\nДобавили значение по index = 3" + list.get(3));
            list.remove(3);
            printl("\nУдаляем значение по index = 3" + list.get(3));

        } catch (Exception e) {
            throw new RuntimeException("Произошли неполадки!");
        }

        list.addAll(listTo);
        printl("\nПроверка addAll(Добавление в list , listTo): " + list.size() + "(size)");
        printList(list);

        printl("\nПроверка first(12) для listTo: " + listTo.first(12));
        printl("Проверка last(13) для listTo: " + listTo.last(13));

        printl("\nПроверка shuffle для list:");
        listTo.shuffle();
        printList(listTo);

        printl("\nПроверка reverseOrder для list:");
        listTo.sort(Comparator.reverseOrder());
        printList(listTo);

        printl("\nПроверка contains(13) для listTo: " + listTo.contains(13));
        printl("Проверка contains(11) для listTo: " + listTo.contains(11));
    }

    private <T> void printList(SimpleList<T> list) {
        IntStream.range(0, list.size())
                .mapToObj(list::get)
                .forEach(optionalElement -> print(optionalElement.orElse(null) + " "));
                        //(element -> System.out.print(element + " ")));
                // выводим значения без Optional
    }

    private void randListInt(SimpleList<Integer> list) {
        Random rand = new Random();
        int maxCount = 10;

       IntStream.range(0, maxCount)
                .mapToObj(i -> rand.nextInt(20))
                .forEach(list::add);

    }
}
