package com.example.michel_desktop.higherlower;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class HigherLower extends AppCompatActivity {

    //textvieuw
    private TextView score;
    private TextView highscore;
    private TextView wonLose;

    //buttons
    private Button buttonLeft;
    private Button buttonRight;

    //all imgage names
    private int[] mImageNames;
    private ImageView mImageView;

    //begin voor de start van de rol
    private int rolNummer = 3;
    private int highScoreInt = 0;
    private int huidigeScore = 0;

    private ListView mListVieuw = null;
    private ArrayAdapter<String> mAdapter = null;
    private List<String> list;

    /**
     * On create
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_higher_lower);

        //inisaliseer text
        this.score = findViewById(R.id.text_score);
        updateScore();
        this.highscore = findViewById(R.id.text_high_score);
        updateHighScore();
        this.wonLose = findViewById(R.id.won_lose_vieuw);
        this.wonLose.setTextColor(Color.WHITE);
        this.wonLose.setBackgroundColor(Color.GRAY);

        //inisaliseer button
        this.buttonLeft = findViewById(R.id.button_left);
        this.buttonRight = findViewById(R.id.button_right);

        //inisaliseer img
        this.mImageNames = new int[]{R.drawable.een, R.drawable.twee, R.drawable.drie,
                R.drawable.vier, R.drawable.vijf, R.drawable.zes};
        this.mImageView = findViewById(R.id.dobbel_steen_img);

        //geef aan wat er moet gebeuren als je op button left drukt
        this.buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rol(0);
            }
        });

        //geef aan wat er moet gebeuren als er op button right drukt
        this.buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rol(1);
            }
        });

        //vieuw andapter cotroler set up
        this.mListVieuw = findViewById(R.id.list_view);
        this.list = new ArrayList<String>();
        this.mAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, list);
        mListVieuw.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_higher_lower, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Methoden die de picture goed instelt
     *
     * @param MATH_NUMMER nummer wat uit math random is gekomen
     */
    private void setPicture(final int MATH_NUMMER){

        //kijk wel nummer match nummer is
        switch (MATH_NUMMER){
            case 1:
                //set new img
                this.mImageView.setImageResource(mImageNames[MATH_NUMMER-1]);
                break;
            case 2:
                //set new img
                this.mImageView.setImageResource(mImageNames[MATH_NUMMER-1]);
                break;
            case 3:
                //set new img
                this.mImageView.setImageResource(mImageNames[MATH_NUMMER-1]);
                break;
            case 4:
                //set new img
                this.mImageView.setImageResource(mImageNames[MATH_NUMMER-1]);
                break;
            case 5:
                //set new img
                this.mImageView.setImageResource(mImageNames[MATH_NUMMER-1]);
                break;
            case 6:
                //set new img
                this.mImageView.setImageResource(mImageNames[MATH_NUMMER-1]);
                break;
                default:
                    System.out.println("Math nummer is niet een getal wat tussen de 1 en 6 ligt");
        }
    }

    /**
     * Methoden wat de rol regeld
     *
     * @param HOGER_LAGER 0 is lager. 1 is hoger
     */
    private void rol(final int HOGER_LAGER){

        int startRoll = (int) (Math.random() * 6 + 1);

        if(HOGER_LAGER == 0){
            //als start rol grote is dan this.rolNummer is het fout
            if(startRoll > this.rolNummer){
                this.huidigeScore = 0;
                updateScore();
                this.wonLose.setText("You lose");
                this.list.clear();
            } else {
                this.huidigeScore++;
                updateScore();
                this.wonLose.setText("You won");
            }
        } else {
            //als start rol kleiner is dan this.rolNummer is het fout
            if(startRoll < this.rolNummer){
                this.huidigeScore = 0;
                updateScore();
                this.wonLose.setText("You lose");
                this.list.clear();
            } else {
                this.huidigeScore++;
                updateScore();
                this.wonLose.setText("You won");
            }
        }

        //update rol nummer
        this.rolNummer = startRoll;

        //update picture
        setPicture(startRoll);

        //update high score
        if(this.highScoreInt < this.huidigeScore){
            updateHighScore();
        }

        //add worp in arrayList
        list.add("Throw is " + startRoll);

        //update list
        mAdapter.notifyDataSetChanged();
    }



    /**
     * Update score
     */
    private void updateScore(){
        this.score.setText("Score: "+this.huidigeScore);
    }

    /**
     * Update high_score
     */
    private void updateHighScore(){
        this.highScoreInt = this.huidigeScore;
        this.highscore.setText("HighScore: "+this.huidigeScore);
    }
}
