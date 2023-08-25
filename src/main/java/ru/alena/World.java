package ru.alena;

import java.util.ArrayList;

public class World {
    Bot[][] matrix = new Bot[100][100];
    static int bornEnergy = 200;
    {
        for (int i = 0; i < 100; i++) {
            for(int o = 0; o < 100; o++) {
                matrix[i][o] = new Bot(bornEnergy, generateGen(64), ((int) (Math.random() * 3)) > 1);
            }
        }
    }

    /**
     * Генерация кода бота, принимает вкачестве параметра размер генерируемой последовательности генокода
     * @param length
     * @return
     */
    public ArrayList<Code> generateGen(int length) {
        ArrayList<Code> gen = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            gen.add(Code.values()[Boot.r.nextInt(Code.values().length)]);
        }
        return gen;
    }

}
