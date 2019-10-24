package com.example.rishabh.card24;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rishabh.card24.R;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.rishabh.card24.MESSAGE";
    Button startButton;
    public void sendMessage(View view) {
        Intent intent = new Intent(this, GamePage.class);
        EditText editText = (EditText) findViewById(R.id.target);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = (Button) findViewById(R.id.start);
        startButton.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view){
                sendMessage(view);
            }
        });
    }
}
