package com.example.user.diplom_2.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.user.diplom_2.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CountryDetails {

    static  Map<String,Integer> flags = new HashMap<>();
    public static void  putCounties(SQLiteDatabase db){
         flags = new HashMap<>();
        flags.put("Турция", R.drawable.turkey);
        flags.put("Польша", R.drawable.poland);
        flags.put("Молдавия", R.drawable.moldavia);

        db.execSQL("CREATE TABLE IF NOT EXISTS countries (name TEXT,flagIMG INTEGER,info TEXT)");
        db.execSQL("DELETE FROM countries ");

        //------------------------------------------------------------------------------------------
        db.execSQL("INSERT INTO countries VALUES('Турция',"+ flags.get("Турция")+",'file:///android_asset/countryInfo/Turkey/Turkey.html')");
        db.execSQL("INSERT INTO countries VALUES('Польша',"+ flags.get("Польша")+",'file:///android_asset/countryInfo/Poland/Poland.html')");
        db.execSQL("INSERT INTO countries VALUES('Молдавия',"+ flags.get("Молдавия")+",'file:///android_asset/countryInfo/Moldova/Moldova.html')");
    }

    public static ArrayList<Country> getCountries( SQLiteDatabase db){
        ArrayList<Country> countryList = new ArrayList<>();
        String sub = null;

        Cursor query = db.rawQuery("SELECT  * FROM countries;",null);
        if(query.moveToFirst()){
            do{
                String name = query.getString(0);
                Integer flag = query.getInt(1);
                String info = query.getString(2);
                countryList.add(new Country(flag,name,info));
            }
            while(query.moveToNext());
        }
        query.close();

        return countryList;
    }
}
