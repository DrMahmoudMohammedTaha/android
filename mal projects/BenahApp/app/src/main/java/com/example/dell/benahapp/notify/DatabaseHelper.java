package com.example.dell.benahapp.notify;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by basma on 14/05/2017.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "University.db";

    public static final String TABLE1_NAME = "Faculty_table";
    public static final String COL_facID = "facID";
    public static final String COL_facName = "facNAME";

    public static final String TABLE2_NAME = "Department_table";
    public static final String COL_depID = "depID";
    public static final String COL_DepName = "depNAME";


    public static final String TABLE3_NAME= "Doctor_table";
    public static final String COL_docID = "docID";
    public static final String COL_docNAME = "docNAME";

    public static final String TABLE4_NAME= "Course_table";
    public static final String COL_courseID = "courseID";
    public static final String COL_courseNAME = "courseNAME";

    public static final String TABLE7_NAME= "enroll_table";
    public static final String COL_userID = "userID";
    public static final String COL_username = "userNAME";

    public static final String TABLE8_NAME= "Doctor_table";

    public static final  String TABLE9_NAME= "SignUP_table";

    public static final  String TABLE10_NAME= "actionDr_table";
/*


    public static final String TABLE4_NAME = "Assistant_teacher_table";
    public static final String COL_AssID = "AssID";
    public static final String COL_AssNAME = "AssNAME";





    public static final String TABLE6_NAME= "Student_table";
    public static final String COL_studentID = "studentID";
    public static final String COL_studentNAME = "studentNAME";

    public static final String TABLE7_NAME= "enroll_table";
    public static final String COL_userID = "userID";
    public static final String COL_username = "userNAME";

 */





    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE1_NAME +" (facID INTEGER PRIMARY KEY AUTOINCREMENT,facNAME TEXT)");
        db.execSQL("INSERT INTO  "+TABLE1_NAME +" (facNAME) VALUES('Engineering,Shoubra')");

        db.execSQL("create table " + TABLE2_NAME +" (depID INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "depNAME TEXT,facID  INTEGER NOT NULL,FOREIGN KEY (facID) REFERENCES "+TABLE1_NAME +" (facID))" );
        db.execSQL("INSERT INTO  "+TABLE2_NAME +" (depNAME,facID) VALUES('Electrical',1)");
        db.execSQL("INSERT INTO  "+TABLE2_NAME +" (depNAME,facID) VALUES('Civil',1)");
        db.execSQL("INSERT INTO  "+TABLE2_NAME +" (depNAME,facID) VALUES('Mechanical Engineering',1)");
        db.execSQL("INSERT INTO  "+TABLE2_NAME +" (depNAME,facID) VALUES('Surveying Engineering',1)");
        db.execSQL("INSERT INTO  "+TABLE2_NAME +" (depNAME,facID) VALUES('Architectural Engineering',1)");


        db.execSQL("create table " + TABLE3_NAME +" (docID INTEGER PRIMARY KEY AUTOINCREMENT,docNAME TEXT,depNAME Text ," +
                "facID  INTEGER NOT NULL,FOREIGN KEY (facID) REFERENCES "+TABLE1_NAME +" (facID))");
        db.execSQL("INSERT INTO  "+TABLE3_NAME +" (docNAME,depNAME ,facID) VALUES('Islam ElShaarawy','Electrical',1)");
        db.execSQL("INSERT INTO  "+TABLE3_NAME +" (docNAME,depNAME ,facID) VALUES('Ahmed Bayiomy Zaki Ahmed','Electrical',1)");
        db.execSQL("INSERT INTO  "+TABLE3_NAME +" (docNAME,depNAME ,facID) VALUES('Hisham Eid','Civil',1)");

        db.execSQL("create table " + TABLE4_NAME +" (courseID INTEGER PRIMARY KEY AUTOINCREMENT ,docNAME TEXT ," +
                "courseNAME TEXT,facID  INTEGER NOT NULL,FOREIGN KEY (facID) REFERENCES "+TABLE1_NAME +" (facID))");
        db.execSQL("INSERT INTO  "+TABLE4_NAME +" (docNAME,courseNAME,facID) VALUES('Islam ElShaarawy','Programming',1)");
        db.execSQL("INSERT INTO  "+TABLE4_NAME +" (docNAME,courseNAME,facID) VALUES('Ahmed Bayiomy Zaki Ahmed','Data Mining',1);");

        db.execSQL("create table " + TABLE7_NAME +" (userID INTEGER PRIMARY KEY AUTOINCREMENT,userNAME TEXT,courseNAME TEXT)");

        db.execSQL("create table " + TABLE9_NAME +" (userNAME TEXT,Password TEXT,Job TEXT)");

        db.execSQL("create table " + TABLE10_NAME +" (courseNAME TEXT,courseNAMEAgain TEXT,action TEXT);");



    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE1_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE2_NAME);
        onCreate(db);
    }

    public boolean insertfac(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_facName, name);
        long result = db.insert(TABLE1_NAME, null, contentValues);
        if (result == -1)
            return false;
        else


        {

            return true;
        }
    }
    public Cursor getAllfac() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE1_NAME,null);
        return res;
    }

    public boolean insertdep(String name,String cource) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_DepName, name);
        contentValues.put(COL_facID, cource);
        long result = db.insert(TABLE2_NAME, null, contentValues);
        if (result == -1)
            return false;
        else


        {

            return true;
        }
    }
    public Cursor getAlldep() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE2_NAME,null);
        return res;
    }

    /*public  Cursor  getselectedtable1(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT facID FROM "+TABLE1_NAME+" WHERE facNAME = ?", new String[] {name});
        return c;
    }
 */
    public  Cursor  getselectedjointable2And1(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        // Cursor s = db.rawQuery("SELECT * FROM "+TABLE2_NAME+ " a"+" INNER JOIN "+TABLE1_NAME+" b"+" ON a.facID=b.facID WHERE facID = ?", new String[] {name});
        Cursor s = db.rawQuery("SELECT depNAME FROM "+TABLE2_NAME+ " a"+"  JOIN "+TABLE1_NAME+" b "+" ON a.facID=b.facID WHERE b.facNAME = ?", new String[] {name});
        // Cursor s = db.rawQuery("SELECT depNAME FROM "+TABLE2_NAME+ " a"+"  JOIN "+TABLE1_NAME+" b "+" ON a.facID=b.facID WHERE b.facID = ?", new String[] {id+""});

        return s;
    }

    public  Cursor  getselectedstaffbyfac(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor s = db.rawQuery("SELECT docNAME FROM "+TABLE3_NAME+ " a"+"  JOIN "+TABLE1_NAME+" b "+" ON a.facID=b.facID WHERE b.facNAME = ?", new String[] {name});

        return s;
    }
    public  Cursor  getselectedstaffbydep(String name1,String name2) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor s = db.rawQuery("SELECT docNAME FROM "+TABLE3_NAME+ " a"+"  JOIN "+TABLE1_NAME+" b "+" ON a.facID=b.facID WHERE b.facNAME = ? AND a.depNAME= ? ", new String[] {name1,name2});

        return s;
    }

    public  Cursor  getselectedcourse(String name1) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor s = db.rawQuery("SELECT courseNAME  FROM "+TABLE4_NAME+ " a"+"  JOIN "+TABLE1_NAME+" b "+" ON a.facID=b.facID WHERE b.facNAME = ?  ", new String[] {name1});

        return s;
    }
    public  Cursor  getselectedcoursebystaff(String name1,String name2) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor s = db.rawQuery("SELECT  courseNAME  FROM "+TABLE4_NAME+ " a"+"  JOIN "+TABLE1_NAME+" b "+" ON a.facID=b.facID WHERE b.facNAME = ? AND a.docNAME= ? ", new String[] {name1,name2});

        return s;
    }

    public boolean enrollincourse(String name1,String name2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_username, name1);
        contentValues.put("courseNAME", name2);

        long result = db.insert(TABLE7_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
        {

            return true;
        }
    }

    public Cursor getAllRegistered() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select username from "+TABLE7_NAME,null);
        return res;
    }

    public Integer deleteData (String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE7_NAME, "username = ?",new String[] {name});
    }
    public boolean signup(String name1,String name2,String name3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userNAME", name1);
        contentValues.put("Password", name2);
        contentValues.put("Job", name3);

        long result = db.insert(TABLE9_NAME, null, contentValues);
        if (result == -1)
            return false;
        else


        {

            return true;
        }
    }

    public  Cursor selectJob(String name1,String name2) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor s = db.rawQuery("SELECT Job  FROM "+TABLE9_NAME+" WHERE userNAME= ? AND Password= ? ", new String[] {name1,name2});

        return s;
    }

    public boolean actionslide(String name1,String name2,String name3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("courseNAME", name1);
        contentValues.put("courseNAMEAgain", name2);
        contentValues.put("action", name3);


        long result = db.insert(TABLE10_NAME, null, contentValues);
        if (result == -1)
            return false;
        else


        {

            return true;
        }
    }

    public  Cursor  selectnotification(String name1) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor s = db.rawQuery("SELECT  action,courseNAMEAgain  FROM "+TABLE10_NAME+ " a"+"  JOIN "+TABLE7_NAME+" b "+" ON a.courseNAME=b.courseNAME WHERE b.userNAME = ? ", new String[] {name1});
