package com.example.dell.benahapp.ques;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.benahapp.R;
import com.example.dell.benahapp.user;

import java.util.ArrayList;

public class Student extends AppCompatActivity {
    Question question = new Question();
    ArrayList<Question> questionList = new ArrayList<>();
    ListView listView;
    TextView questionnaireText;
    QuestionAdapter adapter;
    RadioGroup choiceGroup;
    public static ArrayList<Integer> choiceList  = new ArrayList<>();

    public void submit(View v){
        String ids = "user: " + user.userName + "\nid: "+ user.userId + "\n";
        for(int i=0; i<choiceList.size(); i++){
            ids += choiceList.get(i) + "_";
        }
        Toast.makeText(this, ids, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ques_activity_student);

        questionnaireText = (TextView) findViewById(R.id.questionnaireName);
        String questionnaireName =  "Questionnaire Name";
        questionnaireText.setText(questionnaireName);

        question.q = "Did you learn new things from course?";
        question.a = "not at all";
        question.b = "to some extent";
        question.c = "yes";
        question.d = "a lot of things";
        questionList.add(question);
        choiceList.add(-1);

        question = new Question();
        question.q = "How was the instructor";
        question.a = "not cooperating";
        question.b = "less effort";
        question.c = "normal effort";
        question.d = "cooperating";
        questionList.add(question);
        choiceList.add(-1);


        question = new Question();
        question.q = "Does he come in time";
        question.a = "not at all";
        question.b = "to some extent";
        question.c = "yes";
        question.d = "Always";
        questionList.add(question);
        choiceList.add(-1);


        question = new Question();
        question.q = "Was labs helpful";
        question.q = "Does he come in time";
        question.a = "not at all";
        question.b = "to some extent";
        question.c = "yes";
        question.d = "totally";
        questionList.add(question);
        choiceList.add(-1);


        listView = (ListView) findViewById(R.id.listVew);

        adapter = new QuestionAdapter(getApplicationContext(), questionList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // selected item
                choiceGroup = (RadioGroup) view.findViewById(R.id.choiceGroup);
                                Integer selectedId = choiceGroup.getCheckedRadioButtonId();
                choiceList.set(position,selectedId);
            }
        });


    }
}
