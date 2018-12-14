package com.example.user.diplom_2.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.user.diplom_2.LectionsActivity;
import com.example.user.diplom_2.R;
import com.example.user.diplom_2.data.Subject;


import java.util.ArrayList;

/** Адаптер для предметов */
public class SubjectsAdapter extends RecyclerView.Adapter<SubjectsAdapter.MyHolder> {

    /** Контекст приложения*/
    private Context context;
    /** Список предметов*/
    private ArrayList<Subject> subjects;

    /**
     * Конструктор класса задающий параметры объекта
     * @param context Контекст приложения
     * @param subjects  Список предметов
     */
    public SubjectsAdapter(Context context, ArrayList<Subject> subjects) {
        this.context = context;
        this.subjects = subjects;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.subjects_list,null);
        MyHolder myHolder = new MyHolder(view);
        return  myHolder;
    }

    /**
     *Загрузка и отображение элемента
     * @param viewHolder класс хранящий объект
     * @param i позиция элемента
     */
    @Override
    public void onBindViewHolder(@NonNull MyHolder viewHolder, int i) {
      final int  pos=i;
        viewHolder.image.setImageResource(subjects.get(i).getSubjectImageId());
        viewHolder.progress.setProgress(subjects.get(i).getProgress());
        viewHolder.subjectName.setText(subjects.get(i).getSubjectName());
        viewHolder.subjectProgress.setText(subjects.get(i).getSubjectProgress());
        /** Обработчик нажатия на элемент */
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(context.getApplicationContext(),"item "+Integer.toString(pos),Toast.LENGTH_SHORT);
                toast.show();
               Intent in = new Intent(context,LectionsActivity.class);
               in.putExtra("subject",pos);
                context.startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
       return subjects.size();
    }

    /** Класс MyHolder для хранения объектов Subject в элементе View */
    public static class MyHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView subjectName,subjectProgress;
        ProgressBar progress;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.formals_icon);
            subjectName = itemView.findViewById(R.id.subject_name);
            subjectProgress = itemView.findViewById(R.id.subject_progress);
            progress = itemView.findViewById(R.id.progress);
        }
    }
}
