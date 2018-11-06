package com.nguyenquocthang.quanlysv;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Database database;
    ListView lvStudent;
    ArrayList<Student> studentArrayList;
    StudentAdapter adapter;
    Button btnAdd, btnShow, btnCount;

    public void anhXa(){
        lvStudent = (ListView) findViewById(R.id.lvstudents);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnCount = (Button) findViewById(R.id.btnAdd);
        btnShow = (Button) findViewById(R.id.btnShow);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anhXa();

        studentArrayList = new ArrayList<>();
        adapter = new StudentAdapter(this, R.layout.dong_student, studentArrayList);
        lvStudent.setAdapter(adapter);

        database = new Database(this, "StudentDB.sql", null, 1);

        //Tao Table
        database.queryData("CREATE TABLE IF NOT EXISTS students(Id INTEGER PRIMARY KEY AUTOINCREMENT, Name NVARCHAR(80), Gender NVARCHAR(5), DOB VARCHAR(15))");

        //Add data
        //database.queryData("INSERT INTO students VALUES (null, 'Nguyyễn Quốc Thắng', 'Nam', '1997/7/6')");
        getStudent();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAdd();
            }
        });
    }

    private void getStudent(){
        Cursor dataStudents = database.getData("SELECT * FROM students ORDER BY Id DESC");
        studentArrayList.clear();
        while (dataStudents.moveToNext()){
            int id = dataStudents.getInt(0);
            String ten = dataStudents.getString(1);
            String gender = dataStudents.getString(2);
            String dob = dataStudents.getString(3);
            studentArrayList.add(new Student(id, ten, gender, dob));
            /*Toast.makeText(this, ten + " " + dob, Toast.LENGTH_SHORT).show();*/
        }
        adapter.notifyDataSetChanged();
    }

    public void dialogSua(String ten, final int id, String gender, String dob){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_edit);

        final EditText edtSua = dialog.findViewById(R.id.edtSuaSV);
        final EditText edtGender = dialog.findViewById(R.id.edtGendetSV);
        final EditText edtDOB = dialog.findViewById(R.id.edtDOBSV);
        Button btnHuy = dialog.findViewById(R.id.btnHuy);
        Button btnSua = dialog.findViewById(R.id.btnXacNhan);

        edtSua.setText(ten);
        edtGender.setText(gender);
        edtDOB.setText(dob);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenMoi = edtSua.getText().toString().trim();
                String gioitinhmoi = edtGender.getText().toString().trim();
                String dobmoi = edtDOB.getText().toString().trim();
                database.queryData("UPDATE students SET Name = '"+ tenMoi +"', Gender = '"+ gioitinhmoi +"', DOB = '"+ dobmoi +"' WHERE Id = '"+ id +"'");
                Toast.makeText(MainActivity.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                getStudent();
            }
        });
        dialog.show();
    }

    public void dialogDel(final String ten, final  int id){
        AlertDialog.Builder dialogdel = new AlertDialog.Builder(this);
        dialogdel.setMessage("Bạn có muốn xóa sinh viên " + ten + " không?");
        dialogdel.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogdel.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                database.queryData("DELETE FROM students WHERE Id = '"+ id +"'");
                Toast.makeText(MainActivity.this, "Xóa sinh viên " + ten + " thành công!", Toast.LENGTH_SHORT).show();
                getStudent();
            }
        });
        dialogdel.show();
    }

    public  void dialogAdd(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add);

        final EditText edtAddName = dialog.findViewById(R.id.edtAddName);
        final EditText edtAddGender = dialog.findViewById(R.id.edtAddGenderSV);
        final EditText edtAddDOB = dialog.findViewById(R.id.edtAddDOBSV);
        Button btnAdd = dialog.findViewById(R.id.btnAdd);
        Button btnCancle = dialog.findViewById(R.id.btnCancle);

        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = edtAddName.getText().toString().trim();
                String gioitinh = edtAddGender.getText().toString().trim();
                String dob = edtAddDOB.getText().toString().trim();
                database.queryData("INSERT INTO students VALUES (null, '"+ ten +"', '"+ gioitinh +"', '"+ dob +"')");
                Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                getStudent();
            }
        });
        dialog.show();
    }



}
