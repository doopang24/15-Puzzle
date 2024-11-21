package NumberGame;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Application {

    ThreadLocalRandom random = ThreadLocalRandom.current();

    public static void main(String[] args) {
        Application application = new Application();

        int[][] puzzle = application.start();
        int turn = 1;
        boolean isSolved = false;

        while (!isSolved) {
            application.printStatus(puzzle, turn);
            isSolved = application.isOrdered(puzzle);
            if (!isSolved) {
                int targetNumber = application.getNumberToMove(puzzle);
                puzzle = application.moveTarget(puzzle, targetNumber);
                turn++;
            }
        }

    }

    public int[][] shuffle(int[][] numbers) {
        int length = 4;
        int bound = 4;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                int verticalTarget = random.nextInt(bound); // i
                int horizonTarget = random.nextInt(bound);  // j
                int tmp = numbers[i][j];
                numbers[i][j] = numbers[verticalTarget][horizonTarget];
                numbers[verticalTarget][horizonTarget] = tmp;
            }
        }
        return numbers;
    }

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

    public void printStatus(int[][] puzzle, int turn) {
        int length = 4;
        System.out.println("Turn " + turn);
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (puzzle[i][j] == 0) {
                    System.out.print("[  ]");
                } else if (puzzle[i][j] < 10) {
                    System.out.print("[ " + puzzle[i][j] + "]");
                } else {
                    System.out.print("[" + puzzle[i][j] + "]");
                }
                System.out.println();
            }
        }
    }

    public boolean isOrdered(int[][] puzzle) {
        int verticalLength = 3;
        int horizonLength = 4;
        int bottomLineLength = 3;
        int count = 0;
        int totalGoal = 16;
        for (int i = 0; i < verticalLength; i++) {
            for (int j = 0; j < horizonLength; j++) {
                if (puzzle[i][j] == (i * 4) + j + 1) {
                    count++;
                }
            }
        }
        for (int j = 0; j < horizonLength - 1; j++) {
            if (puzzle[bottomLineLength][j] == 12 + j + 1) {
                count++;
            }
        }
        if (puzzle[verticalLength + 1][horizonLength] == 0) {
            count++;
        }
        return count == totalGoal;
    }

    public int getNumberToMove(int[][] puzzle) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("숫자 입력> ");
                int inputNumber = Integer.parseInt(scanner.nextLine());
                return validateInput(inputNumber, puzzle);
            } catch (Exception e) {
                System.out.println("잘못 입력하셨습니다. 다시 입력해 주세요.");
            }
        }
    }

    public int validateInput(int inputNumber, int[][] puzzle) throws IllegalArgumentException {
        int validNumber = 0;
        int length = 4;
        int minValue = 1, maxValue = 15;
        int verticalPosOfInput = 0;
        int horizonPosOfInput = 0;
        int verticalPosOfBlank = 0;
        int horizonPosOfBlank = 0;
        if (inputNumber < minValue || inputNumber > maxValue) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (puzzle[i][j] == inputNumber) {
                    verticalPosOfInput = i;
                    horizonPosOfInput = j;
                }
                if (puzzle[i][j] == 0) {
                    verticalPosOfBlank = i;
                    horizonPosOfBlank = j;
                }
            }
        }
        if (Math.abs(verticalPosOfInput - verticalPosOfBlank) + Math.abs(horizonPosOfInput - horizonPosOfBlank) > 1) {
            throw new IllegalArgumentException();
        }
        return validNumber;
    }

    public int[][] moveTarget(int[][] puzzle, int targetNumber) {
        int length = 4;
        for(int i=0; i<length; i++) {
            for(int j=0; j<length; j++) {
                if(puzzle[i][j] == 0) {
                    puzzle[i][j] = targetNumber;
                }
                if(puzzle[i][j] == targetNumber) {
                    puzzle[i][j] = 0;
                }
            }
        }
        return puzzle;
    }

    
}



































