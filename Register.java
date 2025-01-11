package com.inhatc.project_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://android31-1685951287960-default-rtdb.firebaseio.com/");

    private TextInputEditText editTextEmail,editTextPwd,editTextID,editTextpwdCheck;
    private Button btnReg,btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextEmail = findViewById(R.id.registEmail);
        editTextpwdCheck = findViewById(R.id.registPwCHK);
        editTextPwd = findViewById(R.id.registPw);
        editTextID = findViewById(R.id.registId);
        btnReg = findViewById(R.id.btn_regist);
        btnLogin = findViewById(R.id.btnBack);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Id = editTextID.getText().toString();
                final String email = editTextEmail.getText().toString();
                final String password = editTextPwd.getText().toString();
                final String passwordChk = editTextpwdCheck.getText().toString();

                //회원가입시 아무것도 기입을 하지않았을때,
                if(Id.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Register.this,"아이디, 비밀번호, 이메일을 기입하지않았습니다.",Toast.LENGTH_SHORT).show();
                    return;
                } else if (!password.equals(passwordChk)) {
                    Toast.makeText(Register.this, "비밀번호와 비밀번호 확인이 일치하지않습니다.", Toast.LENGTH_SHORT).show();
                }else {
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(snapshot.hasChild(Id)) {
                                Toast.makeText(Register.this, "ID가 이미 존재합니다.", Toast.LENGTH_SHORT).show();
                            }else {
                                databaseReference.child("users").child(Id).child("Id").setValue(Id);
                                databaseReference.child("users").child(Id).child("email").setValue(email);
                                databaseReference.child("users").child(Id).child("password").setValue(password);

                                Toast.makeText(Register.this, "회원가입을 성공하였습니다.", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
