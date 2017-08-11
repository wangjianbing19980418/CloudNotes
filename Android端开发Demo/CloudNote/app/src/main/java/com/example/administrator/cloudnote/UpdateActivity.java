package com.example.administrator.cloudnote;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.entity.Note;
import com.wjb.utils.IPUtils;
import com.wjb.utils.MyHttpUtil;
import com.wjb.utils.MyTimeUtils;
import com.wjb.utils.MyUrlUtil;

public class UpdateActivity extends Activity {


    public final static String TAG="UpdateActivity";
    private String IP = IPUtils.getIPAddress();

    private EditText title_et;
    private EditText content_et;
    private TextView time_tv;
    private TextView auther_tv;
    private Note mNote;

    private ImageButton save_ibtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initView();

        initData();
    }

    private void initData() {
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        mNote = (Note) bundle.getSerializable("note");
        String auther=bundle.getString("username");

        title_et.setText(mNote.getTitle());
        time_tv.setText(MyTimeUtils.getDate());
        content_et.setText(mNote.getContent());
        auther_tv.setText(auther);
    }

    private void initView() {

        title_et = (EditText) findViewById(R.id.title_et);
        content_et = (EditText) findViewById(R.id.content_et);

        time_tv = (TextView) findViewById(R.id.time_tv);
        auther_tv = (TextView) findViewById(R.id.auther_tv);

        save_ibtn = (ImageButton) findViewById(R.id.save_ibtn);
        save_ibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateNotes();
            }
        });
    }

    public void updateNotes(){
        String title = title_et.getText().toString();
        String content=content_et.getText().toString();

        if(TextUtils.isEmpty(title)){
            Toast.makeText(UpdateActivity.this, "标题不能够为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(content)){
            Toast.makeText(UpdateActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String url = MyUrlUtil.getURL()+"updateNotes.do";
        String params = "note_id=" + mNote.getId()
                +"&title="+title_et.getText().toString()
                +"&content="+content_et.getText().toString();
        new UpdateNoteAsyncTask().execute(url, params);
    }

    class UpdateNoteAsyncTask extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... params) {
            return MyHttpUtil.post(params[0],params[1]);
        }

        @Override
        protected void onPostExecute(String pS) {

            if(TextUtils.isEmpty(pS)){
                Toast.makeText(UpdateActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
                return;
            }
            else{
                pS = pS.trim();
                Log.d("update", pS);
                boolean result=pS.equals("true");
                if(result){

                    Log.e(TAG, "更新成功，接下来修改历史记录");
                    String url=MyUrlUtil.getURL()+"insertHistoryItems.do" ;
                    String params="notes_id="+mNote.getId()
                            +"&update_time="+MyTimeUtils.getDateTime()
                            +"&update_type="+4
                            +"&before_title="+mNote.getTitle()
                            +"&before_content="+mNote.getContent()
                            +"&after_title="+title_et.getText().toString()
                            +"&after_content="+content_et.getText().toString();
                    new MyInsertHistoryItemsAsyncTask().execute(url, params);
                }
                else{
                    Toast.makeText(UpdateActivity.this, "更新失败", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    class MyInsertHistoryItemsAsyncTask extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... params) {
            return MyHttpUtil.post(params[0],params[1]);
        }

        @Override
        protected void onPostExecute(String pS) {

            if(TextUtils.isEmpty(pS)){
                Toast.makeText(UpdateActivity.this, "修改历史记录时候网络连接失败", Toast.LENGTH_SHORT).show();
                return;
            }
            else{
                pS = pS.trim();
                boolean result=pS.equals("true");
                if(result){
                    //通知列表界面刷新
                    Log.e(TAG, "修改历史记录成功");
                    setResult(RESULT_OK);
                    finish();
                }
                else{
                    Log.e(TAG, "修改历史记录失败");
                    Toast.makeText(UpdateActivity.this, "修改历史记录失败", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
