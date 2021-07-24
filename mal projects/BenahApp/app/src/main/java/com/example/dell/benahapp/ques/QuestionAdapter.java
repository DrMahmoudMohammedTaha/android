package com.example.dell.benahapp.ques;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.dell.benahapp.R;

import java.util.ArrayList;


public class QuestionAdapter extends ArrayAdapter<Question> {


    public QuestionAdapter(Context context, ArrayList<Question> questionList) {
        super(context, 0, questionList);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Question question = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.question_list, parent, false);
        }

        // Lookup view for data population
        TextView qText = (TextView) convertView.findViewById(R.id.qText);
        RadioButton aRadio = (RadioButton) convertView.findViewById(R.id.aRadio);
        RadioButton bRadio = (RadioButton)convertView.findViewById(R.id.bRadio);
        RadioButton cRadio = (RadioButton)convertView.findViewById(R.id.cRadio);
        RadioButton dRadio = (RadioButton)convertView.findViewById(R.id.dRadio);





        // Populate the data into the template view using the data object
        qText.setText(question.q);
        aRadio.setText(question.a);
        bRadio.setText(question.b);
        cRadio.setText(question.c);
        dRadio.setText(question.d);


        // Return the completed view to render on screen
        return convertView;
    }

}
