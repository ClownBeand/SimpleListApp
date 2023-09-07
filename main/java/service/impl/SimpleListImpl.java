package service.impl;

import service.SimpleList;

import java.util.Comparator;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
    	 if (elements.length == size) {
             Object[] newElements = new Object[size * 2];
             System.arraycopy(elements, 0, newElements, 0, elements.length);
             elements = newElements;
         }
        elements[size] = element;

        size++;
    }

    @Override
    public void insert(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
        
        if (elements.length == size) {
            Object[] newElements = new Object[size * 2];
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            elements = newElements;
        }
        
        Object[] newArray = new Object[size + 1];
        IntStream.range(0, index)
                .forEach(i -> newArray[i] = elements[i]);


        elements[index] = element;

        size++;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
        elements = IntStream.range(0, elements.length)
                .filter(i -> i != index)
                .mapToObj(i -> elements[i])
                .toArray();

        size--;
    }

    @Override
    public Optional<T> get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds.");
        }
        return Optional.ofNullable((T) elements[index]);
    }

    @Override
    public void addAll(SimpleList<T> list) {
        int newSize = size + list.size();
        if (newSize > elements.length) {
               Object[] newElements = new Object[newSize * 2];
               System.arraycopy(elements, 0, newElements, 0, size);
               elements = newElements;
            
        }

        
        IntStream.range(0, list.size())
        .forEach(index -> {
            Optional<T> item = list.get(index);
            if (item.isPresent()) {
                add(item.get());
            }
            else {
                add(null); 
            }
        });
    }

    @Override
    public int first(T item) {
        return IntStream.range(0, size)
                .filter(index -> {
                    Optional<T> element = get(index);
                    return element.isPresent() && element.get().equals(item);
                })
                .findFirst()
                .orElse(-1);
    }

    @Override
    public int last(T item) {
        return IntStream.range(0, size - 1)
                .mapToObj(index -> size - 1 - index)
                .filter(index -> {
                    Optional<T> element = get(index);
                    return element.isPresent() && element.get().equals(item);
                })
                .findFirst()
                .orElse(-1);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T item) {
        return Stream.of(elements)
                .anyMatch(element -> (element == null && item == null) ||
                        (element != null && element.equals(item)));
    }

    @Override
    public SimpleList<T> shuffle() {
        Random rand = new Random();

        IntStream.range(0, size - 1)
                .forEach(i -> {
                    int j = rand.nextInt(i + 1);
                    Object temp = elements[i];
                    elements[i] = elements[j];
                    elements[j] = temp;
                    
                });

        return this;
    }

    @Override
    public SimpleList<T> sort(Comparator<T> comparator) {
        IntStream.range(0, size - 1)
                .forEach(i -> {
                    int minIndex = i;

                    for (int j = i + 1; j < size; j++) {
                        Optional<T> getJ = this.get(j);
                        Optional<T> getMinIndex = this.get(minIndex);

                        if (getJ.isPresent() && getMinIndex.isPresent() &&
                                comparator.compare(getJ.get(), getMinIndex.get()) < 0) {
                            minIndex = j;
                        }
                    }
                    Object temp = elements[i];
                    elements[i] = elements[minIndex];
                    elements[minIndex] = temp;
                    
                });
        return this;
    }

}
