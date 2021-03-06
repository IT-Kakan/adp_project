package com.recyclerush.group5.recyclerush;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ScoreboardAdapter extends ArrayAdapter {
    private List<User> userList;
    private Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView textViewPlace;
        TextView textViewName;
        TextView textViewScore;
    }

    public ScoreboardAdapter(List<User> users, Context context){
        super(context, R.layout.scoreboard_item_view, users);
        this.userList = users;
        this.mContext = context;
    }

    private int lastPosition = -1;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         // Get the data item for this position
        User user = (User)getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

      //  final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.scoreboard_item_view, parent, false);
            viewHolder.textViewPlace = convertView.findViewById(R.id.textViewPlace);
            viewHolder.textViewName = convertView.findViewById(R.id.textViewName);
            viewHolder.textViewScore = convertView.findViewById(R.id.textViewScore);

           // result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
          //  result=convertView;
        }

        lastPosition = position;

        if (position != 0) {
            viewHolder.textViewPlace.setText((position+1)+".");
            viewHolder.textViewName.setText(user.getUserName());
            viewHolder.textViewScore.setText(user.getScore()+"");
        } else{
            viewHolder.textViewPlace.setText("Place");
            viewHolder.textViewName.setText("Name");
            viewHolder.textViewScore.setText("Score");
        }

        Log.d("ScoreboardAdapter", "Score: "+user.getScore());

        // Return the completed view to render on screen
        return convertView;
    }
}
