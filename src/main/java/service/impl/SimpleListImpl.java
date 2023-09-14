package service.impl;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.Collection;

import service.SimpleList;

public class SimpleListImpl<T> implements SimpleList<T> {
    private Object[] elements;
    private static final Object[] EMPTY_ELEMENTDATA = {};
    private static final Object[] DEFAULT_CAPACITY_EMPTY = {};

    private int size;

    public SimpleListImpl(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elements = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elements = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Не соответствие размерности: " + initialCapacity);
        }
    }

    public SimpleListImpl() {
        //int DEFAULT_CAPACITY = 10;
        this.elements = DEFAULT_CAPACITY_EMPTY;
    }

    public SimpleListImpl(Collection<? extends T> c) {
        Object[] a = c.toArray();
        if ((size = a.length) != 0) {
            if (c instanceof SimpleList<?>) {
                elements = a;
            } else {
                elements = Arrays.copyOf(a, size, Object[].class);
            }
        } else {
            elements = EMPTY_ELEMENTDATA;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(T element) {
        grow();
        elements[size] = element;

        size++;
    }

    @Override
    public void insert(int index, T element) {
        rangeCheckForAdd(index);

        Object[] newArray = grow();
        IntStream.range(0, index).forEach(i -> newArray[i] = elements[i]);

        elements[index] = element;

        size++;
    }

    @Override
    public void remove(int index) {
        rangeCheckForAdd(index);
        if (index < size) {
            System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        }
        size--;
        elements[size] = null;
    }

    @Override
    public Optional<T>  get(int index) {
        rangeCheckForAdd(index);
        return Optional.ofNullable((T) elements[index]);
    }

    @Override
    public void addAll(SimpleList<T> list) {
        int newSize = size + list.size();

        if (newSize > elements.length) {
            elements = grow(newSize);
        }

        IntStream.range(0, list.size()).forEach(index -> {
            Optional<T> item = list.get(index);
            if (item.isPresent()) {
                elements[size] = item.get();
            } else {
                elements[size] = null;
            }
            size++;
        });
    }

    @Override
    public int first(T item) {
        return IntStream.range(0, size).filter(index -> {
            Optional<T> element = get(index);
            return element.isPresent() && element.get().equals(item);
        }).findFirst().orElse(-1);
    }

    @Override
    public int last(T item) {
        return IntStream.range(0, size - 1).mapToObj(index -> size - 1 - index).filter(index -> {
            Optional<T> element = get(index);
            return element.isPresent() && element.get().equals(item);
        }).findFirst().orElse(-1);
    }

    @Override
    public boolean contains(T item) {
        return Stream.of(elements)
                .anyMatch(element -> (element == null && item == null) || (element != null && element.equals(item)));
    }

    @Override
    public SimpleList<T> shuffle() {
        Random rand = new Random();

        IntStream.range(0, size - 1).forEach(i -> {
            int j = rand.nextInt(i + 1);
            Object temp = elements[i];
            elements[i] = elements[j];
            elements[j] = temp;

        });

        return this;
    }

    @Override
    public SimpleList<T> sort(Comparator<T> comparator) {
        IntStream.range(0, size - 1).forEach(i -> {
            int minIndex = i;

            for (int j = i + 1; j < size; j++) {
                Optional<T> getJ = this.get(j);
                Optional<T> getMinIndex = this.get(minIndex);

                if (getJ.isPresent() && getMinIndex.isPresent()
                        && comparator.compare(getJ.get(), getMinIndex.get()) < 0) {
                    minIndex = j;
                }
            }
            Object temp = elements[i];
            elements[i] = elements[minIndex];
            elements[minIndex] = temp;

        });
        return this;
    }

    public Object[] grow(int minCapacity) {
        int oldCapacity = elements.length;

        if (minCapacity > oldCapacity) {
            int newCapacity = (oldCapacity * 3) / 2 + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            return elements = Arrays.copyOf(elements, newCapacity);
        } else {
            return elements;
        }
    }
    //int DEFAULT_CAPACITY = 10;
    //return elements = new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
    private Object[] grow() {
        return grow(size + 1);
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
    }

}
