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
import com.example.user.diplom_2.data.Lection;

import java.util.ArrayList;

/** Адаптер для лекций */
public class LectionAdapter extends RecyclerView.Adapter<LectionAdapter.MyHolder> {
    /** Контекст приложения*/
    private Context context;
    /** Список лекций*/
    ArrayList<Lection> lections;

    /**
     * Конструктор класса задающий параметры объекта
     * @param context Контекст приложения
     * @param lections  Список лекций
     */
    public LectionAdapter(Context context, ArrayList<Lection> lections) {
        this.context = context;
        this.lections = lections;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lections_item,null);
        MyHolder myHolder = new MyHolder(view);
        return  myHolder;
        //return null;
    }

    /**
     *Загрузка и отображение элемента
     * @param myHolder класс хранящий объект
     * @param i позиция элемента
     */
    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, int i) {
        final int  pos=i;
        myHolder.lectionName.setText(lections.get(i).getLectionName());
        myHolder.empty.setText(null);
        myHolder.lectionDone.setChecked(lections.get(i).isChecked());
        /** Обработчик нажатия на элемент */
        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context,ReadLectionActivity.class);
                in.putExtra("lection_name",lections.get(pos).getLectionName());
                context.startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lections.size();
        //return 0;
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
