package com.example.oyla.ui;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.oyla.R;

import java.util.Objects;
import java.util.Random;

public class Games extends AppCompatActivity {

    protected void createProblem(TextView x){
        int a, c, e;
        char b, d;
        Random random = new Random();
        a = random.nextInt(9)+1;
        if(random.nextInt(3) == 0){
            b = '+';
        }else if(random.nextInt(3) == 1){
            b = '*';
        }else{
            b = '-';
        }
        c = random.nextInt(9)+1;
        if(random.nextInt(3) == 0){
            d = '+';
        }else if(random.nextInt(3) == 1){
            d = '*';
        }else{
            d = '-';
        }
        e = random.nextInt(9)+1;
        int n;
        if(b == '+'){
            if(d == '+'){
                n = a+c+e;
            }else if(d == '-'){
                n = a+c-e;
            }else{
                n = a+c*e;
            }
        }else if(b == '-'){
            if(d == '+'){
                n = a-c+e;
            }else if(d == '-'){
                n = a-c-e;
            }else{
                n = a-c*e;
            }
        }else{
            if(d == '+'){
                n = a*c+e;
            }else if(d == '-'){
                n = a*c-e;
            }else{
                n = a*c*e;
            }
        }
        if(n<0){
            b = '+';
            d = '+';
        }
        x.setText(String.valueOf(a)+String.valueOf(b)+String.valueOf(c)+String.valueOf(d)+String.valueOf(e));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final int[] count = {0};
        setContentView(R.layout.activity_games);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button button0 = findViewById(R.id.button0);
        Button buttonDel = findViewById(R.id.buttonBack);
        Button input = findViewById(R.id.buttonEnter);
        button1.setText("1");
        button2.setText("2");
        button3.setText("3");
        button4.setText("4");
        button5.setText("5");
        button6.setText("6");
        button7.setText("7");
        button8.setText("8");
        button9.setText("9");
        button0.setText("0");
        buttonDel.setText("Del");
        input.setText("Enter");
        TextView problem = findViewById(R.id.text_problem);
        TextView timer = findViewById(R.id.text_timer);
        createProblem(problem);
        TextView num = findViewById(R.id.num);
        TextView scorer = findViewById(R.id.textScore);
        num.setText("0");
        final Boolean[] isDone = {false};
        new CountDownTimer(10000, 1000) {
            String s;
            public void onTick(long millisUntilFinished) {
                s = ((millisUntilFinished+1000) / 1000) + " seconds remaining";
                timer.setText(s);
            }
            public void onFinish() {
                isDone[0] = true;
                timer.setText("Done");
                setResult(Math.max(count[0], getIntent().getIntExtra("score", 0)));
                finish();
            }
        }.start();
        if(!isDone[0]) {
            input.setOnClickListener(new View.OnClickListener() {
                private int findValueOf(String x) {
                    int a = Integer.parseInt(String.valueOf(x.charAt(0)));
                    int b = x.charAt(1);
                    int c = Integer.parseInt(String.valueOf(x.charAt(2)));
                    int d = x.charAt(3);
                    int e = Integer.parseInt(String.valueOf(x.charAt(4)));
                    if(b == '+'){
                        if(d == '+'){
                            return a+c+e;
                        }else if(d == '-'){
                            return a+c-e;
                        }else{
                            return a+c*e;
                        }
                    }else if(b == '-'){
                        if(d == '+'){
                            return a-c+e;
                        }else if(d == '-'){
                            return a-c-e;
                        }else{
                            return a-c*e;
                        }
                    }else{
                        if(d == '+'){
                            return a*c+e;
                        }else if(d == '-'){
                            return a*c-e;
                        }else{
                            return a*c*e;
                        }
                    }
                }
                @Override
                public void onClick(View v) {
                    if(Integer.parseInt(num.getText().toString()) == findValueOf(problem.getText().toString())){
                        count[0]++;
                        scorer.setText("Score : " + count[0]);
                        createProblem(problem);
                    }else{
                    }
                    num.setText(String.valueOf(0));


                }
            });
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (num.getText().toString().equals("0")) {
                        num.setText("1");
                    } else {
                        num.setText(num.getText() + "1");
                    }
                }
            });
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (num.getText().toString().equals("0")) {
                        num.setText("2");
                    } else {
                        num.setText(num.getText() + "2");
                    }
                }
            });
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (num.getText().toString().equals("0")) {
                        num.setText("3");
                    } else {
                        num.setText(num.getText() + "3");
                    }
                }

            });
            button4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (num.getText().toString().equals("0")) {
                        num.setText("4");
                    } else {
                        num.setText(num.getText() + "4");
                    }
                }
            });
            button5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (num.getText().toString().equals("0")) {
                        num.setText("5");
                    } else {
                        num.setText(num.getText() + "5");
                    }
                }
            });
            button6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (num.getText().toString().equals("0")) {
                        num.setText("6");
                    } else {
                        num.setText(num.getText() + "6");
                    }
                }
            });
            button7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (num.getText().toString().equals("0")) {
                        num.setText("7");
                    } else {
                        num.setText(num.getText() + "7");
                    }
                }
            });
            button8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (num.getText().toString().equals("0")) {
                        num.setText("8");
                    } else {
                        num.setText(num.getText() + "8");
                    }
                }
            });
            button9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (num.getText().toString().equals("0")) {
                        num.setText("9");
                    } else {
                        num.setText(num.getText() + "9");
                    }
                }
            });
            button0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (num.getText().toString().equals("0")) {
                        num.setText("0");
                    } else {
                        num.setText(num.getText() + "0");
                    }
                }
            });
            buttonDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    num.setText("0");
                }
            });
        }

    }
}