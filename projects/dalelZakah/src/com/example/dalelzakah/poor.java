package com.example.dalelzakah;

import java.util.ArrayList;


public class poor implements Comparable<poor> {


    public static final String POOR = "›ﬁÌ—";
    public static final String NEED = "„Õ «Ã";
    private String name;
    // false = need help      true = poor
    private boolean state;
    private ArrayList<String> given = new ArrayList<String>();

    public poor() {
        name = "";
        state = false;
    }

    public poor(String name) {
        this.name = name;
        state = false;
    }

    public poor(String name, boolean state) {
        this.name = name;
        this.state = state;
    }

    public void addGiven(String s) {
        given.add(s);
    }

    public ArrayList<String> getGiven() {
        return given;
    }

    @Override
    public String toString() {
        return getName() + " / " + getState();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getState() {
        return state ?POOR : NEED ;
    }

    @Override
    public int compareTo(poor t) {
        return getName().compareTo(t.getName());
    }

    public void setGiven(ArrayList<String> given) {
        this.given = given;
    }

    
    @Override
    protected poor clone() throws CloneNotSupportedException {
        poor man = new poor();
        man.setName(name);
        man.setState(state);
        man.setGiven(given);
        return man;
    }
}
