package com.example.asynchronoustask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    Button start, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button) findViewById(R.id.button);
        stop = (Button) findViewById(R.id.button2);
        tv = (TextView) findViewById(R.id.textView2);
        LongRunningTask lrt=new LongRunningTask();
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lrt.doInBackground();
                lrt.onProgressUpdate();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setSelected(false);
                lrt.onPostExcute("asy task completed");
            }
        });


    }

    private class LongRunningTask extends AsyncTask<String,String,String>{
        protected void onPreExcute(){
            super.onPreExecute();
        }

        protected void onProgressUpdate(String...values) {
            Toast.makeText(getApplicationContext(),"Banner is movin",Toast.LENGTH_LONG).show();

        }


        public void onPostExcute(String s) {
            super.onPostExecute(s);
            tv.setVisibility(View.INVISIBLE);
            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();

        }

        @Override
        protected String doInBackground(String... strings) {
            tv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            tv.setSelected(true);
            tv.setVisibility(View.VISIBLE);
            return null;
        }
    }
}
    