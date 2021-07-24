package com.english_quiz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.content.res.Resources;
import android.widget.Button;
import android.widget.TextView;

public class Words_Data {

    public static void intial(Resources rsrc) throws IOException {
    	InputStream inputStream = rsrc.openRawResource(R.raw.words);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        br.readLine();
        int lines = Integer.parseInt(br.readLine().trim());
       
        for (int i = 0; i < lines; i++) {
        	String speak = br.readLine();
        	english.add(speak.split("•")[0]);
        	arabic.add(speak.split("•")[1]);
	

        }
        br.close();
    
    }
    
    
    	public static void QuizEnd()
    	{
    	  clock.cancel();
    	}
    	public static String getScore()
    		{
			return (Math.round(score / (double) (count - 1) * 100.0) + " % ") ;
    		}
    	public static ArrayList<String> getMistakes() {
      
    		ArrayList<String > man = new ArrayList<String>();
      
    		for (int i = 0; i < mistakes.size(); i++) {
            man.add(english.get(mistakes.get(i)) + "---------" + arabic.get(mistakes.get(i)) + "\n");
    		}
    		return man;
    		}

    	public static void clear() {
        old.clear();
        mistakes.clear();
        score = 0;
        count = 0;
        seconds = 0;
        clock.cancel();
        clock = new Timer();
    }

   
    public static int newQues(TextView time , TextView title, TextView ques, Button yes, Button no) {

        int i = newInt();
        if (i	== -1) {
			return i;
		}
        else{
        	yes.setVisibility(android.view.View.INVISIBLE);
		    no.setVisibility(android.view.View.INVISIBLE);
        	count++;
        	title.setText(count + " - what the meaning of:-");
        	old.add(i);
        	ques.setText(english.get(i));
        
        }
        return i;	
    }

    
    
    public static int newInt() {
        int i = 0;
        if (old.size() == english.size()) {
        	return -1;            
        } else {

            while (true) {

                i = new Random().nextInt(english.size());
                boolean check = true;
                for (int j = 0; j < old.size(); j++) {
                    if (old.get(j).equals(i)) {
                        check = false;
                        break;
                    }
                }
                if (check) {

                    break;
                }

            }
        }
        return i;
    }





    static ArrayList<String> english = new ArrayList();
    static ArrayList<String> arabic = new ArrayList();
    static ArrayList<Integer> old = new ArrayList();
    static ArrayList<Integer> mistakes = new ArrayList();
    static int score, count, seconds , ticks = 10;
    static Timer clock = new Timer();

    }


