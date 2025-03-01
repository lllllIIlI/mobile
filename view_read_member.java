package com.inhatc.project_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class view_read_member extends AppCompatActivity {
    DatabaseReference databaseReference;
    Dialog dilaog01; // 커스텀 다이얼로그
    TextView read_email, read_pwd,read_id;
    Button btn_back,btn_update,btn_delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_read_member);

        String member_id = "id : "+getIntent().getStringExtra("id");
        String password = "password : "+getIntent().getStringExtra("password");
        String email = "email : "+getIntent().getStringExtra("email");

        String memberfromDB = getIntent().getStringExtra("id");
        String passwordfromDB = getIntent().getStringExtra("password");
        String emailfromDB = getIntent().getStringExtra("email");

        read_email = findViewById(R.id.read_email);
        read_email.setText(email);
        read_id = findViewById(R.id.read_id);
        read_id.setText(member_id);
        read_pwd = findViewById(R.id.read_password);
        read_pwd.setText(password);

        btn_update = findViewById(R.id.btn_update);
        btn_delete =findViewById(R.id.btn_delete);
        btn_back = findViewById(R.id.btn_Back);

        dilaog01 = new Dialog(view_read_member.this);       // Dialog 초기화
        dilaog01.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        dilaog01.setContentView(R.layout.activity_member_delete);

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(memberfromDB);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view_read_member.this, update_member.class);
                intent.putExtra("id",memberfromDB);
                intent.putExtra("email",emailfromDB);
                intent.putExtra("password",passwordfromDB);
                startActivity(intent);
                finish();
            }
        });
    }

    public void update_data_member(String member_id) {
        String userId = member_id.toString();

        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = databaseReference.orderByChild("Id").equalTo(userId);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    String emailfromDB = snapshot.child(userId).child("email").getValue(String.class);
                    String passwordfromDB = snapshot.child(userId).child("password").getValue(String.class);

                    Intent intent = new Intent(view_read_member.this,update_member.class);
                    intent.putExtra("id",userId);
                    intent.putExtra("email",emailfromDB);
                    intent.putExtra("password",passwordfromDB);

                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void showDialog(String member_id) {
        dilaog01.show();
        Button btnNo = dilaog01.findViewById(R.id.noBtn);
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dilaog01.dismiss();
            }
        });

        Button btnYes = dilaog01.findViewById(R.id.yesBtn);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase user = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = user.getReference().child("users").child(member_id);

                databaseReference.removeValue()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(view_read_member.this, "계정이 삭제 되었습니다.", Toast.LENGTH_LONG).show();
                                    finish();
                                    startActivity(new Intent(view_read_member.this, member_login.class));
                                } else {
                                    Toast.makeText(view_read_member.this, "계정 삭제에 실패했습니다.", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }
}
