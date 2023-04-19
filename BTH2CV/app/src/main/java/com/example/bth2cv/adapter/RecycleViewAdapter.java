package com.example.bth2cv.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bth2cv.R;
import com.example.bth2cv.model.CongViec;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.RecycleViewHolder> {

    private List<CongViec> list;
    private ItemListener itemListener;

    public RecycleViewAdapter() {
        this.list = new ArrayList<>();
    }

    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    public void setList(List<CongViec> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public CongViec getCV(int vitri){
        return list.get(vitri);
    }



    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);

        return new RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
        CongViec cv=getCV(position);
        holder.tvMacv.setText(cv.getMa());
        holder.tvTenCv.setText(cv.getTen());
        holder.tvNoiDung.setText("Noi dung: "+cv.getNoiDung());
        holder.tvDate.setText("Han: "+cv.getNgayHT());
        holder.tvTinhTrang.setText("Tinh trang: "+cv.getTinhTrang());
        holder.tvCongTac.setText(cv.getCongTac());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView tvMacv, tvTenCv, tvNoiDung, tvDate, tvTinhTrang, tvCongTac;
        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCongTac=itemView.findViewById(R.id.tviCongTac);
            tvMacv=itemView.findViewById(R.id.tviMacv);
            tvTenCv=itemView.findViewById(R.id.tviTencv);
            tvNoiDung=itemView.findViewById(R.id.tviNoiDung);
            tvDate=itemView.findViewById(R.id.tviDate);
            tvTinhTrang=itemView.findViewById(R.id.tviTinhTrang);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemListener.onItemClick(itemView,getAdapterPosition());
        }
    }

    public interface ItemListener{
        void onItemClick(View view, int position);
    }
}
