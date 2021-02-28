package com.company;

import java.util.Scanner;

public class Main {

    final int SIZE = 9;
    final int TOWIN = 4;
    int[][] array = new int[SIZE][SIZE];
    int currentPlayer = 1;


    public static void main(String[] args) {
        Main gra = new Main();
        while(!gra.round());
        System.out.println(gra.printArray());
        System.out.println("Game Over");

    }

    public boolean round()
    {
        System.out.println(numberToPlayer(currentPlayer) + " it's your turn");
        System.out.println(printArray());
        int row = -1;
        int column = -1;
        while(!play(row, column, currentPlayer))
        {
            System.out.println(numberToPlayer(currentPlayer) + " Where do you want to play? [column][row]");
            Scanner scanner = new Scanner(System.in);
            column = scanner.nextInt();
            row = scanner.nextInt();
        }
        currentPlayer++;
        if(currentPlayer > 3) currentPlayer = 1;
        return checkWin();

    }

    public boolean play(int row, int column, int player)
    {
        if(row >= array.length || row < 0 || column < 0 || column >= array.length || array[row][column] != 0) return false;
        array[row][column] = player;
        return true;
    }

    public boolean checkWin()
    {
        for(int i = 0; i < SIZE; i++)
        {
            for(int j = 0; j < SIZE; j++)
            {
                if(array[i][j] != 0)
                {
                    if(checkLeft(i, j, array[i][j], 0) == TOWIN ||
                            checkDown(i, j, array[i][j], 0) == TOWIN ||
                            checkLeftDown(i, j, array[i][j], 0) == TOWIN ||
                            checkRightDown(i, j, array[i][j], 0) == TOWIN)
                    {
                        System.out.println(numberToPlayer(array[i][j]) + " has won");
                        return true;
                    }
                }
            }
        }
        return false;
    }


    private int checkLeft(int row, int column, int player, int counter)
    {
        if(row == array.length) return counter;
        if(array[row][column] == player) return checkLeft(row+1, column, player, counter + 1);
        else return counter;
    }

    private int checkDown(int row, int column, int player, int counter)
    {
        if(column == array[0].length) return counter;
        if(array[row][column] == player) return checkDown(row, column+1, player, counter + 1);
        else return counter;
    }

    private int checkLeftDown(int row, int column, int player, int counter)
    {
        if(row == array.length || column == array[0].length) return counter;
        if(array[row][column] == player) return checkLeftDown(row+1, column+1, player, counter + 1);
        else return counter;
    }

    private int checkRightDown(int row, int column, int player, int counter)
    {
        if(row < 0 || column < 0) return counter;
        if(array[row][column] == player) return checkRightDown(row-1, column+1, player, counter + 1);
        else return counter;
    }


    public char numberToPlayer(int number)
    {
        if(number == 1) return'X';
        else if(number == 2) return 'O';
        else if(number == 3) return '△';
        else return '·';
    }

    public String printArray()
    {
        String returnString = "";

        returnString += "\t";
        for(int i = 0; i < SIZE; i++)
        {
            returnString += i + "\t";
        }
        returnString += "\n";

        for(int i = 0; i < SIZE; i++)
        {
            returnString += i + " [\t";
            for(int j = 0; j < SIZE; j++)
            {
                returnString += numberToPlayer(array[i][j]) + "\t";
            }
            returnString += "]" + i +"\n";
        }

        returnString += "\t";
        for(int i = 0; i < SIZE; i++)
        {
            returnString += i + "\t";
        }
        return returnString;
    }
}
