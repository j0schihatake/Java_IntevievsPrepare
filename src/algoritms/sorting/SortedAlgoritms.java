package algoritms.sorting;

import java.util.Arrays;

public class SortedAlgoritms {

    // https://javarush.ru/groups/posts/1997-algoritmih-sortirovki-v-teorii-i-na-praktike

    public static void main(String[] args){
        SortedAlgoritms algoritms = new SortedAlgoritms();

        algoritms.bubbleSort();

        algoritms.selectionSort();
    }

    /**
     * Метод меняет местами элементы:
     * @param array
     * @param ind1
     * @param ind2
     */
    private void swap(int[] array, int ind1, int ind2) {
        int tmp = array[ind1];
        array[ind1] = array[ind2];
        array[ind2] = tmp;
    }

    // Простейшая сортировка (Bubble Sort)
    public void bubbleSort(){
        System.out.println("Пример сортировки BuubleSort:");
        int[] array = {10, 2, 10, 3, 1, 2, 5};
        System.out.println(Arrays.toString(array));
        boolean needIteration = true;
        while (needIteration) {
            needIteration = false;
            for (int i = 1; i < array.length; i++) {
                if (array[i] < array[i - 1]) {
                    swap(array, i, i-1);
                    needIteration = true;
                }
            }
        }
        System.out.println(Arrays.toString(array));
    }

    // Сортировка выбором (Selection Sort)
    public void selectionSort(){
        System.out.println("Пример сортировки SelectionSort:");
        int[] array = {10, 2, 10, 3, 1, 2, 5};
        System.out.println(Arrays.toString(array));
        for (int left = 0; left < array.length; left++) {
            int minInd = left;
            for (int i = left; i < array.length; i++) {
                if (array[i] < array[minInd]) {
                    minInd = i;
                }
            }
            swap(array, left, minInd);
        }
        System.out.println(Arrays.toString(array));
    }

    // Сортировка вставками (Insertion Sort)
    public void insertionSort(){
        System.out.println("Пример сортировки InsertionSort:");
        int[] array = {10, 2, 10, 3, 1, 2, 5};
        System.out.println(Arrays.toString(array));
        for (int left = 0; left < array.length; left++) {
            // Вытаскиваем значение элемента
            int value = array[left];
            // Перемещаемся по элементам, которые перед вытащенным элементом
            int i = left - 1;
            for (; i >= 0; i--) {
                // Если вытащили значение меньшее — передвигаем больший элемент дальше
                if (value < array[i]) {
                    array[i + 1] = array[i];
                } else {
                    // Если вытащенный элемент больше — останавливаемся
                    break;
                }
            }
            // В освободившееся место вставляем вытащенное значение
            array[i + 1] = value;
        }
        System.out.println(Arrays.toString(array));
    }

