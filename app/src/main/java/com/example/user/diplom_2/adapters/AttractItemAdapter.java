package com.example.user.diplom_2.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.diplom_2.R;
import com.example.user.diplom_2.data.AttractionItem;

import java.util.ArrayList;

/** Адаптер для типов аттракций */
public class AttractItemAdapter extends ArrayAdapter<AttractionItem> {

    /**
     * Конструктор класса задающий параметры объекта
     * @param context Контекст приложения
     * @param attractionItems Список аттракций
     */
    public AttractItemAdapter(Context context, ArrayList<AttractionItem> attractionItems){

        super(context,0,attractionItems);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }
    private  View initView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if(convertView== null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.attract_spinner_row,parent,false);

        ImageView img = convertView.findViewById(R.id.attract_image);
        TextView attractType = convertView.findViewById(R.id.attract_type);
        AttractionItem currentItem = getItem(position);
        if(currentItem!=null) {
            img.setImageResource(currentItem.getImgID());
            attractType.setText(currentItem.getAttractName());
        }

        return convertView;
    }
}
