package com.example.a15squarepuzzle;
import android.util.Log;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/* SquareModel.java
 * Stores data of our current game state and handles all manipulation of the data with internal
 * methods. Takes calls from SquareController.java
 *
 * @author: Aaron Stoll
 * @version: 2/28/23
 */

public class SquareModel {


    private int[][] square;
    private int[][] solution;

    private int squareDimensions;


    // Constructor
    public SquareModel(int dim){
        square = new int[dim][dim]; // initialize square board and solution board
        solution = new int[dim][dim];
        squareDimensions = dim;

        int count = 1; // set solution board with accurate values
        for(int i = 0; i<dim; i++){
            for(int j = 0; j<dim-1; j++){
                solution[i][j] = count;
                count++;
            }
        }
        solution[dim-1][dim-1] = dim^2-1;

    }


    // Returns int[] of location of input value
    // int[] -> [n row][n col]    (range: 0->n-1)
    public int[] getLocation(int value){ //return location of a value (row,col) 0 to (n-1)
        int[] location = new int[2];
        for(int i = 0; i<squareDimensions; i++){
            for(int j = 0; j<squareDimensions; j++){
                if(square[i][j] == value){
                    location[0] = i;
                    location[1] = j;
                }
            }
        }
        return location;
    }


    // Check validity of move and update square[][]
    // RETURNS: TRUE if successful
    // RETURNS: FALSE if unsuccessful
    public void moveSquare(int row, int col) { // switches the values of selected block & empty
        int value = square[row][col]; // value of selected square
        int[] loc2 = getLocation(0); // location of blank square
        if (validMove(row, col, loc2[0], loc2[1])) {
            square[row][col] = 0;
            square[loc2[0]][loc2[1]] = value; // switch values
        }
    }

    // Test if selected move is valid
    // INPUT: selected square: row, col | 0 value: row, col
    // RETURNS: TRUE  (for a valid move)
    // RETURNS: FALSE (for an invalid move)
    public boolean validMove(int r1, int c1, int r2, int c2){
        if(r2 == r1 && (c2 == c1-1 || c2 == c1+1)){
            return true;
        }
        if(c2 == c1 && (r2 == r1-1 || r2 == r1+1)){
            return true;
        }
        else{
            return false;
        }
    }

    // Updated Puzzle to random configuration
    // calls isSolveable()
    public void scramblePuzzle(){
        int[] valuesList = new int[squareDimensions*squareDimensions];
        for(int i = 0; i<(valuesList.length); i++) { // int array 0 to n values required for puzzle
            valuesList[i] = i;
        }

        Random rand = new Random();

        for (int i = 0; i < valuesList.length; i++) {
            int randomIndexToSwap = rand.nextInt(valuesList.length);
            int temp = valuesList[randomIndexToSwap];
            valuesList[randomIndexToSwap] = valuesList[i];
            valuesList[i] = temp;
        }
        int count = 0;
        for(int i = 0; i<squareDimensions; i++){
            for(int j = 0; j<squareDimensions; j++){
                square[i][j] = valuesList[count];
                count++;
            }
        }
        isSolveable();
    }

    // Tests if puzzle can be solved
    // calls scramblePuzzle() if not
    public void isSolveable(){
        int N = 0;
        int value, e;
        int n = 0;

        for (int i = 0; i < squareDimensions; i++){ // Loop through each value
            for (int j = 0; j < squareDimensions; j++){
                if(square[i][j] == 0){ // if it lands on the empty square
                    e = i;
                    continue;
                }
                value = square[i][j];

                for (int x = i; x < squareDimensions; x++){ // Loop through each value after
                    for (int y = j; y < squareDimensions; y++){
                        if(square[x][y] < value){ // comparison to increment n
                            n++;
                        }
                    }
                }
                N = N + n; // Sum inversion
                n = 0; // reset inversion var
            }
        }

        if(N % 2 == 1) {
            scramblePuzzle(); // scramble again
        }
    }

    // Checks if game is over
    // RETURNS: TRUE if solution == squares
    // RETURNS: FALSE otherwise
    public boolean isSolved(){ // check if game is over
        if(Arrays.deepEquals(square, solution)){
            return true;
        }
        else{
            return false;
        }
    }


    public int getValue(int row, int col){
        return square[row][col];
    }


}