    // Челночная сортировка (Shuttle Sort)
    public void shuttleSort(){
        System.out.println("Пример сортировки ShuttleSort:");
        int[] array = {10, 2, 10, 3, 1, 2, 5};
        System.out.println(Arrays.toString(array));
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                swap(array, i, i - 1);
                for (int z = i - 1; (z - 1) >= 0; z--) {
                    if (array[z] < array[z - 1]) {
                        swap(array, z, z - 1);
                    } else {
                        break;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(array));
    }

    // Сортировка Шелла
    public void schella(){
        System.out.println("Пример сортировки Шела:");
        int[] array = {10, 2, 10, 3, 1, 2, 5};
        System.out.println(Arrays.toString(array));
        // Высчитываем промежуток между проверяемыми элементами
        int gap = array.length / 2;
        // Пока разница между элементами есть
        while (gap >= 1) {
            for (int right = 0; right < array.length; right++) {
                // Смещаем правый указатель, пока не сможем найти такой, что
                // между ним и элементом до него не будет нужного промежутка
                for (int c = right - gap; c >= 0; c -= gap) {
                    if (array[c] > array[c + gap]) {
                        swap(array, c, c + gap);
                    }
                }
            }
            // Пересчитываем разрыв
            gap = gap / 2;
        }
        System.out.println(Arrays.toString(array));
    }

    /**
     * Cортировка слиянием (merge sort): mergeSort(array, 0, array.length-1)
     * Во-вторых, сложность у нас будет уже не квадратичная, как мы с вами привыкли. Сложность данного алгоритма
     * — логарифмическая. Записывается как O(n log n).
     */
    public static void mergeSort(int[] source, int left, int right) {
        // Выберем разделитель, т.е. разделим пополам входной массив
        int delimiter = left + ((right - left) / 2) + 1;
        // Выполним рекурсивно данную функцию для двух половинок (если сможем разбить(
        if (delimiter > 0 && right > (left + 1)) {
            mergeSort(source, left, delimiter - 1);
            mergeSort(source, delimiter, right);
        }
        // Создаём временный массив с нужным размером
        int[] buffer = new int[right - left + 1];
        // Начиная от указанной левой границы идём по каждому элементу
        int cursor = left;
        for (int i = 0; i < buffer.length; i++) {
            // Мы используем delimeter чтобы указывать на элемент из правой части
            // Если delimeter > right, значит в правой части не осталось недобавленных элементов 32 14
            if (delimiter > right || source[cursor] > source[delimiter]) {
                buffer[i] = source[cursor];
                cursor++;
            } else {
                buffer[i] = source[delimiter];
                delimiter++;
            }
        }
        System.arraycopy(buffer, 0, source, left, buffer.length);
    }

    /**
     * Сортировка подсчётом (Counting Sort) и Поразрядная сортировка (Radix Sort)
     * Другим интересным алгоритмом сортировки является сортировка подсчётом (Counting Sort). Алгоритмическая сложность
     * в этом случае будет O(n+k), где n — количество элементов, а k — максимальное значение элемента. Есть с
     * алгоритмом одна незадача: нам нужно знать минимальное и максимальное значение в массиве.
     */
    public static int[] countingSort(int[] theArray, int maxValue) {
        // Массив со "счётчиками" размером от 0 до максимального значения
        int numCounts[] = new int[maxValue + 1];
        // В соответствующей ячейке (индекс = значение) увеличиваем счётчик
        for (int num : theArray) {
            numCounts[num]++;
        }
        // Подготавливаем массив для отсортированного результата
        int[] sortedArray = new int[theArray.length];
        int currentSortedIndex = 0;
        // идём по массиву со "счётчиками"
        for (int n = 0; n < numCounts.length; n++) {
            int count = numCounts[n];
            // идём по количеству значений
            for (int k = 0; k < count; k++) {
                sortedArray[currentSortedIndex] = n;
                currentSortedIndex++;
            }
        }
        return sortedArray;
    }

    /**
     * Быстрая сортировка (Quick Sort)
     * Ну и на сладкое — один из самых известных алгоритмов: быстрая сортировка. Она имеет алгоритмическую сложность,
     * то есть мы имеем O(n log n). Данную сортировку ещё называют сортировкой Хоара. Интересно, что алгоритм был
     * придуман Хоаром во время его пребывания в Советском Союзе, где он обучался в Московском университете
     * компьютерному переводу и занимался разработкой русско-английского разговорника. А ещё данный алгоритм в более
     * сложной реализации используется в Arrays.sort в Java. А что с Collections.sort? Предлагаю посмотреть
     * самостоятельно, как сортируются они "под капотом".
     */
    public static void quickSort(int[] source, int leftBorder, int rightBorder) {
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        int pivot = source[(leftMarker + rightMarker) / 2];
        do {
            // Двигаем левый маркер слева направо пока элемент меньше, чем pivot
            while (source[leftMarker] < pivot) {
                leftMarker++;
            }
            // Двигаем правый маркер, пока элемент больше, чем pivot
            while (source[rightMarker] > pivot) {
                rightMarker--;
            }
            // Проверим, не нужно обменять местами элементы, на которые указывают маркеры
            if (leftMarker <= rightMarker) {
                // Левый маркер будет меньше правого только если мы должны выполнить swap
                if (leftMarker < rightMarker) {
                    int tmp = source[leftMarker];
                    source[leftMarker] = source[rightMarker];
                    source[rightMarker] = tmp;
                }
                // Сдвигаем маркеры, чтобы получить новые границы
                leftMarker++;
                rightMarker--;
            }
        } while (leftMarker <= rightMarker);

        // Выполняем рекурсивно для частей
        if (leftMarker < rightBorder) {
            quickSort(source, leftMarker, rightBorder);
        }
        if (leftBorder < rightMarker) {
            quickSort(source, leftBorder, rightMarker);
        }
    }
}
