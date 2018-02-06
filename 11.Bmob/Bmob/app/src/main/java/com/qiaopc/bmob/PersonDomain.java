package com.qiaopc.bmob;

import cn.bmob.v3.BmobObject;

/**
 * Created by qiaopc on 2018/2/6 0006.
 */

public class PersonDomain extends BmobObject {
    private String name;
    private String address;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
