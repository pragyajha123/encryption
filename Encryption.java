package com.example.pragya.encryption;

import android.app.Activity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class Encryption extends Activity {
    static final String TAG = "SymmetricAlgorithmAES";

    EditText text1;
    Button en;
    Button en1;
    public SecretKeySpec sks = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryption);

        text1 = (EditText) findViewById(R.id.text);
        en = (Button) findViewById(R.id.en);
        en1 = (Button) findViewById(R.id.en1);

        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed("any data used as random seed".getBytes());
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128, sr);
            sks = new SecretKeySpec((kg.generateKey()).getEncoded(), "AES");
        } catch (Exception e) {
            Log.e(TAG, "AES secret key spec error");
        }

        en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String theTestText = String.valueOf(text1.getText());

                byte[] data = new byte[0];
                try {
                    data = theTestText.getBytes("UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String text = Base64.encodeToString(data, Base64.DEFAULT);

                text1.setText(text);
            }
        });

        en1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String theTest = String.valueOf(text1.getText());

                byte[] encodedBytes = null;
                try {
                    Cipher c = Cipher.getInstance("AES");
                    c.init(Cipher.ENCRYPT_MODE, sks);
                    encodedBytes = c.doFinal(theTest.getBytes());
                } catch (Exception e) {
                    Log.e(TAG, "AES encryption error");
                }

                text1.setText(
                        encodedBytes + "\n"+sks+"");
            }
        });
    }
}
