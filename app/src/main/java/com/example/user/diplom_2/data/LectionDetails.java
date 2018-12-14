package com.example.user.diplom_2.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class LectionDetails {

    public  static void putLections(SQLiteDatabase db){
        //------------------------------------------------------------------------------------------
        db.execSQL("CREATE TABLE IF NOT EXISTS lections (name TEXT,subject TEXT,adres TEXT)");
        //db.execSQL("CREATE TABLE IF NOT EXISTS works (name TEXT,subject TEXT,adres TEXT)");
        db.execSQL("DELETE FROM lections ");
        //db.execSQL("DELETE FROM works ");
        //------------------------------------------------------------------------------------------
        //db.execSQL("INSERT INTO works VALUES ('Змістовий модуль 1. Бізнес рекреаційний та туристичний','Система_туристических_аттракций','file:///android_asset/wort_wort_wort/attract/Mod_work1.html')");
       // db.execSQL("INSERT INTO works VALUES ('Змістовий модуль 2. Система атракцій та кластерів у світі','Система_туристических_аттракций','file:///android_asset/wort_wort_wort/attract/Lection2.html')");
       // db.execSQL("INSERT INTO works VALUES ('Системи атракцій','Система_туристических_аттракций','file:///android_asset/wort_wort_wort/attract/Attract_sys_2.html')");
       // db.execSQL("INSERT INTO works VALUES ('Лекція 4. Фонтани як туристичні атракції','Система_туристических_аттракций','file:///android_asset/lections/wort_wort_wort/Lection4.html')");

        //------------------------------------------------------------------------------------------
        db.execSQL("INSERT INTO lections VALUES ('Лекція 1. Розваги як бізнес','Система_туристических_аттракций','file:///android_asset/lections/attract/Lection1.html')");
        db.execSQL("INSERT INTO lections VALUES ('Лекція 2. Дестинації, їх розвиток, інвестиції','Система_туристических_аттракций','file:///android_asset/lections/attract/Lection2.html')");
        db.execSQL("INSERT INTO lections VALUES ('Лекція 3. Інфраструктура як базис розвитку атракцій','Система_туристических_аттракций','file:///android_asset/lections/attract/Lection3.html')");
        db.execSQL("INSERT INTO lections VALUES ('Лекція 4. Фонтани як туристичні атракції','Система_туристических_аттракций','file:///android_asset/lections/attract/Lection4.html')");

        //------------------------------------------------------------------------------------------
        db.execSQL("INSERT INTO lections VALUES ('Лекція 1. Формальності в туризмі: основні теоретичні положення','Формальности_в_туризме','file:///android_asset/lections/formals/Lection1.html')");
        db.execSQL("INSERT INTO lections VALUES ('Лекція 2. Поліцейські формальності.','Формальности_в_туризме','file:///android_asset/lections/formals/Lection2.html')");
        db.execSQL("INSERT INTO lections VALUES ('Лекція 3. Паспортні формальності','Формальности_в_туризме','file:///android_asset/lections/formals/Lection3.html')");
        db.execSQL("INSERT INTO lections VALUES ('Лекція 4. Візові формальності','Формальности_в_туризме','file:///android_asset/lections/formals/Lection4.html')");
        db.execSQL("INSERT INTO lections VALUES ('Лекція 5. Митні формальності.','Формальности_в_туризме','file:///android_asset/lections/formals/Lection5.html')");
        db.execSQL("INSERT INTO lections VALUES ('Лекція 6. Валютні формальності','Формальности_в_туризме','file:///android_asset/lections/formals/Lection6.html')");
        db.execSQL("INSERT INTO lections VALUES ('Лекція 7. Медико-санітарні формальності та формальності безпеки','Формальности_в_туризме','file:///android_asset/lections/formals/Lection7.html')");

    }
    public static ArrayList<Lection> getLections(int subject,SQLiteDatabase db){
        ArrayList<Lection> lectionList = new ArrayList<>();
        String sub = null;
        if(subject==0)
            sub="Формальности_в_туризме";
        if(subject==1)
            sub="Система_туристических_аттракций";

        Cursor query = db.rawQuery("SELECT name,adres FROM lections WHERE subject = '"+sub+"';",null);
        if(query.moveToFirst()){
            do{
                String name = query.getString(0);
                String adress = query.getString(1);
                lectionList.add(new Lection(name,adress));
            }
            while(query.moveToNext());
        }
        query.close();

        return lectionList;
    }
}
