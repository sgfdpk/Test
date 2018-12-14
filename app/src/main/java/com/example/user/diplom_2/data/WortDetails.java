package com.example.user.diplom_2.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class WortDetails {

    public  static void putWorks(SQLiteDatabase db){
        //------------------------------------------------------------------------------------------

        db.execSQL("CREATE TABLE IF NOT EXISTS works (name TEXT,subject TEXT,adres TEXT)");

        db.execSQL("DELETE FROM works ");
        //------------------------------------------------------------------------------------------
        db.execSQL("INSERT INTO works VALUES ('Змістовий модуль 1. Бізнес рекреаційний та туристичний','Система_туристических_аттракций','file:///android_asset/wort_wort_wort/attract/Mod_work1.html')");
        db.execSQL("INSERT INTO works VALUES ('Змістовий модуль 2. Система атракцій та кластерів у світі','Система_туристических_аттракций','file:///android_asset/wort_wort_wort/attract/Mod_work2.html')");
        db.execSQL("INSERT INTO works VALUES ('Системи атракцій','Система_туристических_аттракций','file:///android_asset/wort_wort_wort/attract/Attract_sys_2.html')");
        db.execSQL("INSERT INTO works VALUES ('Лабораторнi роботи','Система_туристических_аттракций','file:///android_asset/wort_wort_wort/attract/Practic_5year.html')");

        //------------------------------------------------------------------------------------------
        db.execSQL("INSERT INTO works VALUES ('МЕТОДИЧНІ ВКАЗІВКИ до виконання курсової роботи','Формальности_в_туризме','file:///android_asset/wort_wort_wort/formals/Method_kurs_2018.html')");
        db.execSQL("INSERT INTO works VALUES ('Домашнє завдання до лекції 3','Формальности_в_туризме','file:///android_asset/wort_wort_wort/formals/Lection3.html')");
        db.execSQL("INSERT INTO works VALUES ('Домашнє завдання до лекції 4','Формальности_в_туризме','file:///android_asset/wort_wort_wort/formals/Lection4.html')");
        db.execSQL("INSERT INTO works VALUES ('Домашнє завдання до лекції 5','Формальности_в_туризме','file:///android_asset/wort_wort_wort/formals/Lection5.html')");
        db.execSQL("INSERT INTO works VALUES ('Домашнє завдання до лекції 6','Формальности_в_туризме','file:///android_asset/wort_wort_wort/formals/Lection6.html')");

    }
    public static ArrayList<Wort> getWorks(int subject, SQLiteDatabase db){
        ArrayList<Wort> WorkList = new ArrayList<>();
        String sub = null;
        if(subject==0)
            sub="Формальности_в_туризме";
        if(subject==1)
            sub="Система_туристических_аттракций";

        Cursor query = db.rawQuery("SELECT name,adres FROM works WHERE subject = '"+sub+"';",null);
        if(query.moveToFirst()){
            do{
                String name = query.getString(0);
                String adress = query.getString(1);
                WorkList.add(new Wort(name,adress));
            }
            while(query.moveToNext());
        }
        query.close();

        return WorkList;
    }
}
