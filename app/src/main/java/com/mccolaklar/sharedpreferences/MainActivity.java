package com.mccolaklar.sharedpreferences;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText stringValueET,intValueET;
    CheckBox trueFalse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Referanslar
        stringValueET = (EditText) findViewById(R.id.editText);
        intValueET = (EditText) findViewById(R.id.editText2);
        trueFalse = (CheckBox) findViewById(R.id.checkBox);
    }

    //Butona Tıklanınca
    public void onClick (View view){
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        if(view.getId()==R.id.button){
            String stringValue = stringValueET.getText().toString();
            String intValue = intValueET.getText().toString();
            Boolean isChecked = trueFalse.isChecked();

            if(stringValue==null || stringValue.equals("")|| intValue.equals("")){
                Toast.makeText(this,"Boş Alanları Doldurunuz",Toast.LENGTH_SHORT).show();
            } else {  //Değerler Boş Değil İse
                int value = Integer.parseInt(intValue);
                SharedPreferences.Editor editor = sharedPref.edit(); //SharedPreferences'a kayıt eklemek için editor oluşturuyoruz
                editor.putInt("intValue", value);
                editor.putString("stringValue", stringValue);
                editor.putBoolean("isChecked", isChecked);
                editor.commit();  //Kayıt
                Toast.makeText(this, "Kayıt Yapıldı.", Toast.LENGTH_SHORT).show();
            }
        }else if(view.getId()==R.id.button2){

            //Kayıtlı Verileri Getir Butonu
            //kaydedilen veriler kaydedilen key ile geri çekiliyor.
            //Eğer o key ile eşlesen bir değer yoksa default  value cekilir
            //örneğin "stringValue" değeri ile bir kayıt yoksa savedString'e değer olarak "Kayıt yok" atanacak

            String savedString = sharedPref.getString("stringValue","Kayot Yok");
            int savedInt = sharedPref.getInt("intValue",0);
            Boolean savedChecked = sharedPref.getBoolean("isChecked",false);

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Mobilhanem");
            if(savedString.equals("Kayıt Yok")){
                builder.setMessage("Önce Kayıt Yapınız");
            }else{ //Kayıtlı Değerler Yazdırılıyor
                builder.setMessage("Kayıtlı String :" + savedString + "\n Kayıtlı İnt : "+ savedInt + "\n isChecked :" + savedChecked);
            }
            builder.setNeutralButton("TAMAM", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.show();
        }
    }

}









