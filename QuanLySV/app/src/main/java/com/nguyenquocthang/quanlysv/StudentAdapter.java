package com.nguyenquocthang.quanlysv;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class StudentAdapter extends BaseAdapter {

    private MainActivity context;
    private int layout;
    private List<Student> studentList;

    public StudentAdapter(MainActivity context, int layout, List<Student> studentList) {
        this.context = context;
        this.layout = layout;
        this.studentList = studentList;
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class viewHolder{
        TextView txtTen;
        ImageView imgDel, imgEdit;
        CheckBox cbSV;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder holder;
        if (convertView == null){
            holder = new viewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder.txtTen = (TextView) convertView.findViewById(R.id.textviewTenSV);
            holder.imgEdit = (ImageView) convertView.findViewById(R.id.imgEdit);
            holder.imgDel = (ImageView) convertView.findViewById(R.id.imgDelete);
            holder.cbSV = (CheckBox) convertView.findViewById(R.id.checkBoxSV);
            convertView.setTag(holder);
        }else{
            holder = (viewHolder) convertView.getTag();
        }

        final Student student = studentList.get(position);
        holder.txtTen.setText(student.getName());
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.dialogSua(student.getName(), student.getId(), student.getGendet(), student.getDOB());
            }
        });

        holder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.dialogDel(student.getName(), student.getId());
            }
        });
        holder.cbSV.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                }
            }
        });
        return convertView;
    }


}
