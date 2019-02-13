package com.usermportal.encripting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import static android.text.TextUtils.isEmpty;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button encript = (Button) findViewById(R.id.encript);
        Button decript = (Button) findViewById(R.id.decript);



        encript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, String> meMap=new HashMap<String, String>();
                meMap.put("Color1","Red");
                meMap.put("Color2","Blue");
                meMap.put("Color3","Green");
                meMap.put("Color4","White");

                String key = "0123456789abcdef";
                String iv = "fedcba9876543210";
                String data = ""+meMap;

                Java_AES_Cipher aes = new Java_AES_Cipher();
                aes.encrypt(key, iv, data);
                Log.d("encripted", ""+aes.encrypt(key, iv, data));
            }
        });

        decript.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String key = "0123456789abcdef";
                String iv = "fedcba9876543210";
                try {
                    iv = new String(Base64.encodeToString(iv.getBytes("ISO-8859-1"), Base64.DEFAULT));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String data = "Kx+c85B7tWjre4j6cdXHJQ==:"+iv;

                Java_AES_Cipher aes = new Java_AES_Cipher();
                String decrpt = aes.decrypt(key, data);
                Log.d("decpteddata", ""+decrpt);
            }
        });
    }

}
