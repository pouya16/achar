package com.example.esppad.calc2.Main.Tools.passwordHelper;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.esppad.calc2.Main.G;
import com.example.esppad.calc2.R;

import java.util.Random;

import at.markushi.ui.CircleButton;

public class PasswordActivity extends AppCompatActivity {
    Random random;
    Button passwordGenerate;
    TextView passwordShow;TextView savedPasswordTxt;TextView savedUrlTxt;TextView savedUesrTxt;
    CheckBox lengthCheckBox;CheckBox numbersCheckBox;CheckBox specialCheckBox;
    EditText lengthEditTxt;EditText numbersEditTxt;EditText spacialEditTxt;EditText urlEditText;EditText  userEditText;
    ImageButton lengthPlus;ImageButton lengthMinus;ImageButton numbersPlus;
    ImageButton numbersMinus;ImageButton specialPlus;ImageButton specialsMinus;
    String generatedPassword;
    CardView passwordCard;
    Button passwordBtn;
    Button savedPasswords;
    Button  addViewPassword;
    CircleButton deletePasswordBtn;
    G MyHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        //defining views in the pages:
        savedPasswordTxt = (TextView) findViewById(R.id.passwordSavedPassAct);
        savedUrlTxt = (TextView) findViewById(R.id.urlSavedPassAct);
        passwordShow = (TextView) findViewById(R.id.passwordText);
        savedUesrTxt = (TextView) findViewById(R.id.userSavedPassAct);
        passwordGenerate = (Button) findViewById(R.id.generatePasswordBtn);
        lengthCheckBox = (CheckBox) findViewById(R.id.lengthCheckBoxPasswordAct);
        numbersCheckBox = (CheckBox) findViewById(R.id.numbersCheckBoxPasswordAct);
        specialCheckBox = (CheckBox) findViewById(R.id.numbersCheckBoxPasswordAct);
        lengthEditTxt = (EditText) findViewById(R.id.lengthEditTxtPasswordAct);
        numbersEditTxt = (EditText) findViewById(R.id.numberEditTxtPasswordAct);
        spacialEditTxt = (EditText) findViewById(R.id.specialEditTxtPasswordAct);
        urlEditText = (EditText) findViewById(R.id.siteAdressTxtPasswordAct);
        userEditText = (EditText) findViewById(R.id.siteUserTxtPasswordActivity);
        lengthPlus = (ImageButton) findViewById(R.id.lengthPlusImageBtnPasswordAct);
        lengthMinus = (ImageButton) findViewById(R.id.lengthNegImageBtnPasswordAct);
        numbersPlus = (ImageButton) findViewById(R.id.numberPlusImageBtnPasswordAct);
        numbersMinus = (ImageButton) findViewById(R.id.numberNegImageBtnPasswordAct);
        specialPlus = (ImageButton) findViewById(R.id.specialPlusImageBtnPasswordAct);
        specialsMinus = (ImageButton) findViewById(R.id.specialNegImageBtnPasswordAct);
        passwordCard = (CardView) findViewById(R.id.passwordConatainerPasswordAc);
        passwordBtn = (Button) findViewById(R.id.savePasswordBtnPasswordAct);
        savedPasswords = (Button) findViewById(R.id.btnSavedPassPasswordsAct);
        addViewPassword = (Button) findViewById(R.id.btnEditWatchPassPasswordAct);
        deletePasswordBtn = (CircleButton) findViewById(R.id.btnDeletePasswordAct);
        MyHelper = new G(getApplicationContext(),getString(R.string.password_show_passwordmake),"passworddb");
        MyHelper.createPasswordsAppDirectories();
        MyHelper.createOrOpenDataBase();

        final ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        passwordCard.setVisibility(View.GONE);

        lengthPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int length = Integer.parseInt(lengthEditTxt.getText().toString());
                if(length<30) {
                    length++;
                    lengthEditTxt.setText(Integer.toString(length));
                }else{
                    Toast.makeText(PasswordActivity.this,"length is less than 30 chars", Toast.LENGTH_SHORT).show();
                }
            }
        });

        savedPasswords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MyHelper.getSharedPreferenceValue()!=null){
                    String pass = MyHelper.getSharedPreferenceValue();

                }else{

                }
                //Intent intent = new Intent(PasswordActivity.this,SavedPasswordsActivity.class);
                //PasswordActivity.this.startActivity(intent);
            }
        });

        passwordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(passwordShow.getText().length()>0){
                    passwordCard.setVisibility(View.VISIBLE);
                    savedPasswordTxt.setText(passwordShow.getText().toString());
                    savedUrlTxt.setText(urlEditText.getText().toString());
                    savedUesrTxt.setText(userEditText.getText().toString());
                }else{
                    Toast.makeText(PasswordActivity.this,"لطفا اول پسورد بسازید",Toast.LENGTH_SHORT).show();
                }
            }
        });

        deletePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordCard.setVisibility(View.GONE);
            }
        });

        lengthMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int length = Integer.parseInt(lengthEditTxt.getText().toString());
                if(length>1) {
                    length--;
                    lengthEditTxt.setText(Integer.toString(length));
                }else{
                    Toast.makeText(PasswordActivity.this,"length is greater than 1 chars",Toast.LENGTH_SHORT).show();
                }
            }
        });

        numbersPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentNumber = Integer.parseInt(numbersEditTxt.getText().toString());
                int specialLength = Integer.parseInt(spacialEditTxt.getText().toString());
                int length = Integer.parseInt(lengthEditTxt.getText().toString());
                if(!specialCheckBox.isChecked()){
                    specialLength = 0;
                }
                int calc = calcNumber(currentNumber,specialLength,length,1);
                numbersEditTxt.setText(Integer.toString(calc));
            }
        });
        numbersMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentNumber = Integer.parseInt(numbersEditTxt.getText().toString());
                int specialLength = Integer.parseInt(spacialEditTxt.getText().toString());
                int length = Integer.parseInt(lengthEditTxt.getText().toString());
                if(!specialCheckBox.isChecked()){
                    specialLength = 0;
                }
                int calc = calcNumber(currentNumber,specialLength,length,-1);
                numbersEditTxt.setText(Integer.toString(calc));
            }
        });
        specialsMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentNumber = Integer.parseInt(numbersEditTxt.getText().toString());
                int specialLength = Integer.parseInt(spacialEditTxt.getText().toString());
                int length = Integer.parseInt(lengthEditTxt.getText().toString());
                if(!numbersCheckBox.isChecked()){
                    specialLength = 0;
                }
                int calc = calcNumber(specialLength,currentNumber,length,-1);
                spacialEditTxt.setText(Integer.toString(calc));
            }
        });
        specialPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentNumber = Integer.parseInt(numbersEditTxt.getText().toString());
                int specialLength = Integer.parseInt(spacialEditTxt.getText().toString());
                int length = Integer.parseInt(lengthEditTxt.getText().toString());
                if(!numbersCheckBox.isChecked()){
                    specialLength = 0;
                }
                int calc = calcNumber(specialLength,currentNumber,length,1);
                spacialEditTxt.setText(Integer.toString(calc));
            }
        });

        lengthCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lengthCheckBox.setChecked(true);
            }
        });



        passwordShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ClipData clip = ClipData.newPlainText("password",passwordShow.getText().toString());
                    clipboardManager.setPrimaryClip(clip);
                    Toast.makeText(PasswordActivity.this,"پسورد با موفقیت در حافظه موفقت ذخیره شد.",Toast.LENGTH_SHORT).show();
                }catch (Exception e){

                }

            }
        });


        passwordGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int length = Integer.parseInt(lengthEditTxt.getText().toString());
                int numbers = Integer.parseInt(numbersEditTxt.getText().toString());
                int special = Integer.parseInt(spacialEditTxt.getText().toString());
                if(length<31 && numbers+special<length){
                     generatedPassword = passwordmaker(length,numbers,special);
                }else if(length>31){
                    passwordShow.setText("طول رمز کوچکتر از 30 کاراکتر باید باشد");
                }else{
                    passwordShow.setText("جمع اعداد و کاراکترهای خاص کمتر از طول باید باشد.");
                }
                Log.i("Log", "password is: " + generatedPassword);
                passwordShow.setText(generatedPassword);
            }
        });
    }

    public int calcNumber(int changingNumber,int constantNumber,int length,int sign){
        int result = changingNumber;
        if(sign == 1){
            if(changingNumber<length-constantNumber){
                result = result + sign;
            }else{
                Toast.makeText(PasswordActivity.this,"bounds warning",Toast.LENGTH_SHORT).show();
            }
        }else{
            if(changingNumber>0){
                result = result + sign;
            }else{
                Toast.makeText(PasswordActivity.this,"bounds warning",Toast.LENGTH_SHORT).show();
            }
        }
        return result;
    }


    public String passwordmaker(int size,int numberCount,int specialCount){
        String s="";
        String numbers = "0123456789";
        String alphabets = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
        String specialChars = "*%$@#-_!";
        random = new Random();
        char[] randomArray = new char[size];
        for(int i=0;i<size;i++){
            randomArray[i] = '`';
        }
        for(int i=0;i<numberCount;i++){
            int index = Math.abs(random.nextInt()%size);
            while(randomArray[index]!='`'&&index<size-1){
                index++;
            }
            if(randomArray[index]=='`'){
                while(randomArray[index]!='`'&&index>0){
                    index --;
                }
            }
            int randomNum = Math.abs(random.nextInt()%(numbers.length()));
            randomArray[index] = numbers.charAt(randomNum);
        }
//
        for(int i=0;i<specialCount;i++){
            int index = Math.abs(random.nextInt()%size);
            while(randomArray[index]!='`'&&index<size-1){
                index++;
            }
            if(randomArray[index]=='`'){
                while(randomArray[index]!='`'&&index>0){
                    index --;
                }
            }
            int randomNum = Math.abs(random.nextInt()%(specialChars.length()));
            randomArray[index] = specialChars.charAt(randomNum);
        }

        for(int i = 0; i <size;i++){
            if(randomArray[i]=='`'){
                int randomNum = Math.abs(random.nextInt()%(alphabets.length()));
                randomArray[i] = alphabets.charAt(randomNum);
            }
            s = s +randomArray[i];
        }
        return s;
    }
}
