import java.util.Arrays;

public class HopfieldNetwork {
    private final int size; // Количество нейронов
    private final int[][] weights; // Матрица весов
    private final int[][] patterns; // Шаблоны для хранения

    public HopfieldNetwork(int size) {
        this.size = size;
        this.weights = new int[size][size];
        this.patterns = new int[size][size]; // Двумерный массив для шаблонов
    }

    // Метод для обучения сети
    public void train(int[][] pattern) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // Установка весов
                for (int k = 0; k < size; k++) {
                    for (int l = 0; l < size; l++) {
                        if (i != k || j != l) {
                            weights[i * size + j][k * size + l] += pattern[i][j] * pattern[k][l];
                        }
                    }
                }
            }
        }
    }

    // Метод для восстановления
    public int[][] recall(int[][] input) {
        int[][] output = new int[size][size];
        for (int i = 0; i < size; i++) {
            output[i] = Arrays.copyOf(input[i], input[i].length);
        }

        boolean isChanged;
        do {
            isChanged = false;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    int sum = 0;
                    for (int k = 0; k < size; k++) {
                        for (int l = 0; l < size; l++) {
                            sum += weights[i * size + j][k * size + l] * output[k][l];
                        }
                    }

                    int newValue = sum > 0 ? 1 : -1; // Применение активационной функции (здесь -1, 1)

                    if (newValue != output[i][j]) {
                        output[i][j] = newValue;
                        isChanged = true;
                    }
                }
            }
        } while (isChanged); // Повторять, пока есть изменения

        return output;
    }

    public static void main(String[] args) {
        HopfieldNetwork network = new HopfieldNetwork(100); // 10x10 = 100 нейронов

        // Пример шаблона для цифры 0
        int[][] digit0 = {
            {1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 0, 1, 1, 1, 1}
        };

        // Обучение сети шаблоном цифры 0
        network.train
                              // Вычисляем активацию нейрона
                    int netInput = 0;
                    for (int k = 0; k < size; k++) {
                        for (int l = 0; l < size; l++) {
                            netInput += weights[i * size + j][k * size + l] * output[k][l];
                        }
                    }
                    
                    // Обновляем состояние нейрона
                    int newState = (netInput > 0) ? 1 : 0; // Используем порог
                    if (newState != output[i][j]) {
                        output[i][j] = newState;
                        isChanged = true; // Если состояние изменилось, необходимо обновить
                    }
                }
            }
        } while (isChanged); // Продолжаем до тех пор, пока есть изменения

        return output; // Возвращаем восстановленное изображение
    }

    // Пример представления цифр в бинарном виде
    public static void main(String[] args) {
        // Создаем сеть размером 10x10
        HopfieldNetwork network = new HopfieldNetwork(10);

        // Образ для цифры "0"
        int[][] pattern0 = {
            {1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
        };

        // Обучаем сеть на образах
        network.train(pattern0);

        // Входное, частично поврежденное изображение
        int[][] noisyInput = {
            {1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
            {1, 1, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 0, 0, 1, 0, 1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 0, 0, 1, 1, 1},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
        };

        // Восстанавливаем изображение
                  {1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // Изменено на 0
            {1, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // Изменено на 0
            {1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
        };

        // Восстановление изображения
        int[][] restoredImage = network.recall(noisyInput);

        // Вывод результата
        System.out.println("Входное изображение (частичный шум):");
        printImage(noisyInput);

        System.out.println("Восстановленное изображение:");
        printImage(restoredImage);
    }

    // Метод для вывода изображения на экран
    private static void printImage(int[][] image) {
        for (int[] row : image) {
            for (int pixel : row) {
                System.out.print((pixel == 1 ? "#" : ".") + " ");
            }
            System.out.println();
        }
    }
}
