package com.islam.quran_quiz;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Random;
import android.content.res.Resources;
import android.widget.TextView;

public class Quran_Data  {
	static ArrayList<String> name = new ArrayList();
    static ArrayList<String> content = new ArrayList();
    static ArrayList<ArrayList<String>> mtshbh = new ArrayList();

    static int currentName;
    static boolean target = false , target_m = false;

    public static void intial(  Resources rsrc) throws FileNotFoundException, IOException {
    	InputStream inputStream = rsrc.openRawResource(R.raw.quran);
    		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        name.add(br.readLine());
        content.add(br.readLine());
        for (int i = 1; i < 114; i++) {
            name.add(br.readLine());
            br.readLine();
            content.add(br.readLine());
        }
        br.close();
        
        mtshbh.add(new ArrayList<String>());
		ObjectInputStream input = new ObjectInputStream(rsrc.openRawResource(R.raw.mtshbh_o));
        try {
            mtshbh = (ArrayList< ArrayList< String>>) input.readObject();
        } catch (ClassNotFoundException ex) {
         
        }
        input.close();
    
    }
    public static void newMtshbh( TextView ask) {
    currentName = new Random().nextInt(mtshbh.size());
    ask.setText(mtshbh.get(currentName).get(0));
    }
    public static void newQues( TextView ask) {
        currentName = new Random().nextInt(114);
        int i = new Random().nextInt(content.get(currentName).length() - 40);
        String keeper = content.get(currentName).substring(i, i + 39);
        while ( keeper.contains("(") || keeper.contains(")")  )
        {
        currentName = new Random().nextInt(114);
        i = new Random().nextInt(content.get(currentName).length() - 40);
        keeper = content.get(currentName).substring(i, i + 39);
        
        }
        ask.setText(keeper);

    }
public static ArrayList<String> mtshbhAns(  TextView num ) {

    ArrayList<String> results = new ArrayList<String>();
num.setText(" ﬂ——  Â–Â «·¬Ì… " + (mtshbh.get(currentName).size()-1) );
    for (int i = 1; i < mtshbh.get(currentName).size(); i++) {
  results.add(mtshbh.get(currentName).get(i) + "\n-----------------");
    }
  
    return results;
 
}
    public static ArrayList<String> quesAns( TextView ask  , TextView num ) {

        ArrayList<String> results = Quran_Data.search(ask.getText().toString());
        num.setText("⁄œœ «·”Ê— " + results.size());
        for (int i = 0; i < results.size(); i++) {
		results.set(i, results.get(i) + "\n-----------------");
		}
        return results;        
       

    }

    public static ArrayList<String> search(String word) {
        ArrayList<String> results = new ArrayList();
        for (int i = 0; i < 114; i++) {
            if (content.get(i).contains(word)) {
                int index = 1;
               while (true) {
                    if (content.get(i).contains(index + "") && content.get(i).contains((index + 1) + "")) {
                        String holder = content.get(i).substring(content.get(i).indexOf(index + ""), content.get(i).indexOf((index + 1) + ""));
                        if (holder.contains(word.substring(1, word.length() - 1))) {
                            results.add( holder + name.get(i).replace("”Ê—…", "").replace("-", ")")  );
                        }
                        index++;
                    } else {
                        break;
                    }

                }

            }

        }

        return results;
    }

}
