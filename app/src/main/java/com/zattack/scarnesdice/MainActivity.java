package com.zattack.scarnesdice;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public static int yourScore = 0;
    public static int compScore = 0;
    public static int yourTurnScore = 0;
    public static int compTurnScore = 0;

    TextView textView = (TextView)findViewById(R.id.gameStatus);

    private Random r = new Random();

    private int getDiceValue(){
        int diceValue = r.nextInt(6)+1;
        String img = "dice" + Integer.toString(diceValue);
        int resourceId = this.getResources().getIdentifier(img, "drawable", getPackageName());
        Drawable d = getDrawable(resourceId);
        ImageView imageView = (ImageView)findViewById(R.id.diceImage);
        imageView.setImageDrawable(d);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return diceValue;
    }

    public void rollDice(View view){
        int value = getDiceValue();
        textView.setText("Player Rolled" + value);
        if(value!=1){
            yourTurnScore+=value;
        }else {
            textView.setText("Rolled 1");
            yourTurnScore = 0;
            computerTurn();
        }
    }

    public void holdScore(View view){
        textView.setText("Hold Score");
        yourScore += yourTurnScore;
        yourTurnScore = 0;
        updateScore();
        computerTurn();
    }

    private void updateScore(){
        TextView t1 = (TextView)findViewById(R.id.yourScore);
        t1.setText("Your Score:" + yourScore);
        TextView t2 = (TextView)findViewById(R.id.compScore);
        t2.setText("Computer's Score:" + compScore);
    }

    public void resetGame(View view){
        textView.setText("Game Reset");
        yourScore = 0;
        compScore = 0;
        yourTurnScore = 0;
        compTurnScore = 0;
        updateScore();
    }

    public void computerTurn() {
        disableButtons();
        textView.setText("Computer's Turn");
        boolean flag = false;

        for(int max_turn = r.nextInt(3)+3;!flag && max_turn > 0;max_turn--){
            int value = getDiceValue();
            textView.setText("Computer Rolled " + value);
            if(value != 1){
                compTurnScore += value;
            }else {
                flag = true;
            }
        }
        if(!flag){
            compScore += compTurnScore;
        }else {
            compScore = 0;
            textView.setText("Computer Rolled 1");
        }
        compTurnScore = 0;
        updateScore();
        enableButtons();
    }

    private void disableButtons(){
        Button roll = (Button)findViewById(R.id.roll);
        Button hold = (Button)findViewById(R.id.hold);
        Button reset = (Button)findViewById(R.id.reset);
        roll.setEnabled(false);
        hold.setEnabled(false);
        reset.setEnabled(false);
    }

    private void enableButtons(){
        Button roll = (Button)findViewById(R.id.roll);
        Button hold = (Button)findViewById(R.id.hold);
        Button reset = (Button)findViewById(R.id.reset);
        roll.setEnabled(true);
        hold.setEnabled(true);
        reset.setEnabled(true);
    }
}
