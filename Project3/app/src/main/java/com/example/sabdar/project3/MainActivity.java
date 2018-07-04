package com.example.sabdar.project3;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Declare question TextViews
    private TextView questionOne;
    private TextView questionTwo;
    private TextView questionThree;
    private TextView questionFour;
    private TextView questionFive;
    private TextView questionSix;

    //Declare TextInput EditText, Radio Buttons, CheckBoxes, Buttons
    private TextInputEditText answerOne;
    private TextInputEditText answerFour;
    private RadioGroup answerTwo;
    private RadioGroup answerFive;
    private RadioButton answerTwoOptionA;
    private RadioButton answerTwoOptionB;
    private RadioButton answerTwoOptionC;
    private RadioButton answerTwoOptionD;
    private RadioButton answerFiveOptionA;
    private RadioButton answerFiveOptionB;
    private RadioButton answerFiveOptionC;
    private RadioButton answerFiveOptionD;
    private CheckBox answerThreeOptionA;
    private CheckBox answerThreeOptionB;
    private CheckBox answerThreeOptionC;
    private CheckBox answerThreeOptionD;
    private CheckBox answerSixOptionA;
    private CheckBox answerSixOptionB;
    private CheckBox answerSixOptionC;
    private CheckBox answerSixOptionD;
    private Button submitButton;
    //Declare questions list
    private List<String> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initQuestions();

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.submitButton:
                validateQuiz();
                break;
            default:
                break;
        }
    }
    //Validate the selected answers and display result
    private void validateQuiz() {
        int score = 0;
        String answer1 = answerOne.getText().toString();
        int answer2 = answerTwo.getCheckedRadioButtonId();
        boolean answer3 = answerThreeOptionA.isChecked() && answerThreeOptionD.isChecked() && !answerThreeOptionB.isChecked() && !answerThreeOptionC.isChecked();
        String answer4 = answerFour.getText().toString();
        int answer5 = answerFive.getCheckedRadioButtonId();
        boolean answer6 = answerSixOptionA.isChecked() && answerSixOptionB.isChecked() && answerSixOptionD.isChecked() && !answerSixOptionC.isChecked();
        if (answer1.equalsIgnoreCase(getString(R.string.answerOne))) {
            score++;
        }
        if (answer2 == answerTwoOptionA.getId()) {
            score++;
        }
        if (answer3) {
            score++;
        }
        if (answer4.equalsIgnoreCase(getString(R.string.answerFour))) {
            score++;
        }
        if (answer5 == answerFiveOptionD.getId()) {
            score++;
        }
        if (answer6) {
            score++;
        }
        String result = getString(R.string.result, score, questionList.size());
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }

    //Initialize TextViews, InputEditText, RadioGroup, RadioButtons, Checkboxes
    private void initViews() {
        questionOne = findViewById(R.id.questionOneTV);
        questionTwo = findViewById(R.id.questionTwoTV);
        questionThree = findViewById(R.id.questionThreeTV);
        questionFour = findViewById(R.id.questionFourTV);
        questionFive = findViewById(R.id.questionFiveTV);
        questionSix = findViewById(R.id.questionSixTV);

        answerOne = findViewById(R.id.answerOneTIET);
        answerFour = findViewById(R.id.answerFourTIET);

        answerTwo = findViewById(R.id.answerTwoRG);
        answerTwoOptionA = findViewById(R.id.answerTwoRB1);
        answerTwoOptionB = findViewById(R.id.answerTwoRB2);
        answerTwoOptionC = findViewById(R.id.answerTwoRB3);
        answerTwoOptionD = findViewById(R.id.answerTwoRB4);

        answerThreeOptionA = findViewById(R.id.answerThreeCB1);
        answerThreeOptionB = findViewById(R.id.answerThreeCB2);
        answerThreeOptionC = findViewById(R.id.answerThreeCB3);
        answerThreeOptionD = findViewById(R.id.answerThreeCB4);

        answerFive = findViewById(R.id.answerFiveRG);
        answerFiveOptionA = findViewById(R.id.answerFiveRB1);
        answerFiveOptionB = findViewById(R.id.answerFiveRB2);
        answerFiveOptionC = findViewById(R.id.answerFiveRB3);
        answerFiveOptionD = findViewById(R.id.answerFiveRB4);

        answerSixOptionA = findViewById(R.id.answerSixCB1);
        answerSixOptionB = findViewById(R.id.answerSixCB2);
        answerSixOptionC = findViewById(R.id.answerSixCB3);
        answerSixOptionD = findViewById(R.id.answerSixCB4);

        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(this);

    }
    //Initialize Quiz Questions List & display Questions
    private void initQuestions() {
        questionList = Arrays.asList(getResources().getStringArray(R.array.questions));
        questionOne.setText(questionList.get(0));
        questionTwo.setText(questionList.get(1));
        questionThree.setText(questionList.get(2));
        questionFour.setText(questionList.get(3));
        questionFive.setText(questionList.get(4));
        questionSix.setText(questionList.get(5));

        List<String> optionsTwoList = Arrays.asList(getResources().getStringArray(R.array.qTwoOptions));
        answerTwoOptionA.setText(optionsTwoList.get(0));
        answerTwoOptionB.setText(optionsTwoList.get(1));
        answerTwoOptionC.setText(optionsTwoList.get(2));
        answerTwoOptionD.setText(optionsTwoList.get(3));

        List<String> optionsThreeList = Arrays.asList(getResources().getStringArray(R.array.qThreeOptions));
        answerThreeOptionA.setText(optionsThreeList.get(0));
        answerThreeOptionB.setText(optionsThreeList.get(1));
        answerThreeOptionC.setText(optionsThreeList.get(2));
        answerThreeOptionD.setText(optionsThreeList.get(3));

        List<String> optionsFiveList = Arrays.asList(getResources().getStringArray(R.array.qFiveOptions));
        answerFiveOptionA.setText(optionsFiveList.get(0));
        answerFiveOptionB.setText(optionsFiveList.get(1));
        answerFiveOptionC.setText(optionsFiveList.get(2));
        answerFiveOptionD.setText(optionsFiveList.get(3));

        List<String> optionsSixList = Arrays.asList(getResources().getStringArray(R.array.qSixOptions));
        answerSixOptionA.setText(optionsSixList.get(0));
        answerSixOptionB.setText(optionsSixList.get(1));
        answerSixOptionC.setText(optionsSixList.get(2));
        answerSixOptionD.setText(optionsSixList.get(3));

    }


}
