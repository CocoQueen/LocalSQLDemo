package com.example.localsqldemo;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.database.greenDao.db.DaoMaster;
import com.database.greenDao.db.DaoSession;

/**
 * @Author Coco
 * @ClassName MyApp
 * @Date 2020/9/3 9:30
 * @Description TODO
 */
public class MyApp extends Application {
    private static MyApp myApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        initGreenDao();
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "LocalSQL.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    private DaoSession daoSession;
    public DaoSession getDaoSession() {
        return daoSession;
    }
}
