package com.example.user.diplom_2;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.user.diplom_2.adapters.SubjectsAdapter;
import com.example.user.diplom_2.data.Subject;
import com.example.user.diplom_2.data.SubjectDetails;

import java.util.ArrayList;


/**
 * Главный экран приложения, в который загружается список предметов
 */
public class MainActivity extends AppCompatActivity {

    /** @param layoutManager - Макет разметки*/
    LinearLayoutManager layoutManager;
    /**  @param subjects - Список предметов*/
    ArrayList<Subject> subjects;
    /** @param  subjectsAdapter - Адаптер для связывания списка предметов и элемента RecyclerView*/
    SubjectsAdapter subjectsAdapter;
    /**  @param recyclerView - Элемент для отображения списка предметов*/
    RecyclerView recyclerView;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Туризм");
        recyclerView = findViewById(R.id.subjects_view);
        layoutManager = new LinearLayoutManager(this);
        subjects = SubjectDetails.getList();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        subjectsAdapter = new SubjectsAdapter(MainActivity.this,subjects);
        recyclerView.setAdapter(subjectsAdapter);

    }
}
