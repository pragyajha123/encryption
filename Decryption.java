package com.example.pragya.encryption.encryption;

import android.os.Bundle;
import android.text.Editable;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.UnsupportedEncodingException;
import javax.crypto.Cipher;

public class Decryption extends Encryption {

    static final String TAG = "SymmetricAlgorithmAES";

    EditText text1;
    Editable t1;
    String t;
    Button en;
    Button en1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decryption);

        text1 = (EditText) findViewById(R.id.text);
        en = (Button) findViewById(R.id.en);
        en1 = (Button) findViewById(R.id.en1);

        en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                t1 = text1.getText();
                t = String.valueOf(t1);
                byte[] data = Base64.decode(t, Base64.DEFAULT);
                try {
                    String t2 = new String(data, "UTF-8");
                    text1.setText(t2);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });

        en1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String theTest = String.valueOf(text1.getText());

                byte[] decodedBytes = null;
                try {
                    Cipher c = Cipher.getInstance("AES");
                    c.init(Cipher.DECRYPT_MODE, sks);
                    decodedBytes = c.doFinal(theTest.getBytes());
                } catch (Exception e) {
                    Log.e(TAG, "AES decryption error");
                }

                text1.setText(""+decodedBytes+"\n"+sks);
            }
        });
    }
}

