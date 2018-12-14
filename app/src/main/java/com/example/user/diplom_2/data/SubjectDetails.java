package com.example.user.diplom_2.data;

import com.example.user.diplom_2.R;
import java.util.ArrayList;

public class SubjectDetails {
    public static ArrayList<Subject> getList() {
        ArrayList<Subject> subjectList = new ArrayList<>();
        subjectList.add(new Subject(R.drawable.formals, 6, "Формальности в туризме", "6/100"));
        subjectList.add(new Subject(R.drawable.attract, 1, "Система туристических аттракций", "1/100"));
        return subjectList;
    }
}
