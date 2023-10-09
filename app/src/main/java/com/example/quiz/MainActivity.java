package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class MainActivity extends AppCompatActivity {
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private TextView questionTextView;
    private int currentIndex = 0;
    private int score = 0;

    private Question[] questions = new Question[] {
            new Question(R.string.question1, true),
            new Question(R.string.question2, true),
            new Question(R.string.question3, false),
            new Question(R.string.question4, true),
            new Question(R.string.question5, false),
            new Question(R.string.question6, true),
            new Question(R.string.question7, false),
            new Question(R.string.question8, false),
            new Question(R.string.question9, true),
            new Question(R.string.question10, true)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        questionTextView = findViewById(R.id.question_text_view);

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerCorrectness(true);
                currentIndex = (currentIndex + 1) % questions.length;
                setNextQuestion();
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswerCorrectness(false);
                currentIndex = (currentIndex + 1) % questions.length;
                setNextQuestion();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkResult();
                currentIndex = (currentIndex + 1) % questions.length;
                setNextQuestion();
            }
        });

        setNextQuestion();
    }

    private void checkAnswerCorrectness(boolean userAnswer) {
        boolean correctAnswer = questions[currentIndex].isTrueAnswer();
        int resultMessageId = 0;

        if(userAnswer == correctAnswer) {
            resultMessageId = R.string.correct_answer;
            score++;
        } else {
            resultMessageId = R.string.incorrect_answer;
        }

        Toast.makeText(this, resultMessageId, Toast.LENGTH_SHORT).show();
        checkResult();
    }

    private void setNextQuestion() {
        questionTextView.setText(questions[currentIndex].getQuestionId());
    }

    private void checkResult() {
        if(currentIndex + 1 == questions.length) {
            showResult();
        }
    }
    private void showResult() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Twój wynik to " + score + " pkt.");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                score = 0;
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}