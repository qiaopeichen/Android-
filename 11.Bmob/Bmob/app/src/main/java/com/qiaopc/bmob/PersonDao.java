package com.qiaopc.bmob;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by qiaopc on 2018/2/6 0006.
 */

public class PersonDao {

    private Context mContext;
    private static PersonDao personDao;
    private static SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor mEditor;

    private PersonDao(Context context) {
        mContext = context;
        mSharedPreferences = mContext.getSharedPreferences("person", Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    public static PersonDao getInstance(Context context) {
        if (personDao == null) {
            personDao = new PersonDao(context);
        }
        return personDao;
    }

    public void insert(String name, String address) {
        final PersonDomain p2 = new PersonDomain();
        p2.setName(name);
        p2.setAddress(address);
        p2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if (e == null) {
                    Toast.makeText(mContext, "添加数据成功，返回objectId为：" + objectId, Toast.LENGTH_SHORT).show();
                    // 存入name和id的映射
                    mEditor.putString(p2.getName(), objectId);
                    mEditor.apply();
                } else {
                    Toast.makeText(mContext, "添加失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void update(final String id, String name, String address) {
        //更新Person表里面id为6b6c11c537的数据，address内容更新为“北京朝阳”
        final PersonDomain p2 = new PersonDomain();
        p2.setName(name);
        p2.setAddress(address);
        p2.update(id, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Toast.makeText(mContext, "更新成功:" + p2.getUpdatedAt(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "更新失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void delete(final String name) {
        String id = mSharedPreferences.getString(name, "" + 0);
        final PersonDomain p2 = new PersonDomain();
        p2.delete(id, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Toast.makeText(mContext, "删除成功:" + p2.getUpdatedAt(), Toast.LENGTH_SHORT).show();
                    mEditor.remove(name);
                    mEditor.commit();
                } else {
                    Toast.makeText(mContext, "删除失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
