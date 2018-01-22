package com.example.jasmin.japanesememory;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class JapaneseMemory extends AppCompatActivity implements View.OnClickListener {

    Character[] signs;
    Button b, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11 ;
    ButtonMap bm;
    ViewGroup view;
   Character sign1, sign2;
    int counterDisplayed, counterEmpty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_japanese_memory);
        bm = new ButtonMap();
        signs = new Character[12];
        view = findViewById(android.R.id.content);
        signs[0] = 'ろ';
        signs[1] = 'ろ';
        signs[2] = 'き';
        signs[3] = 'き';
        signs[4] = 'た';
        signs[5] = 'た';
        signs[6] = 'ま';
        signs[7] = 'ま';
        signs[8] = 'は';
        signs[9] = 'は';
        signs[10] = 'れ';
        signs[11] = 'れ';
        counterDisplayed = 0;
        counterEmpty=0;
        System.out.println("counterhh" + counterDisplayed);
        b = findViewById(R.id.button);
        b1 = findViewById(R.id.button2);
        b2 = findViewById(R.id.button3);
        b3 = findViewById(R.id.button4);
        b4 = findViewById(R.id.button5);
        b5 = findViewById(R.id.button6);
        b6 = findViewById(R.id.button7);
        b7 = findViewById(R.id.button8);
        b8 = findViewById(R.id.button9);
        b9 = findViewById(R.id.button10);
        b10 = findViewById(R.id.button11);
        b11 = findViewById(R.id.button12);
        instantiate(view);
    }

    public void instantiate(ViewGroup view) {
        int counter = 0;
        System.out.println(view.getChildCount() + "");
        for (int i = 0; i < view.getChildCount(); i++) {
        System.out.println("Ja");
        View v = view.getChildAt(i);
        System.out.println("ID "+ v.getId());
        if (v instanceof Button) {
            Button b = (Button) v;
            bm.put(b,signs[counter]);
            b.setOnClickListener(this);
            System.out.println("Here" + bm.get(b).toString());
            if (counter == 11){
                break;
            }
            else{
                counter ++;
            }
        }
        else if (v instanceof ViewGroup) {

            this.instantiate((ViewGroup) v);
        }
    }
}

    public void check(){

    if(counterDisplayed<2) {
counterDisplayed = 0;
counterEmpty = 0;
        for (Button b : bm.keySet()) {
            if(b.getText().toString().equals("Neues Spiel") || (b.isEnabled()== false)){
                continue;
            }
            if (b.getText().toString().equals("")) {
                counterEmpty++;
                continue;
            } else if(!(b.getText().toString()=="")) {
                counterDisplayed++;
            }
        }
    }
    }
    @Override
    public void onClick(View v) {
        System.out.println("counterJetzt" + counterDisplayed);
        System.out.println("counterEmpty" + counterEmpty);
        if (v instanceof Button) {
            Button b = (Button) v;
            if(counterDisplayed<=1){
                if(b.getText()==""){
                    b.setText(bm.get(b).toString());
                    b.setBackgroundColor(getResources().getColor(R.color.primaryColor));
                    if(counterDisplayed==1){
                        System.out.println("Now");
                        sign2 = bm.get(b);
                        System.out.print(sign2);
                        System.out.print(sign1);
                        if(sign2.compareTo(sign1)== 0){
                            System.out.println("Here");
                            win(bm.getKeyByValue(sign1));
                        }
                    }
                    else
                    {
                        sign1 = (Character) bm.get(b);
                        System.out.println("SIGN" + sign1);
                    }
                }
                else{
                    b.setText("");
                    b.setBackgroundColor(getResources().getColor(R.color.secondaryTextColor));
                }
            }
            else if(counterDisplayed>1 && (!(b.getText().equals("")))) {
                b.setText("");
                b.setBackgroundColor(getResources().getColor(R.color.secondaryTextColor));
                if(counterDisplayed>2){
                    counterDisplayed = 2;
                }
                counterDisplayed--;
            }
            else{
                Toast.makeText(this, "Du hast schon 2 Karten aufgedeckt", Toast.LENGTH_SHORT).show();
            }

        }
        check();
        System.out.println("counterJetzt" + counterDisplayed);
        System.out.println("counterEmpty" + counterEmpty);
    }

    public void win(Set<Button> bs){
        System.out.print("YAY");
       Iterator<Button> it = bs.iterator();
        while(it.hasNext()){
            Button bu = it.next();
            bu.setBackgroundColor(getResources().getColor(R.color.secondaryDarkColor));
            bu.setTextColor(getResources().getColor(R.color.secondaryTextColor));
            bu.setEnabled(false);
        }
    }

    public void newGame(View aView){
        bm = bm.randomize();
        for (Button b : bm.keySet()) {
        b.setEnabled(true);
        b.setText("");
        b.setBackgroundColor(getResources().getColor(R.color.secondaryTextColor));
        b.setTextColor(getResources().getColor(R.color.secondaryColor));
        }
    }
}
