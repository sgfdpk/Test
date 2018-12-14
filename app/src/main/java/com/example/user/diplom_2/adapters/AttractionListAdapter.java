package com.example.user.diplom_2.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.user.diplom_2.R;
import com.example.user.diplom_2.data.Attraction;

import java.util.ArrayList;

/** Адаптер для списка аттракций */
public class AttractionListAdapter extends ArrayAdapter<Attraction>  {


    /**
     * Конструктор класса задающий параметры объекта
     * @param context Контекст приложения
     * @param AttractionList Список типов аттракций
     */
    public AttractionListAdapter(Context context, ArrayList<Attraction> AttractionList) {
        super(context, 0,AttractionList);
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    @Override
    public View getDropDownView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }


    public View initView(int position, @NonNull View convertView, @NonNull ViewGroup parent){
        if(convertView== null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.attract_details_spinner_row,parent,false);
        TextView name = convertView.findViewById(R.id.attract_object);
        //WebView description = convertView.findViewById(R.id.attract_description);
        Attraction attraction = getItem(position);
        if(attraction!=null){
            name.setText(attraction.getName());
           //description.loadData(attraction.getDescription()+"\n\n Находится"+attraction.getAdress()+ "\n<a href=\""+attraction.getUrl()+"\">текст ссылки</a>",
                //    "text/html", "UTF-8");
        }
        return convertView;
    }
}
