package com.kriti.jashandemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerDemo extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList <RecyclerInfo> myList = new ArrayList<RecyclerInfo>();
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_demo);
        recyclerView = findViewById(R.id.recyclerView);

        for (int i=0; i<=5; i++)
        {
            RecyclerInfo info = new RecyclerInfo();
            info.setrImage(i);
            info.setrText(i);
            myList.add(info);
        }

        adapter = new MyAdapter();
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
    {
        class MyViewHolder extends RecyclerView.ViewHolder
        {
            TextView textView;
            ImageView imageView, emptyHeartView, fullHeartView;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.recyclerTextView);
                emptyHeartView = itemView.findViewById(R.id.recyclerEmptyHeart);
                fullHeartView = itemView.findViewById(R.id.recyclerFullHeart);
                imageView = itemView.findViewById(R.id.recyclerImageView);
            }
        }

        @NonNull
        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(RecyclerDemo.this);
            View view = inflater.inflate(R.layout.recycler_layout, parent, false);
            return new MyAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position)
        {
            RecyclerInfo info = myList.get(position);

            holder.textView.setText(info.getrText());
            holder.imageView.setImageResource(info.getrImage());

            if (info.isrHeart())
            {
                holder.emptyHeartView.setVisibility(View.GONE);
                holder.fullHeartView.setVisibility(View.VISIBLE);
            }
            else
            {
                holder.emptyHeartView.setVisibility(View.VISIBLE);
                holder.fullHeartView.setVisibility(View.GONE);
            }

            holder.emptyHeartView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Listener", "Empty heart is clicked");
                    info.setrHeart(true);
                    adapter.notifyDataSetChanged();
                }
            });

            holder.fullHeartView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Listener", "Full heart is clicked");
                    info.setrHeart(false);
                    adapter.notifyDataSetChanged();
                }
            });

        }

        @Override
        public int getItemCount() {
            return myList.size();
        }
    }

}