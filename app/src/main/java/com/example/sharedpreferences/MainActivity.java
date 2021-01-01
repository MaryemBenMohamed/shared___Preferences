package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private EditText mName, mPassword;
    private Button btnLogin;
    private CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mName = (EditText) findViewById(R.id.etName);
        mPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        mCheckBox = (CheckBox) findViewById(R.id.checkbox);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //mPreferences = getSharedPreferences("com.example.sharedpreferencestest", Context=MODE_PRIVTE)
        mEditor = mPreferences.edit();
        checkSharedPreferences();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //save the checkbox preferences
                if(mCheckBox.isChecked()){
                    //set a checkbox when the application starts
                    mEditor.putString(getString(R.string.chekbox), "True");
                    mEditor.commit();
                    //save the name
                    String name = mName.getText().toString();
                    mEditor.putString(getString(R.string.name), name);
                    mEditor.commit();
                    //save the password
                    String password = mPassword.getText().toString();
                    mEditor.putString(getString(R.string.password), password);
                    mEditor.commit();

                }else{
                    mEditor.putString(getString(R.string.chekbox), "False");
                    mEditor.commit();
                    //save the name
                    mEditor.putString(getString(R.string.name), "");
                    mEditor.commit();
                    //save the password
                    mEditor.putString(getString(R.string.password), "");
                    mEditor.commit();

                }
            }
        });



    }

    /**
     * check the shared preferences and set then accordingly
     */
    private void checkSharedPreferences(){
        String chekbox = mPreferences.getString(getString(R.string.chekbox),"False");
        String name = mPreferences.getString(getString(R.string.name),"");
        String password = mPreferences.getString(getString(R.string.password),"");
        mName.setText(name);
        mPassword.setText(password);
        if (chekbox.equals("True")){
            mCheckBox.setChecked(true);

        }else {
            mCheckBox.setChecked(false);
        }

    }
}