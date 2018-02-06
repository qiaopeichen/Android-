package com.qiaopc.bmob;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "jojo";

    private EditText et_name;
    private EditText et_address;

    private ListView lv_person;
    private List<PersonDomain> mPersonDomainList;
    private PersonAdapter mPersonAdapter;

    private SharedPreferences mSharedPreferences;
    private Button btn_insert;
    private Button btn_query;
    private Button btn_delete;
    private Button btn_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //第一：默认初始化
        Bmob.initialize(this, "a3ab5094e47441def1d3b8399fbc0a5c");
        mSharedPreferences = getSharedPreferences("person", MODE_PRIVATE);
        initView();
    }

    private void initView() {
        btn_insert = findViewById(R.id.btn_insert);
        btn_query = findViewById(R.id.btn_query);
        btn_delete = findViewById(R.id.btn_delete);
        btn_update = findViewById(R.id.btn_update);
        et_name = findViewById(R.id.et_name);
        et_address = findViewById(R.id.et_address);
        lv_person = findViewById(R.id.lv_person);
        mPersonDomainList = new ArrayList<>();
        mPersonAdapter = new PersonAdapter(MainActivity.this, mPersonDomainList);
        lv_person.setAdapter(mPersonAdapter);

        btn_update.setOnClickListener(this);
        btn_query.setOnClickListener(this);
        btn_insert.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String name = et_name.getText().toString();
        String address = et_address.getText().toString();
        String id = mSharedPreferences.getString(name, null);
        switch (v.getId()) {
            case R.id.btn_insert:
                if (id != null) {
                    PersonDao.getInstance(MainActivity.this).update(id, name, address);
                } else {
                    PersonDao.getInstance(MainActivity.this).insert(name, address);
                }
                break;
            case R.id.btn_query:
                query();
                break;
            case R.id.btn_update:
                if (id != null) {
                    PersonDao.getInstance(MainActivity.this).update(id, name, address);
                }
                break;
            case R.id.btn_delete:
                PersonDao.getInstance(MainActivity.this).delete(name);
                break;
            default:
                break;
        }
    }

    private void query() {
        BmobQuery<PersonDomain> query = new BmobQuery<>();
        //查询personName
        query.addWhereEqualTo("name", et_name.getText().toString());
        //返回50条数据，如果不加上这条语句，默认返回10条数据
        query.setLimit(50);
        //执行查询方法
        //注意此方法会开启线程，如果不在此方法内刷新数据，会造成时序问题，导致先刷新，数据后添加。
        query.findObjects(new FindListener<PersonDomain>() {
            @Override
            public void done(List<PersonDomain> object, BmobException e) {
                if (e == null) {
                    mPersonDomainList.clear();
                    Toast.makeText(MainActivity.this, "查询成功：共" + object.size() + "条数据。", Toast.LENGTH_SHORT).show();
                    for (PersonDomain personDomain : object) {
                        mPersonDomainList.add(personDomain);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                mPersonAdapter.notifyDataSetChanged();
            }
        });
    }
}
