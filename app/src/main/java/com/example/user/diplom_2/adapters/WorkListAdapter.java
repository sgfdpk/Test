package com.example.user.diplom_2.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.example.user.diplom_2.R;
import com.example.user.diplom_2.ReadLectionActivity;
import com.example.user.diplom_2.data.Wort;

import java.util.ArrayList;
import java.util.List;

/** Адаптер для заданий */
public class WorkListAdapter extends RecyclerView.Adapter<WorkListAdapter.MyHolder> {

    /** Контекст приложения*/
    private Context context;
    /** Список лекций*/
    ArrayList<Wort> works;

    /**
     * Конструктор класса задающий параметры объекта
     * @param context Контекст приложения
     * @param works  Список заданий
     */
    public WorkListAdapter(Context context, ArrayList<Wort> works) {
        this.context = context;
        this.works = works;
    }

    /**
     *Загрузка и отображение элемента
     * @param myHolder класс хранящий объект
     * @param i позиция элемента
     */
    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
        final int  pos=i;
        myHolder.lectionName.setText(works.get(i).getWorkName());
        myHolder.empty.setText(null);
       // myHolder.lectionDone.setChecked(works.get(i).isChecked());
        /** Обработчик нажатия на элемент */
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context,ReadLectionActivity.class);
                in.putExtra("work_name",works.get(pos).getWorkName());
                context.startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return works.size();
    }

    @NonNull
    @Override
    public WorkListAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lections_item,null);
        WorkListAdapter.MyHolder myHolder = new WorkListAdapter.MyHolder(view);
        return  myHolder;
    }

    /** Класс MyHolder для хранения объектов Subject в элементе View */
    public static class MyHolder extends RecyclerView.ViewHolder{
        TextView lectionName, empty;
        CheckBox lectionDone;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            lectionName = itemView.findViewById(R.id.lection);
            empty = itemView.findViewById(R.id.buff);
            lectionDone = itemView.findViewById(R.id.lection_done);
        }
    }
}
