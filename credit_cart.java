package com.inhatc.project_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class credit_cart extends AppCompatActivity {
    Button cardCredit, payPalcredit,cancel;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_carty);

        cardCredit = findViewById(R.id.cardButton);
        payPalcredit = findViewById(R.id.PayPalbutton);

        cardCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(credit_cart.this, "결제완료", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(credit_cart.this, ordered.class);
                startActivity(intent);
                finish();
            }
        });
        
        payPalcredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(credit_cart.this, "결제완료", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(credit_cart.this, ordered.class);
                startActivity(intent);
                finish();
            }
        });
        
        cancel = findViewById(R.id.cancel_button);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
