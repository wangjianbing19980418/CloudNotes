package com.example.administrator.cloudnote;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.wjb.utils.IPUtils;
import com.wjb.utils.MyHttpUtil;
import com.wjb.utils.MyTimeUtils;
import com.wjb.utils.MyUrlUtil;


public class AddActivity extends Activity {


    private final String TAG = "AddActivity";
    private String IP = IPUtils.getIPAddress();

    private EditText title_et;
    private EditText content_et;
    private TextView time_tv;
    private TextView auther_tv;
    private int user_id;
    private String username;

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
        user_id=bundle.getInt("userId");
        username = bundle.getString("username");
        auther_tv.setText(username);

        time_tv.setText(MyTimeUtils.getDate());
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
                addNotes();
            }
        });
    }

    public void addNotes(){
        String title = title_et.getText().toString();
        String content=content_et.getText().toString();
        if(TextUtils.isEmpty(title)){
            Toast.makeText(AddActivity.this, "标题不能够为空", Toast.LENGTH_SHORT).show();
          return;
        }
        if(TextUtils.isEmpty(content)){
            Toast.makeText(AddActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String url = MyUrlUtil.getURL()+"insertNotes.do";
        String params = null;
        params = "title=" + title
                        +"&content="+content
                        +"&creating_date="+MyTimeUtils.getDate()
                        +"&user_id="+user_id;
        new AddNoteAsyncTask().execute(url, params);
    }
    class AddNoteAsyncTask extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... params) {
            return MyHttpUtil.post(params[0],params[1]);
        }
        @Override
        protected void onPostExecute(String pS) {

            Log.e(TAG, pS);
            if(TextUtils.isEmpty(pS)){
                Toast.makeText(AddActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
            }
            else{
                pS = pS.trim();
                Log.e(TAG, pS);
                int insertIndex=Integer.parseInt(pS);
//                Toast.makeText(AddActivity.this, ""+result, Toast.LENGTH_SHORT).show();
                if(insertIndex!=-1){
                    //通知列表界面刷新
                    Log.e(TAG, "增加记录成功，接下来进行历史记录的更新");
                    String title = title_et.getText().toString();
                    String content=content_et.getText().toString();
                    String url=MyUrlUtil.getURL()+"insertHistoryItems.do";
                    String params="notes_id="+insertIndex
                            +"&update_time="+MyTimeUtils.getDateTime()
                            +"&update_type="+1
                            +"&after_title="+title
                            +"&after_content="+content;
                    new MyInsertHistoryItemsAsyncTask().execute(url, params);
                }
                else{
                    Toast.makeText(AddActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
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

            if(!TextUtils.isEmpty(pS)){
                if(pS.trim().equals("true")){
                    Log.e(TAG, "历史记录生成成功");
                    setResult(RESULT_OK);
                    finish();

                }
                else{
                    Log.e(TAG, "历史记录生成失败");
                    Toast.makeText(AddActivity.this, TAG+"历史记录生成失败", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(AddActivity.this, TAG+"历史记录生成失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("标题");
        getMenuInflater().inflate(R.menu.input_context_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public void onContentChanged() {

    }
}
