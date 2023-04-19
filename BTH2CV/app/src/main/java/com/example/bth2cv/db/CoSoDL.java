package com.example.bth2cv.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.bth2cv.model.CongViec;

import java.util.ArrayList;
import java.util.List;

public class CoSoDL extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="congviec";
    private static final int DATABASE_VERSION=1;

    public CoSoDL(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE works( "+
                "id INTEGER PRIMARY KEY AUTOINCREMENT, ma TEXT, ten TEXT, noidung TEXT, ngayht TEXT, tinhtrang TEXT, congtac TEXT)";
        db.execSQL(sql);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    public List<CongViec> getAll(){
        List<CongViec> list=new ArrayList<>();
        SQLiteDatabase st=getReadableDatabase();
        String order="ngayht ASC";
        Cursor rs=st.query("works",null,null,null,null,null,order);
        while (rs!=null && rs.moveToNext()){
            int id=rs.getInt(0);
            String m=rs.getString(1);
            String t=rs.getString(2);
            String nd=rs.getString(3);
            String nht=rs.getString(4);
            String tt=rs.getString(5);
            String ct=rs.getString(6);
            list.add(new CongViec(id,m,t,nd,nht,tt,ct));
        }
        return list;
    }

    public long addCV(CongViec cv){
        ContentValues values=new ContentValues();
        values.put("ma", cv.getMa());
        values.put("ten", cv.getTen());
        values.put("noidung", cv.getNoiDung());
        values.put("ngayht", cv.getNgayHT());
        values.put("tinhtrang", cv.getTinhTrang());
        values.put("congtac", cv.getCongTac());
        SQLiteDatabase st=getWritableDatabase();
        return st.insert("works",null,values);
    }

    public long update(CongViec cv){
        ContentValues values=new ContentValues();
        values.put("ma", cv.getMa());
        values.put("ten", cv.getTen());
        values.put("noidung", cv.getNoiDung());
        values.put("ngayht", cv.getNgayHT());
        values.put("tinhtrang", cv.getTinhTrang());
        values.put("congtac", cv.getCongTac());
        String whereClause="id = ?";
        String[] whereArgs={cv.getId()+""};
        SQLiteDatabase st=getWritableDatabase();
        return st.update("works",values,whereClause,whereArgs);
    }

    public long delete(CongViec cv){
        String whereClause="id = ?";
        String[] whereArgs={cv.getId()+""};
        SQLiteDatabase st=getWritableDatabase();
        return st.delete("works",whereClause,whereArgs);
    }

    public List<CongViec> searchName(String s){
        List<CongViec> list=new ArrayList<>();
        SQLiteDatabase st=getReadableDatabase();
        String order="ngayht ASC";
        String[] selectionArgs = {"%" +s+ "%"};
        Cursor rs=st.query("works",null,"ten like ?",selectionArgs,null,null,order);
        while (rs!=null && rs.moveToNext()){
            int id=rs.getInt(0);
            String m=rs.getString(1);
            String t=rs.getString(2);
            String nd=rs.getString(3);
            String nht=rs.getString(4);
            String tt=rs.getString(5);
            String ct=rs.getString(6);
            list.add(new CongViec(id,m,t,nd,nht,tt,ct));
        }
        return list;
    }

//    ma TEXT, ten TEXT, noidung TEXT, ngayht TEXT, tinhtrang TEXT, congTac TEXT)
}
