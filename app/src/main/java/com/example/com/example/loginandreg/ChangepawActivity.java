package com.example.com.example.loginandreg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.R;

public class ChangepawActivity extends AppCompatActivity {

    private Button btn_queding;
    private EditText et_user_name,et_psw;
    private String userName,psw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepaw);
        init();
    }

    private void init() {
        btn_queding = (Button)findViewById(R.id.btn_queding);
        et_user_name =(EditText) findViewById(R.id.user_name);
        et_psw =(EditText)findViewById(R.id.psw_again);
        Drawable icon1 = getResources().getDrawable(R.mipmap.user_name);
        Drawable icon2 = getResources().getDrawable(R.mipmap.psw);
        icon1.setBounds(0,0,100,100);
        icon2.setBounds(0,0,100,100);
        et_user_name.setCompoundDrawables(icon1, null, null, null);
        et_psw.setCompoundDrawables(icon2, null, null, null);
        btn_queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getEditString();
                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(ChangepawActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }else if(TextUtils.isEmpty(psw)){
                    Toast.makeText(ChangepawActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }else if (isExistUserName(userName)){
                    saveRegisterInfo(userName, psw);
                    Intent intent = new Intent(ChangepawActivity.this,LoginActivity.class);

                    Intent data = new Intent();
                    data.putExtra("userName", userName);
                    setResult(RESULT_OK, data);
                    startActivity(intent);
                    ChangepawActivity.this.finish();
                }
            }
        });
    }
    private void getEditString(){
        userName=et_user_name.getText().toString().trim();
        psw=et_psw.getText().toString().trim();
    }
    /**
     * 从SharedPreferences中读取输入的用户名，判断SharedPreferences中是否有此用户名
     */
    private boolean isExistUserName(String userName){
        boolean has_userName=false;
        //mode_private SharedPreferences sp = getSharedPreferences( );
        // "loginInfo", MODE_PRIVATE
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        //获取密码
        String spPsw=sp.getString(userName, "");//传入用户名获取密码
        //如果密码不为空则确实保存过这个用户名
        if(!TextUtils.isEmpty(spPsw)) {
            has_userName=true;
        }
        return has_userName;
    }
    /**
     * 保存账号和密码到SharedPreferences中SharedPreferences
     */
    private void saveRegisterInfo(String userName,String psw){
        String md5Psw = MD5Utils.md5(psw);//把密码用MD5加密
        //loginInfo表示文件名, mode_private SharedPreferences sp = getSharedPreferences( );
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        //获取编辑器， SharedPreferences.Editor  editor -> sp.edit();
        SharedPreferences.Editor editor=sp.edit();
        //以用户名为key，密码为value保存在SharedPreferences中
        //key,value,如键值对，editor.putString(用户名，密码）;
        editor.putString(userName, md5Psw);
        //提交修改 editor.commit();
        editor.commit();
    }
}
