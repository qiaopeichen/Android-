package com.qiaopc.systemtest;

import android.graphics.drawable.Drawable;

/**
 * Created by qiaopc on 2018/2/5 0005.
 */

class PMAppInfo {

    private String appLabel;
    private Drawable appIcon;
    private String pkgName;

    public PMAppInfo() {
    }

    public String getAppLabel() {
        return appLabel;
    }

    public void setAppLabel(String appLabel) {
        this.appLabel = appLabel;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }
}
