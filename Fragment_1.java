package com.inhatc.project_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Fragment_1 extends Fragment {
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment1, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));


        productList = new ArrayList<>();
        // 초기 데이터 추가
        productList.add(new Product("흑당밀크티", 1500, R.drawable.coffe,"1"));
        productList.add(new Product("상품 2", 2000, R.drawable.coffe,"2"));
        productList.add(new Product("상품 3", 3000, R.drawable.coffe,"3"));
        productList.add(new Product("상품 4", 4000, R.drawable.coffe,"4"));
        productList.add(new Product("상품 5", 5000, R.drawable.coffe,"5"));
        productList.add(new Product("상품 6", 6000, R.drawable.coffe,"6"));

        productAdapter = new ProductAdapter(productList);
        recyclerView.setAdapter(productAdapter);

        productAdapter.notifyDataSetChanged(); // 어댑터에 변경 사항 알림

        return view;
    }
}
