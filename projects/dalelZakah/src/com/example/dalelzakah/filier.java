package com.example.dalelzakah;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import android.app.Activity;
import android.util.Log;

public class filier{

	
    public static final String POOR = "›ﬁÌ—";
    public static final String NEED = "„Õ «Ã";
    private final static String needName = "need1.txt";
    private final static String givenName = "given1.txt";
	private String TAG = "filier";
    
    public  void readData(Activity act) throws  IOException {
       
 
		
		if (!act.getFileStreamPath(needName).exists()) {
				FileOutputStream fos;
			try {
				fos = act.openFileOutput(needName, act.MODE_PRIVATE);
				PrintWriter pw = new PrintWriter(new BufferedWriter(		
						new OutputStreamWriter(fos)));
				pw.println("");
				pw.println("0");
				pw.close();
			} catch (FileNotFoundException e) {
					}	
		
			
		}


		if (!act.getFileStreamPath(givenName).exists()) {
			FileOutputStream fos;
			try {
				fos = act.openFileOutput(givenName, act.MODE_PRIVATE);
				PrintWriter pw = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(fos)));
				pw.println("");
				pw.println("0");
				pw.close();
			} catch (FileNotFoundException e) {	 	
		
			}
			
		}
    	        

    	ArrayList<poor> thePoor = new ArrayList<poor>();
    	FileInputStream fis = act.openFileInput(needName);
    	BufferedReader br = new BufferedReader(new InputStreamReader(fis));
    	br.readLine(); // this for the  dot in the start of the notepad
    	int count = Integer.parseInt(br.readLine());
        for (int j = 0; j < count; j++) {
            poor people = new poor(br.readLine(), br.readLine().equals("1"));
            String temp = br.readLine();
            while (!temp.equals("=====")) {
                people.addGiven(temp);
                temp = br.readLine();
            }
            thePoor.add(people);
        }
        br.close();
        FileInputStream fis2 = act.openFileInput(givenName);
        br = new BufferedReader(new InputStreamReader(fis2));
        ArrayList<String> givens = new ArrayList<String>();
        br.readLine();
        count = Integer.parseInt(br.readLine());
        for (int i = 0; i < count; i++) {
            givens.add(br.readLine());
        }
        br.close();
        packagePoor.setThePoor(thePoor);
        packagePoor.setGlobalGiven(givens);
    }

    public void writeData(ArrayList<poor> thePoor, ArrayList<String> givens , Activity act) throws FileNotFoundException  {

    	

		FileOutputStream fos = act.openFileOutput(needName, act.MODE_PRIVATE);

		PrintWriter pw = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(fos)));

        pw.println("");
        pw.println(thePoor.size() + "");
        for (int i = 0; i < thePoor.size(); i++) {
        	pw.println(thePoor.get(i).getName());
        	pw.println(thePoor.get(i).getState().equals(POOR) ? "1" : "0");
            for (int j = 0; j < thePoor.get(i).getGiven().size(); j++) {
            	pw.println(thePoor.get(i).getGiven().get(j));
            }
          pw.println("=====");
          
        }

        pw.close();

        FileOutputStream fos2 = act.openFileOutput(givenName, act.MODE_PRIVATE);

		PrintWriter pw2 = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(fos2)));
        pw2.println("");
        pw2.println(givens.size() + "");
        for (int i = 0; i < givens.size(); i++) {
        	pw2.println(givens.get(i));
            
        }
       pw2.close();

    }

}
