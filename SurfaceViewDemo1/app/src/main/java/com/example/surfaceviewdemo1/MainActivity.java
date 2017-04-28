package com.example.surfaceviewdemo1;

import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int LOCAL_PIC = 1;
    private static int RESULT_LOAD_IMAGE = 1;
    private Button btn;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    String picturePath;
    private boolean DrawOrNotFlag = false;
    private boolean ReturnPicOrNotFlag = false;

    private ImageView imgview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: ");
        DrawOrNotFlag = false;  //NO Draw
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.setKeepScreenOn(true);
        surfaceHolder.addCallback(new SurfaceViewLis());

        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "click", Toast.LENGTH_SHORT).show();
                DrawOrNotFlag = true;
                Log.d(TAG, "onClick: ");

//                new MyThread().start();
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
//
            }
        });

        imgview = (ImageView) findViewById(R.id.imgview);
    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case LOCAL_PIC:
//                    picturePath = msg.obj.toString();
                    DrawOrNotFlag = true;
                    Log.d(TAG, "handleMessage: "+ DrawOrNotFlag);
//                    new MyThread().start();
//                    ReturnPicOrNotFlag = true;
            Resources res = getResources();
            Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.background);
//                    Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
                    imgview.setImageBitmap(bitmap);
                    break;

            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String pictureString = cursor.getString(columnIndex);
            picturePath = pictureString;
            cursor.close();

            Message msg = new Message();
            msg.what = 1;
//            msg.obj = pictureString;
            handler.sendMessage(msg);

            //           new MyThread().start();
        }
    }

    private class SurfaceViewLis implements SurfaceHolder.Callback {

//        private MyThread myThread;

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {
        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {

//            myThread = new MyThread();
//            myThread.start();
//            myThread.isRun = true;
            DrawOrNotFlag = true;
            Log.d(TAG, "surfaceCreated: " + DrawOrNotFlag);
            if(ReturnPicOrNotFlag){
                new MyThread().start();
            }
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
//            myThread.isRun = false;
            DrawOrNotFlag = false;
            Log.d(TAG, "surfaceDestroyed: "+ DrawOrNotFlag);
        }

    }

    private static final String TAG = "MainActivity";

    //线程内部类
    class MyThread extends Thread {

//        public boolean isRun = true;

        @Override
        public void run() {
            Log.d(TAG, "run: enter");
            while (DrawOrNotFlag) {
                Log.d(TAG, "run: while");
                Draw();
            }
        }

        public void Draw() {

            Canvas canvas = surfaceHolder.lockCanvas();

            Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
//            Resources res = getResources();
//            Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.background);

            canvas.drawBitmap(bitmap, 0, 0, null);

            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }
}