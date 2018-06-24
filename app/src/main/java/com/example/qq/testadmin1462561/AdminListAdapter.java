package com.example.qq.testadmin1462561;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sarayut on 14/6/2561.
 */
public class AdminListAdapter extends ArrayAdapter<Database_Register>{
    private Activity context;
    private int resource;
    private List<Database_Register> listImage_admin;

    public AdminListAdapter(@NonNull Activity context, int resource, @NonNull List<Database_Register> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        listImage_admin = objects;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View v = inflater.inflate(resource,null);
        TextView tvName_admin = (TextView)v.findViewById(R.id.textView_admin);


        tvName_admin.setText(listImage_admin.get(position).get_username()+"\n");
        tvName_admin.append(listImage_admin.get(position).get_telephone()+"\n");
        tvName_admin.append(listImage_admin.get(position).get_email()+"\n");

        return v;

    }
}
