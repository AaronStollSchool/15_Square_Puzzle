package com.example.a15squarepuzzle;

import android.util.Log;
import android.view.View;

/* SquareController.java
 * Control class, object of which is constructed in MainActivity.java
 * Initializes a new model and takes calls from the MainActivity class and routes them
 * to the model.
 *
 * @author: Aaron Stoll
 * @version: 2/28/23
 */

public class SquareController{

    private SquareModel model;

    // Runs on when application first opens
    public SquareController(){
        model = new SquareModel(4);
        model.scramblePuzzle();
    }

    // Calls model method scramblePuzzle()
    public void scramblePuzzle(){
        model.scramblePuzzle();
        Log.d("click", "click");
    }

    // Calls model method squarePress()
    // calls model method isSolved() and returns boolean to main
    public boolean squarePress(int row, int col){
        model.moveSquare(row,col);
        Log.d("row",Integer.toString(row));
        Log.d("col",Integer.toString(col));

        boolean gameState = model.isSolved();
        return gameState;

    }
    // middle man method between main and model
    public int refreshSq(int row, int col){
        return model.getValue(row, col);
    }
}
