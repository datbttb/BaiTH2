package com.example.bth2cv;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.bth2cv.adapter.RecycleViewAdapter;
import com.example.bth2cv.db.CoSoDL;
import com.example.bth2cv.model.CongViec;

import java.util.Calendar;

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText tvMacv, tvNoiDung, tvDate, tvTen;
    private Spinner spTinhTrang;
    private CheckBox cbCongTac;
    private Button btUpdate, btCancel, btDelete;
    private CongViec congViec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initView();
        Intent intent=getIntent();
        congViec=(CongViec) intent.getSerializableExtra("congviec");
        tvMacv.setText(congViec.getMa());
        tvTen.setText(congViec.getTen());
        tvNoiDung.setText(congViec.getNoiDung());
        tvDate.setText(congViec.getNgayHT());
        int vt=0;
        for(int i=0;i< spTinhTrang.getCount();i++){
            if(spTinhTrang.getItemAtPosition(i)==congViec.getTinhTrang()){
                vt=i;
            }
        }
        spTinhTrang.setSelection(vt);
        if(congViec.getCongTac()=="1 minh"){
            cbCongTac.setChecked(false);
        }
        else {
            cbCongTac.setChecked(true);
        }
        btUpdate.setOnClickListener(this);
        btCancel.setOnClickListener(this);
        btDelete.setOnClickListener(this);
        tvDate.setOnClickListener(this);

    }

    private void initView(){
        tvTen=findViewById(R.id.tvTencv);
        tvMacv=findViewById(R.id.tvMacv);
        tvNoiDung=findViewById(R.id.tvNoiDung);
        tvDate=findViewById(R.id.tvDate);
        cbCongTac=findViewById(R.id.cbCongTac);
        btUpdate=findViewById(R.id.btUpdate);
        btCancel=findViewById(R.id.btCancel);
        btDelete=findViewById(R.id.btDelete);
        spTinhTrang=findViewById(R.id.spTinhTrang);
        spTinhTrang.setAdapter(new ArrayAdapter<String>(this,R.layout.item_sp,getResources().getStringArray(R.array.tinhtrang)));
    }

    @Override
    public void onClick(View view) {
        if(view==tvDate){
            final Calendar c= Calendar.getInstance();
            int year=c.get(Calendar.YEAR);
            int month=c.get(Calendar.MONTH);
            int day=c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog=new DatePickerDialog(UpdateDeleteActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    String date="";
                    if(m>8){
                        date=d+"/"+(m+1)+"/"+y;
                    }
                    else {
                        date=d+"/0"+(m+1)+"/"+y;
                    }
                    tvDate.setText(date);
                }
            },year,month,day);
            dialog.show();
        }
        if(view==btCancel){
            finish();
        }
        if(view==btUpdate){
            String ma=tvMacv.getText().toString();
            String t=tvTen.getText().toString();
            String tt=spTinhTrang.getSelectedItem().toString();
            String nd=tvNoiDung.getText().toString();
            String d=tvDate.getText().toString();
            String ct="";
            if(cbCongTac.isChecked()){
                ct="Cong tac";
            }
            else {
                ct="1 minh";
            }

            if(!ma.isEmpty()){
                CongViec cv=new CongViec(congViec.getId(),ma,t,nd,d,tt,ct);
                CoSoDL cs=new CoSoDL(this);
                cs.update(cv);
                finish();
            }
        }
        if(view==btDelete){
            String ma=tvMacv.getText().toString();
            String t=tvTen.getText().toString();
            String tt=spTinhTrang.getSelectedItem().toString();
            String nd=tvNoiDung.getText().toString();
            String d=tvDate.getText().toString();
            String ct="";
            if(cbCongTac.isChecked()){
                ct="Cong tac";
            }
            else {
                ct="1 minh";
            }

            if(!ma.isEmpty()){
                CongViec cv=new CongViec(congViec.getId(),ma,t,nd,d,tt,ct);
                CoSoDL cs=new CoSoDL(this);
                cs.delete(cv);
                finish();
            }
        }
    }
}