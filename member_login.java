package com.inhatc.project_android;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class member_login extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://android31-1685951287960-default-rtdb.firebaseio.com/");

    private static final String SITE_KEY = "6LdOrG8mAAAAAHiTICpenpiBUGV2Tx5jP9l5tTj_";
    private static final String TAG = "member_login";
    private EditText ID;
    private EditText password;
    private Button login_btn;
    private Button btn_signUp;
    private CheckBox cappbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_login);

        ID = findViewById(R.id.input_Id);
        password = findViewById(R.id.input_Password);
        cappbutton= findViewById(R.id.Cappbutton);
        btn_signUp = findViewById(R.id.btn_signUp);
        login_btn = findViewById(R.id.btn_login);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = ID.getText().toString();
                String pwd = password.getText().toString();

                if(id.isEmpty() || pwd.isEmpty()) {
                    Toast.makeText(member_login.this, "아이디나 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                }else {
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //회원이  존재할때
                            if(snapshot.hasChild(id)) {
                                String getpwd = snapshot.child(id).child("password").getValue(String.class);
                                if(getpwd.equals(pwd)) {
                                    Toast.makeText(member_login.this,"로그인 성공!",Toast.LENGTH_SHORT).show();

                                    startActivity(new Intent(member_login.this,MainActivity.class).putExtra("id",id));
                                    finish();
                                }else {
                                    Toast.makeText(member_login.this,"ID가 없거나 비밀번호가 없습니다.",Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(member_login.this,"ID가 없거나 비밀번호가 없습니다.",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(member_login.this,Register.class);
                startActivity(register);
            }
        });

        cappbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processLogin();
            }
        });
    }

    private void processLogin() {
        SafetyNet.getClient(this).verifyWithRecaptcha(SITE_KEY)
                .addOnSuccessListener(new OnSuccessListener<SafetyNetApi.RecaptchaTokenResponse>() {
                    @Override
                    public void onSuccess(SafetyNetApi.RecaptchaTokenResponse recaptchaTokenResponse) {
                        String captchaToken = recaptchaTokenResponse.getTokenResult();

                        if (captchaToken != null && !captchaToken.isEmpty()) {
                            String id = ID.getText().toString();
                            String pwd = password.getText().toString();
                            processLoginStep(captchaToken, id, pwd);
                        } else {
                            Toast.makeText(member_login.this, "Invalid Captcha Response", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(member_login.this, "Failed to Load Captcha", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void processLoginStep(String token, String id, String pwd) {
        Log.d(TAG, "Captcha Token: " + token);
        // 로그인 처리를 진행하세요.
    }
}
