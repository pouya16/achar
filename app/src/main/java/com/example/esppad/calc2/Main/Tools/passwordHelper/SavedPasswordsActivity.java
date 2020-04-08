package com.example.esppad.calc2.Main.Tools.passwordHelper;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.esppad.calc2.R;

public class SavedPasswordsActivity extends AppCompatActivity {
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_passwords);
        back = (ImageButton) findViewById(R.id.btnBackSavedPassAct);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavedPasswordsActivity.this.finish();
            }
        });
    }
}
