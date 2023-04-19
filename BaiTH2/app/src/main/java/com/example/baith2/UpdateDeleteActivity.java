package com.example.baith2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.baith2.dal.SQLiteHelper;
import com.example.baith2.model.Item;

import java.util.Calendar;

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener {

    public Spinner sp;
    private EditText eTitle,ePrice,eDate;
    private Button btUpdate, btCancel, btDelete;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        initView();
        btCancel.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
        btDelete.setOnClickListener(this);
        eDate.setOnClickListener(this);
        Intent intent=getIntent();
        item=(Item) intent.getSerializableExtra("item");
        eTitle.setText(item.getTitle());
        ePrice.setText(item.getPrice());
        eDate.setText(item.getDate());
        int p=0;
        for (int i=0; i<sp.getCount(); i++){
            if(sp.getItemAtPosition(i).toString()==item.getDate()){
                p=i;
            }
        }
        sp.setSelection(p);

    }

    private void initView(){
        sp=findViewById(R.id.spCategory);
        eTitle=findViewById(R.id.tvTitle);
        ePrice=findViewById(R.id.tvPrice);
        eDate=findViewById(R.id.tvDate);
        btUpdate=findViewById(R.id.btUpdate);
        btCancel=findViewById(R.id.btCancel);
        btDelete=findViewById(R.id.btDelete);
        sp.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner, getResources().getStringArray(R.array.category)));
    }

    @Override
    public void onClick(View view) {
        if(view==eDate){
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
                    eDate.setText(date);
                }
            },year,month,day);
            dialog.show();
        }
        if(view==btCancel){
            finish();
        }
        if(view==btUpdate){
            int id=item.getId();
            String t=eTitle.getText().toString();
            String p=ePrice.getText().toString();
            String c=sp.getSelectedItem().toString();
            String d=eDate.getText().toString();
            if(!t.isEmpty() && p.matches("\\d+")){
                Item i=new Item(id,t,c,p,d);
                SQLiteHelper db=new SQLiteHelper(this);
                db.update(i);
                finish();
            }
        }
        if(view==btDelete){
            int id=item.getId();
            String t=eTitle.getText().toString();
            String p=ePrice.getText().toString();
            String c=sp.getSelectedItem().toString();
            String d=eDate.getText().toString();
            if(!t.isEmpty() && p.matches("\\d+")){
                Item i=new Item(id,t,c,p,d);
                SQLiteHelper db=new SQLiteHelper(this);
                db.delete(i);
                finish();
            }
        }
    }
}