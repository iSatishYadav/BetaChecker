package com.satishyadav.betachecker.apps;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Avenger on 28-02-2017.
 */

public class AppInfo {
    private String name = "";
    private String package_name = "";
    private String version_name = "";
    private int version_code = 0;
    private Drawable icon;

    public AppInfo(Context _context) {
        this._context = _context;
    }

    public AppInfo() {

    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public String getPackage_name() {
        return package_name;
    }

    public void setPackage_name(String package_name) {
        this.package_name = package_name;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Context _context;

    public List<AppInfo> getInstalledApps(boolean includingSystem) {
        List<AppInfo> res = new ArrayList<>();
        PackageManager _pm = _context.getPackageManager();
        List<PackageInfo> packs = _pm.getInstalledPackages(0);
        for(int i=0;i<packs.size();i++) {
            PackageInfo p = packs.get(i);
            if (!includingSystem && (p.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0 || !includingSystem &&  (p.applicationInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
                continue ;
            }
            AppInfo newInfo = new AppInfo();
            newInfo.name = p.applicationInfo.loadLabel(_pm).toString();
            newInfo.package_name = p.packageName;
            newInfo.version_name = p.versionName;
            newInfo.version_code = p.versionCode;
            newInfo.icon = p.applicationInfo.loadIcon(_pm);
            res.add(newInfo);
        }
        Collections.sort(res, (a1, a2) -> a1.getName().toLowerCase().compareTo(a2.getName().toLowerCase()));
        return res;
    }
}
