package com.example.activitytest;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class FirstActivity extends BaseActivity {

    private Button btn;
    private ProgressBar pgb1;
    private ProgressBar pgb2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(FirstActivity.this,SecondActivity.class);
//                startActivity(intent);
                SecondActivity.actionStart(FirstActivity.this,"data1","data2");
            }
        });
        pgb1 = (ProgressBar) findViewById(R.id.progressBar);
        pgb2 = (ProgressBar) findViewById(R.id.progressBar2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;    //show
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.circlePro:
//                Toast.makeText(this, "add", Toast.LENGTH_SHORT).show();
                if (pgb1.getVisibility() == View.GONE) {
                    pgb1.setVisibility(View.VISIBLE);
                } else {
                    pgb1.setVisibility(View.GONE);
                }
                break;
            case R.id.HorizenPro:
//                Toast.makeText(this, "remove", Toast.LENGTH_SHORT).show();
                int progress = pgb2.getProgress();
                progress = progress + 10;
                pgb2.setProgress(progress);
                break;
            case R.id.alertDialog:
                AlertDialog.Builder dialog = new AlertDialog.Builder(FirstActivity.this);
                dialog.setTitle("This is dialog");
                dialog.setMessage("Something important.");
                dialog.setCancelable(false);
                dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                       @Override
                    public void onClick(DialogInterface dialog, int which){

                       }
                });
                dialog.setNegativeButton("cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which){

                    }
                });
                dialog.show();
                break;
            case R.id.processDialog:
                ProgressDialog progressDialog = new ProgressDialog(FirstActivity.this);
                progressDialog.setTitle("This is ProgressDialog");
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(true);
                progressDialog.show();
                break;
            default:
                break;
        }
        return true;
    }
}
