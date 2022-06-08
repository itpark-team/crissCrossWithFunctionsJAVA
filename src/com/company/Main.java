package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {
    /*1) инициализация переменных.
    2) Приветствие игроков.
    3) Ввод размеров поля.
    4) Рандом выбор кто первым ходит.
    5) Вывод поля для игроков.
    6) Игровой процесс.
    7) Проверка на победу.
    8) Меню если никто не победил. Сделать кноку возвращения на 3 позицию.
    9) Вывод победителя
     */
    enum Player {
        PlayerOne("(X - крестик)"),
        PlayerTwo("(O - нолик)"),
        None("None");

        private String value;

        Player(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    enum FieldCell {
        Cross('X'),
        Zero('O'),
        Empty('*');

        private char value;

        FieldCell(char value) {
            this.value = value;
        }

        public char getValue() {
            return value;
        }
    }

    static void printlnMessage(String message) {
        System.out.println(message);
    }

    static Player getRandomFirstStepPlayer() {
        Random random = new Random();

        if (random.nextInt(1000 + 1) > 500) {
            return Player.PlayerOne;
        } else {
            return Player.PlayerTwo;
        }
    }

    static void waitPressEnter() {
        System.out.println("Для продолжения нажмите <Enter>");
        new Scanner(System.in).nextLine();
    }

    static FieldCell[][] createField(int fieldSize) {
        return new FieldCell[fieldSize][fieldSize];
    }

    static void clearField(FieldCell[][] gameField, int fieldSize) {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                gameField[i][j] = FieldCell.Empty;
            }
        }
    }

    static void printField(FieldCell[][] gameField, int fieldSize) {
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                System.out.print(gameField[i][j].getValue());
            }
            System.out.println();
        }
    }

    static Player getWinner(FieldCell[][] gameField) {
        if (gameField[0][0] == FieldCell.Cross && gameField[0][1] == FieldCell.Cross && gameField[0][2] == FieldCell.Cross ||//горизонт 1
                gameField[1][0] == FieldCell.Cross && gameField[1][1] == FieldCell.Cross && gameField[1][2] == FieldCell.Cross || //горизонт 2
                gameField[2][0] == FieldCell.Cross && gameField[2][1] == FieldCell.Cross && gameField[2][2] == FieldCell.Cross || //горизонт 3
                gameField[0][0] == FieldCell.Cross && gameField[1][0] == FieldCell.Cross && gameField[2][0] == FieldCell.Cross ||//вертикаль 1
                gameField[0][1] == FieldCell.Cross && gameField[1][1] == FieldCell.Cross && gameField[2][1] == FieldCell.Cross || //вертикаль 2
                gameField[0][2] == FieldCell.Cross && gameField[1][2] == FieldCell.Cross && gameField[2][2] == FieldCell.Cross || //вертикаль 3
                gameField[0][0] == FieldCell.Cross && gameField[1][1] == FieldCell.Cross && gameField[2][2] == FieldCell.Cross ||//диагональ 1
                gameField[0][2] == FieldCell.Cross && gameField[1][1] == FieldCell.Cross && gameField[2][0] == FieldCell.Cross) //диагональ 2)
        {
            return Player.PlayerOne;
        }
        if (gameField[0][0] == FieldCell.Zero && gameField[0][1] == FieldCell.Zero && gameField[0][2] == FieldCell.Zero ||//горизонт 1
                gameField[1][0] == FieldCell.Zero && gameField[1][1] == FieldCell.Zero && gameField[1][2] == FieldCell.Zero || //горизонт 2
                gameField[2][0] == FieldCell.Zero && gameField[2][1] == FieldCell.Zero && gameField[2][2] == FieldCell.Zero || //горизонт 3
                gameField[0][0] == FieldCell.Zero && gameField[1][0] == FieldCell.Zero && gameField[2][0] == FieldCell.Zero ||//вертикаль 1
                gameField[0][1] == FieldCell.Zero && gameField[1][1] == FieldCell.Zero && gameField[2][1] == FieldCell.Zero || //вертикаль 2
                gameField[0][2] == FieldCell.Zero && gameField[1][2] == FieldCell.Zero && gameField[2][2] == FieldCell.Zero || //вертикаль 3
                gameField[0][0] == FieldCell.Zero && gameField[1][1] == FieldCell.Zero && gameField[2][2] == FieldCell.Zero ||//диагональ 1
                gameField[0][2] == FieldCell.Zero && gameField[1][1] == FieldCell.Zero && gameField[2][0] == FieldCell.Zero)  //диагональ 2
        {
            return Player.PlayerTwo;
        }

        return Player.None;
    }

    static int inputNumber(String message, int min, int max) {
        boolean inputResult = true;
        int number = 0;

        do {
            try {
                Scanner scanner = new Scanner(System.in);
                inputResult = true;
                System.out.print(message);
                number = scanner.nextInt();

                if (number < min || number > max) {
                    throw new Exception();
                }

            } catch (Exception e) {
                inputResult = false;
                System.out.println("Ошибка. Проверьте правильность ввода данных.");
            }

        } while (!inputResult);

        return number;
    }

    static void makeStep(FieldCell[][] gameField, int filedSize, Player currentPlayer) {
        int iStep, jStep;
        boolean stepResult;

        do {
            iStep = inputNumber(String.format("введите строчку от %d до %d: ", 1, filedSize), 1, filedSize) - 1;

            jStep = inputNumber(String.format("введите столбец от %d до %d: ", 1, filedSize), 1, filedSize) - 1;

            stepResult = true;
            if (gameField[iStep][jStep] != FieldCell.Empty) {
                stepResult = false;
                System.out.println("Ошибка хода. Эта клетка уже заполнена");
            }

        } while (!stepResult);


        if (currentPlayer == Player.PlayerOne) {
            gameField[iStep][jStep] = FieldCell.Cross;
        } else if (currentPlayer == Player.PlayerTwo) {
            gameField[iStep][jStep] = FieldCell.Zero;
        }


    }

    static Player changePlayer(Player currentPlayer) {
        if (currentPlayer == Player.PlayerOne) {
            return Player.PlayerTwo;
        } else if (currentPlayer == Player.PlayerTwo) {
            return Player.PlayerOne;
        }
        return Player.None;
    }

    public static void main(String[] args) {

        FieldCell[][] gameField;
        int fieldSize = 3;
        Player currentPlayer, winPlayer = Player.None;

        printlnMessage("Привет! Эта игра - крестики нолики.");
        printlnMessage("Правила: набрать в ряд 3 крестика или 3 нолика для победы.");

        currentPlayer = getRandomFirstStepPlayer();

        printlnMessage("первым ходит " + currentPlayer.getValue());

        waitPressEnter();

        gameField = createField(fieldSize);
        clearField(gameField, fieldSize);

        while (winPlayer == Player.None) {

            printlnMessage("Поле игры");
            printField(gameField, fieldSize);

            printlnMessage("Ход игрока " + currentPlayer.getValue());
            makeStep(gameField, fieldSize, currentPlayer);
            currentPlayer = changePlayer(currentPlayer);


            winPlayer = getWinner(gameField);
        }
        printlnMessage("победитель: " + winPlayer.getValue());

        printlnMessage("Поле игры");
        printField(gameField, fieldSize);
    }
}