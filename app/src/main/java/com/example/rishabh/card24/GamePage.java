package com.example.rishabh.card24;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rishabh.card24.R;
import com.singularsys.jep.EvaluationException;
import com.singularsys.jep.Jep;
import com.singularsys.jep.ParseException;

import java.util.Random;

public class GamePage extends AppCompatActivity {
    ImageButton[] cards;
    Button rePick;
    Button checkInput;
    Button clear;
    Button left;
    Button right;
    Button plus;
    Button minus;
    Button multiply;
    Button divide;
    TextView expression;
    int[] data;
    int[] card;
    int[] imageCount;
    int cardsClicked;
    int targetValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);
        Intent intent = getIntent();
        String message =  ((Intent) intent).getStringExtra(MainActivity.EXTRA_MESSAGE);
        targetValue = Integer.parseInt(message);
        rePick = (Button)findViewById(R.id.repick); checkInput = (Button)findViewById(R.id.checkinput); left = (Button)findViewById(R.id.left);
        right = (Button)findViewById(R.id.right);
        plus = (Button)findViewById(R.id.plus);
        minus = (Button)findViewById(R.id.minus);
        multiply = (Button)findViewById(R.id.multiply); divide = (Button)findViewById(R.id.divide);
        clear = (Button)findViewById(R.id.clear);
        expression = (TextView)findViewById(R.id.input);
        String textbox = "Please form an expression such that the result is " + targetValue +".";
        expression.setHint(textbox);
        imageCount = new int[4];
        cards = new ImageButton[4];
        cards[0]= (ImageButton) findViewById(R.id.card1);
        cards[1]= (ImageButton) findViewById(R.id.card2);
        cards[2]= (ImageButton) findViewById(R.id.card3);
        cards[3]= (ImageButton) findViewById(R.id.card4);
        initCardImage();
        pickCard();
        cards[0].setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View view) {
                String exp = expression.getText().toString();
                if(exp.length() == 0)
                    clickCard(0);
                else if( exp.charAt(exp.length()-1) == '+' || exp.charAt(exp.length()-1) == '('
                        ||  exp.charAt(exp.length()-1) == '*' || exp.charAt(exp.length()-1) == '-'
                        || exp.charAt(exp.length()-1) == '/')
                    clickCard(0);
            }
        });
        cards[1].setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View view) {
                String exp = expression.getText().toString();
                if(exp.length() == 0)
                    clickCard(1);
                else if( exp.charAt(exp.length()-1) == '+' || exp.charAt(exp.length()-1) == '('
                        ||  exp.charAt(exp.length()-1) == '*' || exp.charAt(exp.length()-1) == '-'
                        || exp.charAt(exp.length()-1) == '/')
                    clickCard(1);
            }
        });
        cards[2].setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View view) {
                String exp = expression.getText().toString();
                if(exp.length() == 0)
                    clickCard(2);
                else if( exp.charAt(exp.length()-1) == '+' || exp.charAt(exp.length()-1) == '('
                        ||  exp.charAt(exp.length()-1) == '*' || exp.charAt(exp.length()-1) == '-'
                        || exp.charAt(exp.length()-1) == '/')
                    clickCard(2);
            }
        });

        cards[3].setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View view) {
                String exp = expression.getText().toString();
                if(exp.length() == 0)
                    clickCard(3);
                else if( exp.charAt(exp.length()-1) == '+' || exp.charAt(exp.length()-1) == '('
                        ||  exp.charAt(exp.length()-1) == '*' || exp.charAt(exp.length()-1) == '-'
                        || exp.charAt(exp.length()-1) == '/')
                    clickCard(3);
            }
        });

        left.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view) {
                String exp = expression.getText().toString();
                if(exp.length() == 0)
                    expression.append("(");
                else if( exp.charAt(exp.length()-1) == '+' || exp.charAt(exp.length()-1) == '('
                        ||  exp.charAt(exp.length()-1) == '*' || exp.charAt(exp.length()-1) == '-'
                        || exp.charAt(exp.length()-1) == '/')
                    expression.append("(");
            }
        });

        right.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view) {
                String exp = expression.getText().toString();
                //Check for length not =0 and if last character is a digit or a closing bracket
                if(exp.length()>0 && (exp.charAt(exp.length()-1) == ')' || ((int)(exp.charAt(exp.length()-1))>=48 && (int)(exp.charAt(exp.length()-1))<=57)))
                    expression.append(")");
            }
        });
        plus.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view) {
                String exp = expression.getText().toString();
                System.out.println((int)(exp.charAt(exp.length()-1)));
                //Check for length not =0 and if last character is a digit or a closing bracket
                if(exp.length()>0 && (exp.charAt(exp.length()-1) == ')' || ((int)(exp.charAt(exp.length()-1))>=48 && (int)(exp.charAt(exp.length()-1))<=57)))
                    expression.append("+");
            }
        });
        minus.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view) {
                String exp = expression.getText().toString();
                //Check for length not =0 and if last character is a digit or a closing bracket
                if(exp.length()>0 && (exp.charAt(exp.length()-1) == ')' || ((int)(exp.charAt(exp.length()-1))>=48 && (int)(exp.charAt(exp.length()-1))<=57)))
                    expression.append("-");
            }
        });
        multiply.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view) {
                String exp = expression.getText().toString();
                //Check for length not =0 and if last character is a digit or a closing bracket
                if(exp.length()>0 && (exp.charAt(exp.length()-1) == ')' || ((int)(exp.charAt(exp.length()-1))>=48 && (int)(exp.charAt(exp.length()-1))<=57)))
                    expression.append("*");
            }
        });
        divide.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view) {
                String exp = expression.getText().toString();
                //Check for length not =0 and if last character is a digit or a closing bracket
                if(exp.length()>0 && (exp.charAt(exp.length()-1) == ')' || ((int)(exp.charAt(exp.length()-1))>=48 && (int)(exp.charAt(exp.length()-1))<=57)))
                    expression.append("/");
            }
        });
        clear.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view) {
                setClear();
            }
        });
        checkInput.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                if(cardsClicked==4) {
                    String inputStr = expression.getText().toString();
                    if (checkInput(inputStr)) {
                        Toast.makeText(GamePage.this, "Correct Answer", Toast.LENGTH_SHORT).show();
                        pickCard();
                    } else {
                        Toast.makeText(GamePage.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
                        setClear();
                    }
                }
            }
        });
    }

    private void initCardImage(){
        for(int i =0; i<4; i++){
            int resID = getResources().getIdentifier("back_0", "drawable", "com.android.card24game");
            cards[i].setImageResource(resID);
        }
    }

    private void pickCard(){
        data = new int[4];
        card = new int[4];
        final int random1 = new Random().nextInt(52) + 1;
        final int random2 = new Random().nextInt(52) + 1;
        final int random3 = new Random().nextInt(52) + 1;
        final int random4 = new Random().nextInt(52) + 1;
        card[0]=random1; card[1]=random2; card[2]=random3; card[3]=random4;
        data[0]=random1; data[1]=random2; data[2]=random3; data[3]=random4;
        setClear();
    }

    private void setClear(){
        int resID;
        expression.setText("");
        for (int i = 0; i < 4; i++) {
            imageCount[i] = 0;
            resID = getResources().getIdentifier("card"+card[i],"drawable", "com.example.rishabh.card24");
            cards[i].setImageResource(resID);
            cards[i].setClickable(true);
        }
        cardsClicked=0;
    }

    private void clickCard(int i){
        int resId;
        String num;
        Integer value;
        if(imageCount[i] ==0){
            resId = getResources().getIdentifier("back_0","drawable", "com.example.rishabh.card24");
            cards[i].setImageResource(resId);
            cards[i].setClickable(false);
            value = data[i];
            num = value.toString();
            expression.append(num);
            imageCount[i] ++;
        }
        cardsClicked +=1;

    }

    private boolean checkInput(String input) {
        Jep jep = new Jep();
        Object res;
        try {
            jep.parse(input);
            res = jep.evaluate();
        } catch (ParseException e) {
            e.printStackTrace();
            Toast.makeText(GamePage.this, "Wrong Expression", Toast.LENGTH_SHORT).show();
            return false;
        } catch (EvaluationException e) {
            e.printStackTrace();
            Toast.makeText(GamePage.this, "Wrong Expression", Toast.LENGTH_SHORT).show();
            return false;
        }
        Double ca = (Double) res;
        if (Math.abs(ca - targetValue) < 1e-6)
            return true;
        return false;
    }


}
