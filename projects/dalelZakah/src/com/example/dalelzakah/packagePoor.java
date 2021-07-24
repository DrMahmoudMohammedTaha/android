package com.example.dalelzakah;

import java.util.ArrayList;
import java.util.Iterator;

import android.util.Log;

public class packagePoor {

    public static final String POOR = "فقير";
    public static final String NEED = "محتاج";

    private static ArrayList<poor> thePoor = new ArrayList<poor>();
    private static ArrayList<String> globalGiven = new ArrayList<String>();

    public static void setGlobalGiven(ArrayList<String> globalGiven) {
        packagePoor.globalGiven = globalGiven;
    }

    public static void setThePoor(ArrayList<poor> thePoor) {
        packagePoor.thePoor = thePoor;
    }

    public static ArrayList<poor> getThePoor() {
        return thePoor;
    }

    public static ArrayList<String> getGlobalGiven() {
        return globalGiven;
    }

    public static void addGlobalGiven(String s) {
        globalGiven.add(s);
    }

    public static ArrayList<String> getThePoorNames() {
        ArrayList<String> poorNames = new ArrayList<String>();
        for (poor object : thePoor) {
            poorNames.add(object.toString());
        }
        return poorNames;
    }

    public static void addPoor(poor p) throws CloneNotSupportedException {
        thePoor.add(p.clone());
    }

    public static void deletePoor(poor p) {
        thePoor.remove(p);
    }

    public static void editPoor(poor old, poor newer) throws CloneNotSupportedException {
        thePoor.remove(old);
        thePoor.add(newer.clone());

    }

    public static int findPoor(poor p) {
        for (int i = 0; i < thePoor.size(); i++) {
            if (thePoor.get(i).equals(p)) {
                return i;
            }

        }
        return -1;
    }

    public static poor getPoor(int i) {
        return thePoor.get(i);
    }
    
    
    public static ArrayList<poor> getSorted() throws CloneNotSupportedException {
        ArrayList<poor> mostPoor = new ArrayList<poor>();
        ArrayList<poor> lessPoor = new ArrayList<poor>();
        for (int i = 0; i < thePoor.size(); i++) {
            if (thePoor.get(i).getState().equals(POOR)) {
                mostPoor.add(thePoor.get(i).clone());

            } else {
                lessPoor.add(thePoor.get(i).clone());
            }
        }
        for (int i = 0; i < lessPoor.size(); i++) {
            mostPoor.add(lessPoor.get(i));
        }
        return mostPoor;
    }

    public static void formatDatabase()  {
        thePoor.clear();
        globalGiven.clear();
//        filier.writeData(thePoor, globalGiven);
    }

    public static void formatGivens()  {
        globalGiven.clear();
        for (int i = 0; i < thePoor.size(); i++) {
            thePoor.get(i).getGiven().clear();
        }
  //      filier.writeData(thePoor, globalGiven);
    }

    
    public static ArrayList<poor> findWithName(String s)  {
        ArrayList<poor> results = new ArrayList<poor>();
       
        for (int i = 0; i < thePoor.size(); i++) {
            if (thePoor.get(i).getName().contains(s)) {
                results.add(thePoor.get(i));
            }
        }
       
        return results;
    }
    public static ArrayList<String> findName(String s)  {
        ArrayList<String> results = new ArrayList<String>();
        for (int i = 0; i < thePoor.size(); i++) {
            if (thePoor.get(i).getName().contains(s)) {
                results.add(thePoor.get(i).toString());
            }
        }
        return results;
    }

    
    public static ArrayList<String> findWithGiven(String s)  {
        
    	ArrayList<String> results = getThePoorNames();
        
        
        for (int i = 0; i < thePoor.size(); i++) {
            poor p = thePoor.get(i);
            for (int j = 0; j < p.getGiven().size(); j++) {
                if (p.getGiven().get(j).equals(s)) {
                    results.set(i , results.get(i)+ "\n√");
                    break;
                }

            }
        }

        return results;

    }
}
