package com.example.user.diplom_2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.user.diplom_2.DB.Inserter;
import com.example.user.diplom_2.adapters.AttractItemAdapter;
import com.example.user.diplom_2.adapters.AttractionListAdapter;
import com.example.user.diplom_2.adapters.CountrySpinnerAdapter;
import com.example.user.diplom_2.adapters.LectionAdapter;
import com.example.user.diplom_2.adapters.WorkListAdapter;
import com.example.user.diplom_2.data.Attraction;
import com.example.user.diplom_2.data.AttractionItem;
import com.example.user.diplom_2.data.Country;
import com.example.user.diplom_2.data.CountryDetails;
import com.example.user.diplom_2.data.Lection;
import com.example.user.diplom_2.data.LectionDetails;
import com.example.user.diplom_2.data.Wort;
import com.example.user.diplom_2.data.WortDetails;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class LectionsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /** Обработчик нажатия на выпадающий список с типами аттракциями
     *загружает из БД список аттракций выбранного типа
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        AttractionItem item = (AttractionItem)parent.getItemAtPosition(position);
        Toast toast = Toast.makeText(getApplicationContext(),"item "+item,Toast.LENGTH_SHORT);
        toast.show();
        AttractionItem a = (AttractionItem) attracts.getSelectedItem();
        switch (a.getAttractName()) {
            case "Фонтаны":{ initFountainListDB("fountain");break;}
            case "Парки":{ initFountainListDB("park");break;}
            case "Улицы":{ initFountainListDB("street");break;}
        }

    }

    String url = "http://free.currencyconverterapi.com/api/v5/convert?q=";
    String url_end="&compact=y";
    String geoUriString = "geo:0,0?q=Одесса ";

    private final static String FILE_NAME = "data/data/com.example.user.diplom_2/databases/app.db";
    int serverPort = 1408;


    GoogleMap googleMap;

    TabHost tabHost;
    float lastX;
    int subject;
    LinearLayoutManager layoutManager, workManager;
    LinearLayout countryTab, attractionTab;
    ArrayList<Lection> lections;
    ArrayList<Wort> works;
    LectionAdapter lectionAdapter;
    WorkListAdapter workAdapter;
    RecyclerView recyclerView,recyclerViewwork;

    SQLiteDatabase db;
    ArrayList<AttractionItem> attractionList;
    ArrayList<Attraction> attractionDetailedList;
    AttractItemAdapter attractItemAdapter;
    AttractionListAdapter attractionListAdapter;
    CountrySpinnerAdapter countrySpinnerAdapter;
    Spinner attracts,attractDetail,countries;

    HashMap<String,String> currency = new HashMap<>();
    HashMap<String,String> country_currency = new HashMap<>();
    Spinner currency_from,currency_to;
    EditText currency_from_input,currency_to_output;

    private static final int CAMERA_REQUEST = 0;
    private ImageView imageView;
    private Button take_a_photo;




    class SendImg extends AsyncTask<Bitmap,Void,Void>{

        @Override
        protected Void doInBackground(Bitmap... map) {
            Log.e("Original   dimensions 2", map[0].getWidth()+" "+map[0].getHeight());
            Socket socket;
            try {
                socket = new Socket("192.168.0.101", serverPort);
                socket.setSoTimeout(10000);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                map[0].compress(Bitmap.CompressFormat.JPEG,100,stream);
                Log.e("Compressed dimensions", map[0].getWidth()+" "+map[0].getHeight());
                byte[] byteArray = stream.toByteArray();
                InputStream inn = new ByteArrayInputStream(byteArray);
                dos.writeShort(1);
                dos.writeInt(byteArray.length);
                int len = 0 ;
                byte [] b = new byte [1024];
                while ((len = inn.read(b)) != -1)
                {
                    dos.write(b,0,len);
                }
                dos.flush();
                stream.close();
                inn.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    class UpdateDB extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Socket socket = new Socket("192.168.0.101", serverPort);
                socket.setSoTimeout(10000);
                DataOutputStream toServer = new DataOutputStream(socket.getOutputStream());
                // Отправка данных на сервер
                toServer.writeShort(2);
                // Ответ сервера
                InputStream in = socket.getInputStream();
                DataInputStream din = new DataInputStream(in);
                long fileSize = din.readLong();
                String fileName = din.readUTF(); //прием имени файла

                byte[] buffer = new byte[64*1024];

                File f= new File(FILE_NAME);
                FileOutputStream outF = new FileOutputStream(f);
                int count, total = 0;
                while ((count = din.read(buffer, 0, buffer.length)) != -1){
                    total += count;
                    outF.write(buffer, 0, count);
                    if(total == fileSize){
                        break;
                    }
                }
                outF.flush();
                outF.close();
                socket.close();
            }
            catch (UnknownHostException ex) {
                ex.printStackTrace();
            } catch (IOException e) {
                try {
                    if (e instanceof SocketTimeoutException) {
                        throw new SocketTimeoutException();
                    } else {
                        e.printStackTrace();
                    }
                } catch (SocketTimeoutException ste) {
                    System.out.println("Turn off the client by timeout");
                }
            }
            ConnectToDatabase();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

        }
    }

    public  void createMapView(){
        try{
                googleMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map_view)).getMap();
        }catch (NullPointerException e){Log.e("MapError",e.toString());}
    }

    public void addMarker(Attraction attraction){
        if(googleMap!=null){
            googleMap.clear();
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(attraction.getLatitude(),attraction.getLongitude())));
            googleMap.addMarker(new MarkerOptions().position(new LatLng(attraction.getLatitude(),attraction.getLongitude())).title(attraction.getName()));
        }
    }
    public void  initList(){
        attractionList = new ArrayList<>();
        attractionList.add(new AttractionItem("Фонтаны",R.drawable.fontan));
        attractionList.add(new AttractionItem("Парки",R.drawable.park));
        attractionList.add(new AttractionItem("Улицы",R.drawable.street));
    }

    public void  initFountainListDB(String Table){
        attractionDetailedList = new ArrayList<>();
        Cursor query = db.rawQuery("SELECT * FROM "+Table+" ",null);
        if(query.moveToFirst()){
            do{
                String name = query.getString(0);
                String description = query.getString(1);
                String adres = query.getString(2);
                String url = query.getString(3);
                float latitude = Float.valueOf(query.getString(4));
                float longitude = Float.valueOf(query.getString(5));
                attractionDetailedList.add(new Attraction(name,description,adres,url,latitude,longitude));
            }
            while(query.moveToNext());
        }
        query.close();
        attractDetail = findViewById(R.id.attract_detail_spinner);
        attractionListAdapter = new AttractionListAdapter(this,attractionDetailedList);
        attractDetail.setAdapter(attractionListAdapter);
        attractDetail.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Attraction att = (Attraction) parent.getItemAtPosition(position);
                addMarker(att);
                WebView attractInfo = findViewById(R.id.attract_description);
                attractInfo.setHorizontalScrollBarEnabled(true);
                attractInfo.setBackgroundColor(Color.TRANSPARENT);
                attractInfo.loadDataWithBaseURL(null,att.getDescription()+"\n Находится"+att.getAdress()+ "\n<a href=\""+att.getUrl()+"\">текст ссылки</a>",
                        "text/html", "UTF-8",null);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void ConnectToDatabase(){
        db = getBaseContext().openOrCreateDatabase("app.db",MODE_PRIVATE,null);
    }



    private class Currency extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String ...params) {
            if(currency_from.getSelectedItem()==null||currency_from.getSelectedItem()==""||currency_from.getSelectedItem()==" ")
                return null;

            BufferedReader reader ;
            StringBuilder buf=new StringBuilder();
            try{
                URL url  = new URL(params[0]);
                HttpURLConnection c = (HttpURLConnection)url.openConnection();
                c.setRequestMethod("GET");
                c.setReadTimeout(10000);
                c.connect();
                reader= new BufferedReader(new InputStreamReader(c.getInputStream()));
                String line;
                while ((line=reader.readLine()) != null)
                    buf.append(line);
            }catch (MalformedURLException ex){ ex.printStackTrace();}
             catch (IOException ex){ ex.printStackTrace();}
             catch (NumberFormatException ex){ex.printStackTrace();}
            return(buf.toString());
        }
        @Override
        protected void onPostExecute(String content) {
            try{
                if(content==null)
                    return;
            JSONObject jsonObject = new JSONObject(content);
            JSONObject json = jsonObject.getJSONObject(currency.get(currency_from.getSelectedItem())+"_"+currency.get(currency_to.getSelectedItem()));
            double value = json.getDouble("val");
            double result = Integer.valueOf(currency_from_input.getText().toString())*value;
            DecimalFormat df = new DecimalFormat("#.##");
            currency_to_output.setText(String.valueOf(df.format(result)));
            } catch (JSONException e){e.printStackTrace();}
            catch (NumberFormatException ex){ex.printStackTrace();}
        }
    }
    /**
     * Метод для переключения вкладок
     * @param direction - направление переключения
     */
    public void switchTabs(boolean direction) {
        if (direction) // true = move left
        {
            if (tabHost.getCurrentTab() == 0)
                tabHost.setCurrentTab(tabHost.getTabWidget().getTabCount() - 1);
            else
                tabHost.setCurrentTab(tabHost.getCurrentTab() - 1);
        } else   // move right
        {
            if (tabHost.getCurrentTab() != (tabHost.getTabWidget()
                    .getTabCount() - 1))
                tabHost.setCurrentTab(tabHost.getCurrentTab() + 1);
            else
                tabHost.setCurrentTab(0);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float currentX;
        switch (event.getAction()){
            case  MotionEvent.ACTION_DOWN:{
                lastX = event.getX();
                break;
            }
            case  MotionEvent.ACTION_UP:{
                currentX = event.getX();
                if (lastX < currentX&&(currentX-lastX)>100)
                    switchTabs(true);
                if (lastX > currentX&&(lastX-currentX)>100)
                    switchTabs(false);
                break;
            }
        }
        return false;
    }


    public static void selectSpinnerItemByValue(Spinner spnr, String value) {
        ArrayAdapter adapter = (ArrayAdapter) spnr.getAdapter();
        for (int position = 0; position < adapter.getCount(); position++) {
            if(adapter.getItem(position) == value) {
                spnr.setSelection(position);
                return;
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap thumbnailBitmap = (Bitmap) data.getExtras().get("data");
            Log.e("Original   dimensions", thumbnailBitmap.getWidth()+" "+thumbnailBitmap.getHeight());
            imageView.setImageBitmap(thumbnailBitmap);
            new SendImg().execute(thumbnailBitmap);
            tabHost.setCurrentTab(2);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lections);
        Intent intent = getIntent();
        createMapView();

        imageView = findViewById(R.id.photo);
        take_a_photo = findViewById(R.id.take_photo);
        take_a_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });
        Display display = getWindowManager().getDefaultDisplay();
        double width = display.getWidth();
        double doubleSize = width/4;
        subject =intent.getIntExtra("subject",0);
        if(subject == 0)
            setTitle("Формальности в туризме");
        if(subject == 1)
            setTitle("Система туристических аттракций");
        //new UpdateDB().execute();

        //------------------------------------------------------------------------------------------

        currency.put("Гривна","UAH");
        currency.put("Доллар США","USD");
        currency.put("Евро","EUR");
        currency.put("Польский злотый","PLN");
        currency.put("Турецкая лира","TRY");
        currency.put("Молдавский лей","MDL");
        country_currency.put("Польша","Польский злотый");
        country_currency.put("Турция","Турецкая лира");
        country_currency.put("Молдавия","Молдавский лей");
        country_currency.put("Украина","Гривна");

        //------------------------------------------------------------------------------------------

        db = getBaseContext().openOrCreateDatabase("app.db",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS fountain (name TEXT,description TEXT,adres TEXT,url TEXT,latitude TEXT,longitude TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS park (name TEXT,description TEXT,adres TEXT,url TEXTl,atitude TEXT,longitude TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS street (name TEXT,description TEXT,includes TEXT,url TEXT,latitude TEXT,longitude TEXT)");
        //new UpdateDB().execute();
        Inserter in = new Inserter(db);
        LectionDetails.putLections(db);
        WortDetails.putWorks(db);
        CountryDetails.putCounties(db);

       // in.InsertF();
      // in.InsertP();
        //in.InsertS();


        countries = findViewById(R.id.country_spinner);
        countrySpinnerAdapter = new CountrySpinnerAdapter(this,CountryDetails.getCountries(db));
        countries.setAdapter(countrySpinnerAdapter);
        currency_from_input = findViewById(R.id.from_value);
        currency_to_output = findViewById(R.id.to_value);
        currency_from_input.setWidth((int)doubleSize);
        currency_to_output.setWidth((int)doubleSize);


        currency_from_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(currency_from.getSelectedItem() != null || currency_from.getSelectedItem() !=""|| currency_from.getSelectedItem() !=" ")
                    new Currency().execute(url +currency.get(currency_from.getSelectedItem())+"_"+currency.get(currency_to.getSelectedItem())+url_end);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        countries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Country country = (Country) parent.getItemAtPosition(position);
                selectSpinnerItemByValue(currency_from,country_currency.get("Украина"));
                selectSpinnerItemByValue(currency_to,country_currency.get(country.getCountryName()));
                WebView countryInfo = findViewById(R.id.country_description);
                countryInfo.setHorizontalScrollBarEnabled(true);
                countryInfo.setBackgroundColor(Color.TRANSPARENT);
                countryInfo.loadUrl(country.getCountryInfo());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //------------------------------------------------------------------------------------------

        Set<String> keys = currency.keySet();
        String[] data = keys.toArray(new String[keys.size()]);

        currency_from = findViewById(R.id.converter_spinner_from);
        currency_to = findViewById(R.id.converter_spinner_to);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,data);

        currency_from.setAdapter(spinnerArrayAdapter);
        currency_to.setAdapter(spinnerArrayAdapter);

        //------------------------------------------------------------------------------------------


        //------------------------------------------------------------------------------------------

        recyclerView = findViewById(R.id.lection_list);
        layoutManager = new LinearLayoutManager(this);
        lections = LectionDetails.getLections(subject,db);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        lectionAdapter = new LectionAdapter(LectionsActivity.this,lections);
        recyclerView.setAdapter(lectionAdapter);

        //------------------------------------------------------------------------------------------

        recyclerViewwork = findViewById(R.id.work_list);
        works = WortDetails.getWorks(subject,db);
        workManager = new LinearLayoutManager(this);
        recyclerViewwork.setLayoutManager(workManager);
        recyclerViewwork.setHasFixedSize(true);
        workAdapter = new WorkListAdapter(LectionsActivity.this,works);
        recyclerViewwork.setAdapter(workAdapter);

        //------------------------------------------------------------------------------------------

        initList();
        attracts = findViewById(R.id.attract_spinner);
        attractItemAdapter = new AttractItemAdapter(this,attractionList);
        attracts.setAdapter(attractItemAdapter);
        attracts.setOnItemSelectedListener(this);

        //------------------------------------------------------------------------------------------

        countryTab = findViewById(R.id.tab4);
        attractionTab =findViewById(R.id.tab3);

        tabHost = findViewById(R.id.tabhost);

        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("tag1");
        tabSpec.setContent(R.id.tab1);
        tabSpec.setIndicator("Лекции");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setContent(R.id.tab2);
        tabSpec.setIndicator("Задания");
        tabHost.addTab(tabSpec);

            tabSpec = tabHost.newTabSpec("tag3");
            if(subject==0){
                tabSpec.setContent(R.id.tab4);
                attractionTab.setVisibility(View.GONE);
            }
            else{
                tabSpec.setContent(R.id.tab3);
                countryTab.setVisibility(View.GONE);
            }
            tabSpec.setIndicator("Доп. Материалы");
            tabHost.addTab(tabSpec);

        tabHost.setCurrentTab(0);
    }
}
