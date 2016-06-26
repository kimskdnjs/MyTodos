package com.jeongjin.mytodos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editTextId, editTextPass;
    Button btnLogin;
    String correct_id = "kimskdnjs";
    String correct_pass = "happy2";
    SharedPreferences sharedPref;
    String packageName = "com.jeongjin.mytodos;";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        editTextId = (EditText) findViewById(R.id.editTextId);
        editTextPass = (EditText) findViewById(R.id.editTextPass);

        sharedPref = this.getSharedPreferences(packageName, Context.MODE_PRIVATE);
        //id하는 키가 존재하면 가져와라, 없으면 빈 칸으로 셋팅하자
        String id = sharedPref.getString("id", "");
        String pw = sharedPref.getString("pw", "");
        editTextId.setText(id);
        editTextPass.setText(pw);

        btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //start new activity
                    String id = editTextId.getText().toString();
                    String pw = editTextPass.getText().toString();
                    if (!id.equals(correct_id)) {
                            Toast.makeText(getApplicationContext(), "Id is not correct",
                                    Toast.LENGTH_SHORT).show();
                    }else if (!pw.equals(correct_pass)) {
                            Toast.makeText(getApplicationContext(), "Password is not correct",
                                    Toast.LENGTH_SHORT).show();
                    }else{
                        loginSucess(correct_id);
                    }
                }
            });
        }

    public void loginSucess(String correct_id) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("id", correct_id);
        editor.putString("pw", correct_pass);
        editor.commit();

        Activity fromActivity = MainActivity.this;
        Class toActivity = TodoActivity.class;
        Intent intent = new Intent(fromActivity, toActivity);
        intent.putExtra("id", correct_id);
        startActivity(intent);
    }
}