package com.example.vdpetrov.pleasefinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class StoreActivity extends AppCompatActivity {

    private ImageButton casebtn, cpubtn, gpubtn, mombtn, powersupbtn, rambtn, hdrivebtn;
    private Button btnExit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        casebtn = findViewById(R.id.casebtn);
        cpubtn = findViewById(R.id.cpubtn);
        gpubtn = findViewById(R.id.gpubtn);
        mombtn = findViewById(R.id.mombtn);
        powersupbtn = findViewById(R.id.powersupbtn);
        rambtn = findViewById(R.id.rambtn);
        hdrivebtn = findViewById(R.id.hdrivebtn);
        setClickListener(casebtn, "case");
        setClickListener(cpubtn, "cpu");
        setClickListener(gpubtn, "gpu");
        setClickListener(mombtn, "motherboard");
        setClickListener(powersupbtn, "powersupply");
        setClickListener(rambtn, "ram");
        setClickListener(hdrivebtn, "harddrive");
    }


    private void setClickListener(ImageButton btn, final String category){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCatalogActivity(category);
            }
        });
    }


    private void openCatalogActivity(String category){
        Intent intent = new Intent(this, CatalogActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }


}


