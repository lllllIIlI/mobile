package com.inhatc.project_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class product_detail extends AppCompatActivity {
    private TextView minus_per, minus_shot, minus_shirup, plus_per, plus_shot, plus_shirup, per_status, shot_status, shirup_status, shirup_price, shot_price, per_price;
    private Button btn_cart, btncanecl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        btn_cart = findViewById(R.id.btn_cart);
        minus_shirup = findViewById(R.id.minus_shrup);
        minus_per = findViewById(R.id.minus_per);
        minus_shot = findViewById(R.id.minus_shot);
        plus_per = findViewById(R.id.plus_per);
        plus_shot = findViewById(R.id.plus_shot);
        plus_shirup = findViewById(R.id.plus_shirup);
        per_status = findViewById(R.id.per_Status);
        shot_status = findViewById(R.id.shot_Status);
        shirup_status = findViewById(R.id.shirup_Status);
        shirup_price = findViewById(R.id.shirup_price);
        shot_price = findViewById(R.id.shot_price);
        per_price = findViewById(R.id.per_price);
        btncanecl=findViewById(R.id.btncancel);

        minus_shirup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = shirup_status.getText().toString();
                int statut = Integer.parseInt(status);
                if (statut >= 1) {
                    statut = statut - 1;
                    shirup_status.setText(String.valueOf(statut));
                }
            }
        });

        minus_shot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = shot_status.getText().toString();
                int statut = Integer.parseInt(status);
                if (statut >= 1) {
                    statut = statut - 1;
                    shot_status.setText(String.valueOf(statut));
                }
            }
        });

        minus_per.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = per_status.getText().toString();
                int statut = Integer.parseInt(status);
                if (statut >= 1) {
                    statut = statut - 1;
                    per_status.setText(String.valueOf(statut));
                }
            }
        });

        plus_shirup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = shirup_status.getText().toString();
                int statut = Integer.parseInt(status);
                if (statut >= 0) {
                    statut = statut + 1;
                    shirup_status.setText(String.valueOf(statut));
                }
            }
        });

        plus_per.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = per_status.getText().toString();
                int statut = Integer.parseInt(status);
                if (statut >= 0) {
                    statut = statut + 1;
                    per_status.setText(String.valueOf(statut));
                }
            }
        });

        plus_shot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = shot_status.getText().toString();
                int statut = Integer.parseInt(status);
                if (statut >= 0) {
                    statut = statut + 1;
                    shot_status.setText(String.valueOf(statut));
                }
            }
        });

        btncanecl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String menu = intent.getStringExtra("productName");
                int price = intent.getIntExtra("productPrice", 0);
                int quantity = intent.getIntExtra("quantity", 1);
                int perQuantity = Integer.parseInt(per_status.getText().toString());
                int shotQuantity = Integer.parseInt(shot_status.getText().toString());
                int shirupQuantity = Integer.parseInt(shirup_status.getText().toString());
                int sum = (perQuantity * 700) + (shotQuantity * 500) + (shirupQuantity * 500) + price;

                intent = new Intent(product_detail.this, proudct_list.class);
                intent.putExtra("menu", menu);
                intent.putExtra("price", sum);
                intent.putExtra("quantity", quantity);
                startActivity(intent);
            }
        });
    }
}
