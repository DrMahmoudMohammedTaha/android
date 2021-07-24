package com.example.zekra3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.regex.PatternSyntaxException;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.Layout;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Hdith extends Activity{
	
	 public static final String HigriMonth[] = {"محرم", "صفر", "ربيع اول", "ربيع اخر", "جمادى اول", "جمادى اخر", "رجب", "شعبان", "رمضان", "شوال", "ذى القعدة", "ذى الحجة"};
	 public static final String name[] = {"muslim", "bokhari", "trmzi", "nesaai", "maga", "dawd", "moataa", "saleh"};
	 public static final String title[] = {"مسلم", "البخارى", "الترمذى", "النسائى", "ابن ماجه", "ابن داود", "الموطأ", "رياض الصالحين"};
	 static ArrayList<String> zekr = new ArrayList();
	 static String important = "";
	
	
	 public static String getSiam() {

	        String dayName = new SimpleDateFormat("EEEE").format(new java.util.Date());
	        int dayno = Integer.parseInt(getHigriDate().split("-")[2]);
	        String message = "استعد للصيام غدا ان شاء الله \n";
	        if (((dayName.equals("Sunday") || dayName.contains("حد")) && compareDate(getFajrTime())) || ((dayName.equals("Monday") || dayName.contains("ثنين")) && !compareDate(getFajrTime()))) {
	            message += "يوم الاثنين\n";
	        } else if (((dayName.equals("Wednesday") || dayName.contains("ربعاء")) && compareDate(getFajrTime())) || ((dayName.equals("Thursday") || dayName.contains("خميس")) && !compareDate(getFajrTime()))) {
	            message += "يوم الخميس\n";
	        }
	        if ((dayno == 11 || dayno == 12 || dayno == 13 || dayno == 14) && compareDate(getFajrTime())) {
	            message += "ربما يكون غدا " + (dayno + 1) + " او " + (dayno + 2) + " " + HigriMonth[Integer.parseInt(getHigriDate().split("-")[1]) - 1];
	        } else if ((dayno == 12 || dayno == 13 || dayno == 14 || dayno == 15) && !compareDate(getFajrTime())) {
	            message += "ربما يكون غدا " + (dayno) + " او " + (dayno + 1) + " " + HigriMonth[Integer.parseInt(getHigriDate().split("-")[1]) - 1];
	        }

	        if (message.equals("استعد للصيام غدا ان شاء الله \n")) {
	            return "";
	        } else {
	            return message;
	        }
	    }


	 public static String getFajrTime() {

	        final int timeZone = 2;
	        final double longitude = 30.2, latitude = 30, fajrTwilight = -19.5;
	        int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(new java.util.Date())), month = Integer.parseInt(new SimpleDateFormat("MM").format(new java.util.Date())), day = Integer.parseInt(new SimpleDateFormat("dd").format(new java.util.Date())), hours = Integer.parseInt(new SimpleDateFormat("HH").format(new java.util.Date())), minutes = Integer.parseInt(new SimpleDateFormat("mm").format(new java.util.Date()));
	        double fajrTime, zuhrTime;

	        double D = (367 * year) - ((year + (int) ((month + 9) / 12)) * 7 / 4) + (((int) (275 * month / 9)) + day - 730531.5);
	        double L = 280.461 + 0.9856474 * D;
	        L = moreLess360(L);
	        double M = 357.528 + (0.9856003) * D;
	        M = moreLess360(M);
	        double Lambda = L + 1.915 * Math.sin(degToRad(M)) + 0.02 * Math.sin(degToRad(2 * M));
	        Lambda = moreLess360(Lambda);
	        double Obliquity = 23.439 - 0.0000004 * D;
	        double Alpha = radToDeg(Math.atan((Math.cos(degToRad(Obliquity)) * Math.tan(degToRad(Lambda)))));
	        Alpha = moreLess360(Alpha);
	        Alpha = Alpha - (360 * (int) (Alpha / 360));
	        Alpha = Alpha + 90 * (Math.floor(Lambda / 90) - Math.floor(Alpha / 90));
	        double ST = 100.46 + 0.985647352 * D;
	        double Dec = radToDeg(Math.asin(Math.sin(degToRad(Obliquity)) * Math.sin(degToRad(Lambda))));
	        double Durinal_Arc = radToDeg(Math.acos((Math.sin(degToRad(-0.8333)) - Math.sin(degToRad(Dec)) * Math.sin(degToRad(latitude))) / (Math.cos(degToRad(Dec)) * Math.cos(degToRad(latitude)))));
	        double Noon = Alpha - ST;
	        Noon = moreLess360(Noon);
	        double UT_Noon = Noon - longitude;

	        // 2) Zuhr Time [Local noon]
	        zuhrTime = UT_Noon / 15 + timeZone;

	        double Fajr_Arc = radToDeg(Math.acos((Math.sin(degToRad(fajrTwilight)) - Math.sin(degToRad(Dec)) * Math.sin(degToRad(latitude))) / (Math.cos(degToRad(Dec)) * Math.cos(degToRad(latitude)))));
	        fajrTime = zuhrTime - (Fajr_Arc / 15);

	        return new DecimalFormat("00").format(fajrTime) + ":" + new DecimalFormat("00").format((fajrTime % 1) * 60);
	    }
	 

	    public static String getHigriDate() {
	    
	    /*
	        Calendar cl = Calendar.getInstance();
	        cl.setTime(new java.util.Date());
	        HijrahDate islamyDate = HijrahChronology.INSTANCE.date(LocalDate.of(cl.get(Calendar.YEAR), cl.get(Calendar.MONTH) + 1, cl.get(Calendar.DATE)));
	        return islamyDate.toString().split(" ")[2];
	    */
	    	return "02-02-02";
	    
	    }
	 
	    

	    static double degToRad(double degree) {
	        return ((3.1415926 / 180) * degree);
	    }
	//convert Radian to Degree

	    static double radToDeg(double radian) {
	        return (radian * (180 / 3.1415926));
	    }
	//make sure a value is between 0 and 360

	    static double moreLess360(double value) {
	        while (value > 360 || value < 0) {
	            if (value > 360) {
	                value -= 360;
	            } else if (value < 0) {
	                value += 360;
	            }
	        }

	        return value;
	    }
	//make sure a value is between 0 and 24

	    static double moreLess24(double value) {
	        while (value > 24 || value < 0) {
	            if (value > 24) {
	                value -= 24;
	            } else if (value < 0) {
	                value += 24;
	            }
	        }

	        return value;
	    }


	    public static boolean compareDate(String s) {

	        int nowh = Integer.parseInt(new SimpleDateFormat("HH").format(new java.util.Date()));
	        int nowm = Integer.parseInt(new SimpleDateFormat("mm").format(new java.util.Date()));
	        int hour = Integer.parseInt(s.split(":")[0]);
	        int minute = Integer.parseInt(s.split(":")[1]);

	        return (hour < nowh || (hour == nowh && minute < nowm));

	    }

	    public static String freeTashkeel(String s) {

	        StringBuilder sbuild = new StringBuilder(s);

	        String x = " ";
	        for (int i = 1570; i < 1595; i++) {
	            x += ((char) (i)) + "";
	        }
	        for (int i = 1601; i < 1611; i++) {
	            x += ((char) (i)) + "";

	        }
	        x += (char) (1569);
	        for (int i = 0; i < sbuild.length(); i++) {

	            if (!x.contains(sbuild.charAt(i) + "")) {
	                sbuild.delete(i, i + 1);
	            }

	        }

	        return sbuild.toString();
	    }

	    
	    
	    public static String arrangehdith(String s) {
	        String x = s.trim().split(" ")[0];
	        return "حديث رقم \n" + x.split("-")[0] + "\n" + s.replaceAll(x, "");
	    }

	    
	    
	    
	    TextView texter;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
		  
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.hdith_window);
	
		texter = (TextView) findViewById(R.id.textView1);
		texter.setMovementMethod(new ScrollingMovementMethod());
		
		if(MainActivity.direction)
		{
		
						
			
			
			try {
				texter.setText(showZkr());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}
			
		}
		else
		{
			texter.setText(important);
			/*
			InputStream is = getResources().openRawResource(R.raw.data);
			 InputStreamReader inputStreamReader = new InputStreamReader(is);
			 BufferedReader br = new BufferedReader(inputStreamReader);
			try {
				br.readLine();
				String holder = "";
				String h = br.readLine();
				while (!h.equals("xxx")) {
				
				holder += h + "\n";
				h = br.readLine();
				
				}
				
				
				texter.setText(holder);
				important = holder;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
			}
			*/
		}
		
		
		
		
		Button back = (Button) findViewById(R.id.back);
		   back.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					//System.exit(0);
					Intent i = new Intent (Hdith.this,MainActivity.class);
					i.setAction ("android.intent.action.MAIN");
					startActivity (i);
					

					
				}
			});
		   

		   
		   Button add = (Button) findViewById(R.id.add);
		   add.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
				
					
					
					if(MainActivity.direction)
					{
					
						
						important += "\n####\n" + texter.getText().toString().replaceAll("---------------", "");
						texter.setText(important);
							
						MainActivity.direction = false;
						

			   
			   
					}
								

					
				}
			});


		   Button shower = (Button) findViewById(R.id.shower);
		   shower.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					try {
						texter.setText(showZkr());
						MainActivity.direction = true;
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
					} catch (IOException e) {
						// TODO Auto-generated catch block
					}
					
				
				}
			});

	
	}



	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public  String showZkr() throws ClassNotFoundException, IOException {

    	
    	
    	
    	
        int target = new Random().nextInt(8);
        String holder = "لا حديث";
        if (target == 1) {

        	
            ObjectInputStream input = new ObjectInputStream(getResources().openRawResource(R.raw.h_bokhari));
            zekr = (ArrayList< String>) input.readObject();
            input.close();

        	
            while (holder.contains("لا حديث") || !(holder.contains("تحفة") || holder.contains("أطرافه") || holder.contains("طرفاه") || holder.contains("طرفه")) || holder.length() < 200) {
                holder = zekr.get(new Random().nextInt(zekr.size()));

            }

            holder = holder.replaceAll("تحفة", "").replaceAll("أطرافه", "").replaceAll("طرفاه", "").replaceAll("طرفه", "").replaceAll("   ", "").replaceAll("/", "").replaceAll(".،", ".").replaceAll("\\(.", "");

            String holding[] = holder.split(" ");

            if (holding[holding.length - 1].equals("باب")) {
                holder = "";

                for (int i = 0; i < holding.length - 2; i++) {
                    if (!holding[i].equals(freeTashkeel(holding[i]))) {
                        holder += holding[i] + " ";
                    }

                }
            }

        } else {
            
        	
        	ObjectInputStream input;
        if(target == 0)
        {
        	 input = new ObjectInputStream(getResources().openRawResource(R.raw.h_muslim));
        }
        else if(target == 2)
        {
        	input = new ObjectInputStream(getResources().openRawResource(R.raw.h_trmzi));
        }else if(target == 3)
        {
        	 input = new ObjectInputStream(getResources().openRawResource(R.raw.h_nesaai));
        }else if(target == 4)
        {
        	 input = new ObjectInputStream(getResources().openRawResource(R.raw.h_maga));
        }else if(target == 5)
        {
        	 input = new ObjectInputStream(getResources().openRawResource(R.raw.h_dawd));
        }else if(target == 6)
        {
        	 input = new ObjectInputStream(getResources().openRawResource(R.raw.h_moataa));
        }else {
        	 input = new ObjectInputStream(getResources().openRawResource(R.raw.h_saleh));
        }
        	

        
        zekr = (ArrayList< String>) input.readObject();
        input.close();

        while (holder.contains("لا حديث") || holder.length() < 100) {
                holder = zekr.get(new Random().nextInt(zekr.size()));

            }
        }
        if (target == 7) {
            holder = arrangehdith(holder).replaceAll("", "");
        } else {
            try {
                holder = holder.split("-")[0] + "\n" + holder.replaceFirst(holder.split("-")[0], "").replaceAll("-", "").trim().replaceAll("Y", "");
            } catch (PatternSyntaxException e) {
                holder = holder.replaceAll("\\(", "").replaceAll("\\)", "");
                holder = holder.split("-")[0] + "\n" + holder.replaceFirst(holder.split("-")[0], "").replaceAll("-", "").trim().replaceAll("Y", "");
            }

        }

                
        zekr.clear();

        return title[target] + "\n" + holder + "\n---------------";
        
    }



}
