package com.inhatc.project_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class proudct_list extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyPagerAdapter pagerAdapter;

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private List<CartItem> cartItems = new ArrayList<>();


    private Button btnDeleteAll;
    private Button btnCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proudct_list);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        recyclerView = findViewById(R.id.recycler_view);
        btnDeleteAll = findViewById(R.id.btn_delete_all);
        btnCheckout = findViewById(R.id.btn_checkout);



        // 탭 추가
        tabLayout.addTab(tabLayout.newTab().setText("전체 메뉴"));
        tabLayout.addTab(tabLayout.newTab().setText("커피"));
        tabLayout.addTab(tabLayout.newTab().setText("쥬스"));

        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        // 탭 변경 이벤트 리스너 설정
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (viewPager != null) {
                    viewPager.setCurrentItem(tab.getPosition());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        // RecyclerView 설정
        cartItems = new ArrayList<>();
        cartAdapter = new CartAdapter(cartItems, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cartAdapter);
        Intent intent = getIntent();
        if (intent != null) {
            String menu = intent.getStringExtra("menu");
            int quantity = intent.getIntExtra("quantity", 0);
            int price = intent.getIntExtra("price", 0);

            // 장바구니에 상품 추가
            addCartItem(menu, quantity, price);
        }

        // 전체삭제 버튼 클릭 이벤트 리스너
        btnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartItems.clear();
                cartAdapter.notifyDataSetChanged();
                Toast.makeText(proudct_list.this, "장바구니가 비워졌습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        // 결제하기 버튼 클릭 이벤트 리스너
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 결제 처리 로직 작성
                Intent intent = new Intent(proudct_list.this,credit_cart.class);
                startActivity(intent);
            }
        });
    }

    // 장바구니 아이템 추가 메서드
    public void addCartItem(String menu, int quantity, int price) {
        boolean isExistingItem = false;
        int existingItemPosition = -1;

        // 동일한 메뉴 항목이 있는지 확인하고 위치를 찾음
        for (int i = 0; i < cartItems.size(); i++) {
            if (cartItems.get(i).getMenu().equals(menu)) {
                isExistingItem = true;
                existingItemPosition = i;
                break;
            }
        }

        if (isExistingItem) {
            // 기존 항목의 수량을 업데이트
            CartItem existingItem = cartItems.get(existingItemPosition);
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            cartAdapter.notifyItemChanged(existingItemPosition);
        } else {
            // 새로운 항목을 추가
            CartItem cartItem = new CartItem(menu, quantity, price);
            cartItems.add(cartItem);
            cartAdapter.notifyItemInserted(cartItems.size() - 1);
        }
    }
}