//Cursor s=db.rawQuery("SELECT i.action AS action, i.courseNAME AS courseNAME FROM "+TABLE10_NAME+ " as i INNER JOIN "+TABLE7_NAME+" AS s USING (courseNAME)" +
     //   " WHERE s.userNAME = ?");
        return s;
    }





/*
    public  Cursor  getselectedjointable1And8(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor s = db.rawQuery("SELECT docNAME FROM "+TABLE8_NAME+ " a"+"  JOIN "+TABLE1_NAME+" b "+" ON a.facID=b.facID WHERE b.facNAME = ?", new String[] {name});

        return s;
    }*/
/*
    public  Cursor  getselectedtable3(String name1,String name2) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor s = db.rawQuery("SELECT docNAME FROM "+TABLE3_NAME+" WHERE facNAME = ? AND  depName = ? ", new String[] {name1,name2});

        return s;
    }
*/
/*
    public boolean updateData(String id,String name,String cource) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,cource);

        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    } */

}
//    android:background="#f3b166"
/*
  <TextView
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:id="@+id/enroll"
        android:textAppearance="?android:attr/textAppearanceLarge"
        android:text="select course to enroll in it if you want"
        android:textAlignment="center"
        android:layout_alignParentLeft="true"
        android:layout_below="@id/course"
        android:layout_marginTop="10dp"
        android:background="#f09833"

        />
    <TextView
        android:layout_width="150dp"
        android:layout_height="50dp"
        android:textAppearance="?android:attr/textAppearanceLarge"
        android:text="User name :"
        android:id="@+id/username"
        android:layout_below="@+id/enroll"
        android:layout_alignParentLeft="true"
        android:layout_marginTop="15dp"/>

    <EditText
        android:layout_width="150dp"
        android:layout_height="50dp"
        android:id="@+id/editText_username"
        android:layout_alignTop="@+id/username"
        android:layout_toRightOf="@+id/username"
        android:layout_toEndOf="@+id/username"
        android:layout_alignParentRight="true"
        />


    <Button
        android:layout_width="100dp"
        android:layout_height="50dp"
        android:id="@+id/btnenroll"
        android:textAppearance="?android:attr/textAppearanceLarge"
        android:text="Enroll"
        android:layout_below="@id/username"
        android:layout_marginTop="15dp"
        android:layout_alignParentRight="true"
        android:layout_toRightOf="@+id/btngetregestered"


        />
    <Button
        android:layout_width="200dp"
        android:layout_height="50dp"
        android:id="@+id/btngetregestered"
        android:textAppearance="?android:attr/textAppearanceLarge"
        android:layout_alignParentLeft="true"
        android:text="The Registrants"
        android:layout_below="@id/username"
        android:layout_marginTop="15dp"

        />
    <Button
        android:layout_width="200dp"
        android:layout_height="50dp"
        android:id="@+id/btnunregestered"
        android:textAppearance="?android:attr/textAppearanceLarge"
        android:layout_alignParentLeft="true"
        android:text="UnRegister"
        android:layout_below="@id/btngetregestered"
        android:layout_marginTop="15dp"

        />


 */