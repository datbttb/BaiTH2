package com.example.bth2cv.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.appcompat.widget.SearchView;

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

import java.util.List;

public class FragmentSearch extends Fragment implements RecycleViewAdapter.ItemListener {

    private RecyclerView rcSearch;
    private RecycleViewAdapter adapter;
    private CoSoDL coSoDL;
    private Button btSearch;
    private SearchView svsearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcSearch=view.findViewById(R.id.recycleView);
        adapter=new RecycleViewAdapter();
        coSoDL=new CoSoDL(getContext());
        List<CongViec> cvl=coSoDL.getAll();
        adapter.setList(cvl);
        LinearLayoutManager manager=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rcSearch.setLayoutManager(manager);
        rcSearch.setAdapter(adapter);
        adapter.setItemListener(this);
        btSearch=view.findViewById(R.id.btSearch);
        svsearch=view.findViewById(R.id.svsearch);
        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ttk=svsearch.getQuery().toString();
                List<CongViec> cvl1=coSoDL.searchName(ttk);
                adapter.setList(cvl1);
            }
        });
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
