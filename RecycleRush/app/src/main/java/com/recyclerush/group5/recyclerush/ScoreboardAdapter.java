package com.recyclerush.group5.recyclerush;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ScoreboardAdapter extends ArrayAdapter {
    private ArrayList<User> userList;
    private Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView textViewPlace;
        TextView textViewName;
        TextView textViewScore;
    }

    public ScoreboardAdapter(ArrayList<User> users, Context context){
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
            viewHolder.textViewPlace = (TextView) convertView.findViewById(R.id.textViewPlace);
            viewHolder.textViewName = (TextView) convertView.findViewById(R.id.textViewName);
            viewHolder.textViewScore = (TextView) convertView.findViewById(R.id.textViewScore);

           // result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
          //  result=convertView;
        }

        lastPosition = position;

        viewHolder.textViewPlace.setText((position+1)+".");
        viewHolder.textViewName.setText(user.getUserName());
        viewHolder.textViewScore.setText(user.getScore()+"");

        Log.d("XXX", "score: "+user.getScore());

        // Return the completed view to render on screen
        return convertView;
    }
}
