package com.inhatc.project_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://android31-1685951287960-default-rtdb.firebaseio.com/");
    Button btn_order_list;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    Dialog dilaog01; // 커스텀 다이얼로그

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayShowTitleEnabled(false);
        drawerLayout=findViewById(R.id.layout_drawer);
        navigationView=findViewById(R.id.nav);
        //네비게이션뷰의 메뉴 아이콘의 색조 제거
        navigationView.setItemIconTintList(null);

        //왼쪽에 메뉴 생성 
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerToggle.syncState();
        drawerLayout.addDrawerListener(drawerToggle);

        //전에 있던 intent값 가져오기 key 는 id
        String member_id = getIntent().getStringExtra("id");

        dilaog01 = new Dialog(MainActivity.this);       // Dialog 초기화
        dilaog01.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        dilaog01.setContentView(R.layout.activity_member_delete);

        btn_order_list = findViewById(R.id.btn_order);
        btn_order_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, proudct_list.class);
                startActivity(intent);
                finish();
            }
        });

        //드로우어 클릭시 화면 이동
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int item_id = item.getItemId();
                if(item_id==R.id.menu_update) {
                    //startActivity(new Intent(MainActivity.this,update_member.class).putExtra("id",member_id));
                    update_data_member(member_id);
                }
                if(item_id==R.id.menu_infromation) {
                    read_data_member(member_id);
                }if(item_id==R.id.menu_delete) {
                    showDialog(member_id);
                }

                return true;
            }
        });

        //액션바에 제목이 자동 표시 되지 않음
        getSupportActionBar().setDisplayShowTitleEnabled(false);

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
                                    Toast.makeText(MainActivity.this, "계정이 삭제 되었습니다.", Toast.LENGTH_LONG).show();
                                    finish();
                                    startActivity(new Intent(MainActivity.this, member_login.class));
                                } else {
                                    Toast.makeText(MainActivity.this, "계정 삭제에 실패했습니다.", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
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

                    Intent intent = new Intent(MainActivity.this,update_member.class);
                    intent.putExtra("id",userId);
                    intent.putExtra("email",emailfromDB);
                    intent.putExtra("password",passwordfromDB);

                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void read_data_member(String member_id) {
        String userId = member_id.toString();

        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = databaseReference.orderByChild("Id").equalTo(userId);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {
                    String emailfromDB = snapshot.child(userId).child("email").getValue(String.class);
                    String passwordfromDB = snapshot.child(userId).child("password").getValue(String.class);

                    Intent intent = new Intent(MainActivity.this,view_read_member.class);
                    intent.putExtra("id",userId);
                    intent.putExtra("email",emailfromDB);
                    intent.putExtra("password",passwordfromDB);

                    startActivity(intent);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
