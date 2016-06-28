package com.zattack.scarnesdice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static int yourScore = 0;
    public static int compScore = 0;
    public static int yourTurnScore = 0;
    public static int compTurnScore = 0;

    TextView textView = (TextView)findViewById(R.id.gameStatus);

    private Random r = new Random();

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

    private int getDiceValue(){
        int diceValue = r.nextInt(6)+1;
        String img = "dice" + Integer.toString(diceValue);
        int resourceId = this.getResources().getIdentifier(img, "drawable", getPackageName());
        //Drawable drawable = getDrawable(resourceId);
        ImageView imageView = (ImageView)findViewById(R.id.diceImage);
        imageView.setImageResource(resourceId);
        return diceValue;
    }

    public void rollDice(View view){
        int value = getDiceValue();
        textView.setText("Player Rolled" + value);
        if(value!=1){
            yourTurnScore+=value;
        }else {
            textView.setText("Rolled 1");
            yourTurnScore=1;
        }
    }

    public void holdDice(View view){
        textView.setText("Hold Score");
           
    }
}
