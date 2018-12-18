package com.example.roch.kiedydojade.feature.DataBase;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.roch.kiedydojade.feature.R;

import java.util.ArrayList;
import java.util.List;

public class StationListAdapter extends ArrayAdapter<StacionModels> {
    private List<StacionModels> stacionList;
    private List<ViewHolder> holders;

    public StationListAdapter(Context _context, List<StacionModels> stacionList)
    {
        super(_context, R.layout.recyclerview, stacionList);
        holders = new ArrayList<ViewHolder>();

        this.stacionList = stacionList;
    }

    public static class ViewHolder{
        public  TextView title;
        public StacionModels stacionModels;
        public  int position;
    }

    @Override
    public int getCount() {
        if (stacionList != null)
            return stacionList.size();
        else return 0;
    }


    public void setStacionList(List<StacionModels> stacionModels){
        stacionList = stacionModels;
        notifyDataSetChanged();
    }

    public List<ViewHolder> getHolders() {return holders;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //convertView = new LinearLayout(getContext());
        String inflater = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater vi = (LayoutInflater)getContext().getSystemService(inflater);
        convertView = vi.inflate(R.layout.recyclerview_item, parent, false);

        StacionModels stacionModels = stacionList.get(position);

        ViewHolder holder = new ViewHolder();

        holder.title = (TextView)convertView.findViewById(R.id.textView);
        holder.title.setText(stacionModels.getDestinationName());

        holder.stacionModels = stacionModels;
        holder.position = position;

        holders.add(holder);
        convertView.setTag(holder);
        return convertView;
    }

}
