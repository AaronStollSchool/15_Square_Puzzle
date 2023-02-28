package com.example.a15squarepuzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/* MainActivity.java
 * Main class for the program, in charge of keeping button references and listeners,
 * calls to SquareController.java to have it update the model for us. Toggles game won textView
 *
 * @author: Aaron Stoll
 * @version: 2/28/23
 */
public class MainActivity extends AppCompatActivity {

    private TextView youWin;

    private Button b0;
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    private Button b6;
    private Button b7;
    private Button b8;
    private Button b9;
    private Button b10;
    private Button b11;
    private Button b12;
    private Button b13;
    private Button b14;
    private Button b15;

    private Button[][] buttonSq;

    private Button scramble;
    private SquareController control;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // perform superclass operations; create layout
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        control = new SquareController(); // create controller and initialize board
        buttonSq = new Button[4][4];

        //initialize buttons
        buttonSq[0][0] = (Button)findViewById(R.id.button1);
        buttonSq[0][1] = (Button)findViewById(R.id.button2);
        buttonSq[0][2] = (Button)findViewById(R.id.button3);
        buttonSq[0][3] = (Button)findViewById(R.id.button4);
        buttonSq[1][0] = (Button)findViewById(R.id.button5);
        buttonSq[1][1] = (Button)findViewById(R.id.button6);
        buttonSq[1][2] = (Button)findViewById(R.id.button7);
        buttonSq[1][3] = (Button)findViewById(R.id.button8);
        buttonSq[2][0] = (Button)findViewById(R.id.button9);
        buttonSq[2][1] = (Button)findViewById(R.id.button10);
        buttonSq[2][2] = (Button)findViewById(R.id.button11);
        buttonSq[2][3] = (Button)findViewById(R.id.button12);
        buttonSq[3][0] = (Button)findViewById(R.id.button13);
        buttonSq[3][1] = (Button)findViewById(R.id.button14);
        buttonSq[3][2] = (Button)findViewById(R.id.button15);
        buttonSq[3][3] = (Button)findViewById(R.id.button0);

        // Textview to display when game state is a win
        youWin = (TextView)findViewById(R.id.textView);
        youWin.setVisibility(View.INVISIBLE);

        // set listener for reset button
        scramble = findViewById(R.id.scramble);
        scramble.setOnClickListener(new ScrambleButtonListener());
        // call to update button text with postscrambled values
        refreshSqM();
        // setting listeners for each button
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++) {
                final int r = i;
                final int c = j;
                buttonSq[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean winState = control.squarePress(r,c);
                        if(winState){youWin.setVisibility(View.VISIBLE);}
                        else{youWin.setVisibility(View.INVISIBLE);}
                        refreshSqM();
                    }
                });
            }
        }
    }

    // listener for reset button
    private class ScrambleButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            control.scramblePuzzle();
            youWin.setVisibility(View.INVISIBLE);
            refreshSqM();
        }
    }

    // Method to call controller to return current model value at changed location
    // Sets button text to updated model values
    private void refreshSqM(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(control.refreshSq(i,j) == 0){
                    buttonSq[i][j].setText("");
                }
                else {
                    buttonSq[i][j].setText(Integer.toString(control.refreshSq(i, j)));
                }
            }
        }
    }

}