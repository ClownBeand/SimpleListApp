package impl.template;

import utils.ArrayUtils;
import utils.CheckIndex;
import impl.SimpleList;
import utils.ComparisonUtils;

import java.util.Comparator;
import java.util.Optional;
import java.util.Random;

public class SimpleListImpl<T> implements SimpleList<T> {
    private Object[] elements;
    private int size;

    public SimpleListImpl() {
        size = 1;
        elements = new Object[size];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T element) {
        elements = ArrayUtils.increaseSize(elements, size);
        elements[size] = element;

        size++;
    }

    @Override
    public void insert(int index, T element) {
        CheckIndex.validate(index, size);
        elements = ArrayUtils.increaseSize(elements, size);

        // сдвиг элементов для получения свободного места.
        for (int i = size - 1; i >= index; i--) {
            elements[i + 1] = elements[i];
        }

        elements[index] = element;

        size++;
    }

    @Override
    public void remove(int index) {
        CheckIndex.validate(index, size);
        elements = ArrayUtils.decreaseSizeOne(elements, index);

        size--;
    }

    @Override
    public Optional<T> get(int index) {
        CheckIndex.validate(index, size);
        return Optional.ofNullable((T) elements[index]);
    }

    @Override
    public void addAll(SimpleList<T> list) {
        int newSize = size + list.size();
        if (newSize > elements.length) {
            elements = ArrayUtils.increaseSize(elements, newSize);
        }


        //IntStream.range(0,list.size());
        for (int i = 0; i < list.size(); i++) {
            add((T) list.get(i));
        }
    }

    @Override
    public int first(T item) {
        for (int i = 0; i < size; i++) {
            if (ComparisonUtils.isEqual(elements[i], item)) {
                return i;
            }
        }
        return -1; // если элемент не найден
    }

    @Override
    public int last(T item) {
        for (int i = size - 1; i >= 0; i--) {
            if (ComparisonUtils.isEqual(elements[i], item)) {
                return i;
            }
        }
        return -1; // если элемент не найден
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T item) {
        for (var element : elements) {
            if ((element == null && item == null) ||
                    (element != null && element.equals(item))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public SimpleList<T> shuffle() {
        Random rand = new Random();

        for (int i = size - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            ArrayUtils.swap(elements, i, j);
        }

        return this;
    }

    @Override
    public SimpleList<T> sort(Comparator<T> comparator) {
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < size; j++) {
                Optional<T> getJ = this.get(j);
                Optional<T> getMinIndex = this.get(minIndex);

                if (getJ.isPresent() && getMinIndex.isPresent() &&
                        comparator.compare(getJ.get(), getMinIndex.get()) < 0) {
                    minIndex = j;
                }
            }
            ArrayUtils.swap(elements, i, minIndex);
        }
        return this;
    }

}