package com.kriti.jashandemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MusicList extends AppCompatActivity {

    ListView listView;
    int[] imagesList = {R.drawable.one, R.drawable.two, R.drawable.three,
            R.drawable.four, R.drawable.five, R.drawable.six};

    ArrayList<String> textList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);

        listView = findViewById(R.id.musicListView);

        textList.add("One");
        textList.add("Two");
        textList.add("Three");
        textList.add("Four");
        textList.add("Five");
        textList.add("Six");

        MyAdapter adapter = new MyAdapter();
        listView.setAdapter(adapter);
    }

    class MyAdapter extends BaseAdapter
    {
        @Override
        public int getCount() {
            return imagesList.length;
        }

        @Override
        public Object getItem(int i) {
            return imagesList[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null)
            {
                view = getLayoutInflater().inflate(R.layout.custom_layout,  viewGroup, false);
            }

            ImageView imageView = view.findViewById(R.id.customImageView);
            TextView textView = view.findViewById(R.id.customTextView);

            imageView.setImageResource(imagesList[i]);
            textView.setText(textList.get(i));

            return view;
        }
    }

}