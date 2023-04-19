package com.example.bth2cv.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bth2cv.R;
import com.example.bth2cv.UpdateDeleteActivity;
import com.example.bth2cv.adapter.RecycleViewAdapter;
import com.example.bth2cv.db.CoSoDL;
import com.example.bth2cv.model.CongViec;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FragmentHome extends Fragment implements RecycleViewAdapter.ItemListener{

    private RecyclerView rcHome;
    private RecycleViewAdapter adapter;
    private CoSoDL coSoDL;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcHome=view.findViewById(R.id.rcHome);
        adapter=new RecycleViewAdapter();
        coSoDL=new CoSoDL(getContext());
        List<CongViec> cvl=coSoDL.getAll();
        adapter.setList(cvl);
        LinearLayoutManager manager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rcHome.setLayoutManager(manager);
        rcHome.setAdapter(adapter);
        adapter.setItemListener(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        System.out.println("OK");
        CongViec item=adapter.getCV(position);
        Intent intent=new Intent(getActivity(), UpdateDeleteActivity.class);
        intent.putExtra("congviec", item);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<CongViec> cvl=coSoDL.getAll();
        adapter.setList(cvl);
    }
}
