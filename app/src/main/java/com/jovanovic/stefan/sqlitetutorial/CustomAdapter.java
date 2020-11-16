package com.jovanovic.stefan.sqlitetutorial;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList movie_id, movie_name, movie_type, movie_runtime;

    CustomAdapter(Activity activity, Context context, ArrayList movie_id, ArrayList movie_name, ArrayList movie_type,
                  ArrayList movie_runtime){
        this.activity = activity;
        this.context = context;
        this.movie_id = movie_id;
        this.movie_name = movie_name;
        this.movie_type = movie_type;
        this.movie_runtime = movie_runtime;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.movie_id_txt.setText(String.valueOf(movie_id.get(position)));
        holder.movie_name_txt.setText(String.valueOf(movie_name.get(position)));
        holder.movie_type_txt.setText(String.valueOf(movie_type.get(position)));
        holder.movie_runtime_txt.setText(String.valueOf(movie_runtime.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(movie_id.get(position)));
                intent.putExtra("name", String.valueOf(movie_name.get(position)));
                intent.putExtra("type", String.valueOf(movie_type.get(position)));
                intent.putExtra("runtime", String.valueOf(movie_runtime.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return movie_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView movie_id_txt, movie_name_txt, movie_type_txt, movie_runtime_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            movie_id_txt = itemView.findViewById(R.id.movie_id_txt);
            movie_name_txt = itemView.findViewById(R.id.movie_name_txt);
            movie_type_txt = itemView.findViewById(R.id.movie_type_txt);
            movie_runtime_txt = itemView.findViewById(R.id.movie_runtime_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
           /* Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);*/
        }

    }

}
