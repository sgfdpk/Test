package com.example.user.diplom_2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.user.diplom_2.data.Lection;

/**
 * Экран приложения, в который загружается содержимое лекции или лабораторной работы
 */
public class ReadLectionActivity extends AppCompatActivity {

    /** Параметры лекции или задания lectionName - название лекции, workName - название задания, adress - ссылка на файл с содержимым  */
    String lectionName,adress,workName;
    /** База данных */
    SQLiteDatabase db;
    /** Объект дляя записи результата обращения к БД */
    Cursor query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_lection);
        Intent intent = getIntent();
        lectionName = intent.getStringExtra("lection_name");
        workName = intent.getStringExtra("work_name");
        db = getBaseContext().openOrCreateDatabase("app.db",MODE_PRIVATE,null);
        if(lectionName!=null)
        {
            query = db.rawQuery("SELECT adres FROM lections WHERE name = '" + lectionName + "';", null);
            setTitle(lectionName);
        }
        else {
            query = db.rawQuery("SELECT adres FROM works WHERE name = '" + workName + "';", null);
            setTitle(workName);
        }


        if(query.moveToFirst()){
            do{
               // String name = query.getString(0);
                 adress = query.getString(0);
               // lectionList.add(new Lection(name,adress));
            }
            while(query.moveToNext());
        }
        query.close();
        WebView lectionRead = findViewById(R.id.lection_read);
        lectionRead.loadUrl(adress);

    }
}
