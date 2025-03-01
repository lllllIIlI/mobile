package com.inhatc.project_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class update_member extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://android31-1685951287960-default-rtdb.firebaseio.com/");
    Button update_btn,back_btn;
    TextInputEditText update_email, update_password,update_passwordchk;
    TextView txt_nemberId ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_member);

        txt_nemberId = findViewById(R.id.update_id);

        String member_id = getIntent().getStringExtra("id");
        String password = getIntent().getStringExtra("password");
        String email = getIntent().getStringExtra("email");
        String temp = txt_nemberId.getText().toString();
        temp = temp+member_id;
        txt_nemberId.setText(temp);
        update_email = findViewById(R.id.updateEmail);
        update_password = findViewById(R.id.updatePw);
        update_passwordchk = findViewById(R.id.updatePwCHK);
        update_btn = findViewById(R.id.btn_update);

        update_email.setText(email);
        update_password.setText(password);

    back_btn = findViewById(R.id.btnBack);
    back_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    });

    databaseReference = FirebaseDatabase.getInstance().getReference("users");

    update_btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String password = update_password.getText().toString();
            String passwordchk = update_passwordchk.getText().toString();
            String email = update_email.getText().toString();
            if(!password.equals(passwordchk)){
                Toast.makeText(update_member.this, "비밀번호와 비밀번호 확인이 맞지않습니다.", Toast.LENGTH_SHORT).show();
            }else if(password.isEmpty() || email.isEmpty()){
                Toast.makeText(update_member.this, "이메일, 비밀번호중 공백입니다.", Toast.LENGTH_SHORT).show();
            }else {
                databaseReference.child(member_id).child("email").setValue(update_email.getText().toString());
                databaseReference.child(member_id).child("password").setValue(update_password.getText().toString());
                Toast.makeText(update_member.this, "수정 완료!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    });
  }
}
