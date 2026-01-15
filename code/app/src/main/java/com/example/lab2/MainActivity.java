package com.example.lab2;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<City> cityAdapter;
    ArrayList<City> dataList;
    City selectedCity;

    Button addCity;
    Button deleteCity;
    EditText cityNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cityList = findViewById(R.id.city_list);
        cityNameText = findViewById(R.id.enter_city_text_field);
        addCity = findViewById(R.id.delete_city_button);
        deleteCity = findViewById(R.id.add_city_button);

        dataList = new ArrayList<>();
        dataList.add(new City("test"));
        selectedCity = dataList.get(0);
        Log.d(TAG, String.format("dataList: %s", dataList.toString()));
        Log.d(TAG, String.format("delectedCity: %s", selectedCity.toString()));

        cityAdapter = new ArrayAdapter<City>(this, R.layout.content, dataList);
        Log.d(TAG, String.format("cityAdapter: %s", cityAdapter.toString()));

        Log.d(TAG, String.format("cityList: %s", cityList.toString()));
        cityList.setAdapter(cityAdapter);
        Log.d(TAG, String.format("cityList: %s", cityList.toString()));

        // https://codingtechroom.com/question/-retrieve-selected-item-listview-android
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedCity = (City) parent.getItemAtPosition(position);
            }
        });

        addCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataList.remove(selectedCity);
                cityAdapter.notifyDataSetChanged();
            }
        });

        deleteCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = cityNameText.getText().toString();
                dataList.add(new City(cityName));
                cityAdapter.notifyDataSetChanged();

//                Toast myToast = Toast.makeText(getActivity(), R.string.toast_text, Toast.LENGTH_SHORT);
//                myToast.show();
            }
        });

    }

}