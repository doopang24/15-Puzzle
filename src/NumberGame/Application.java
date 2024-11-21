package NumberGame;

import java.util.*;

public class Application {

    public int[][] start() {
        System.out.println("재미있는 15 퍼즐!");
        int[][] numbers = new int[4][4];
        int length = numbers.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                numbers[i][j] = (i * 4) + j;    // 0~15까지 숫자 넣음
            }
        }
        return shuffle(numbers);
    }

    public static void main(String[] args) {
        Application application = new Application();


    }
}
