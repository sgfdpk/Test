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
import com.example.user.diplom_2.data.Country;

import java.util.List;

/** Адаптер для списка стран */
public class CountrySpinnerAdapter extends ArrayAdapter<Country> {

    /**
     * Конструктор класса задающий параметры объекта
     * @param context Контекст приложения
     * @param objects Список стран
     */
    public CountrySpinnerAdapter(Context context,  List<Country> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if(convertView== null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.attract_spinner_row,parent,false);

        ImageView img = convertView.findViewById(R.id.attract_image);
        TextView country = convertView.findViewById(R.id.attract_type);
        Country currentItem = getItem(position);
        if(currentItem!=null) {
            img.setImageResource(currentItem.getCountryImageId());
            country.setText(currentItem.getCountryName());
        }

        return convertView;
    }
}
