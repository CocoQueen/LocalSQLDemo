package com.example.localsqldemo;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.database.greenDao.db.DaoMaster;
import com.database.greenDao.db.DaoSession;
import com.database.greenDao.db.MyDaoMaster;
import com.tencent.bugly.Bugly;

import org.greenrobot.greendao.database.Database;


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
        Bugly.init(getApplicationContext(), "f58cef1677", false);
    }

    private void initGreenDao() {
        MyDaoMaster helper = new MyDaoMaster(this, "LocalSQL.db");
        SQLiteDatabase db = helper.getWritableDatabase();
//        Database db = helper.getEncryptedWritableDb("123456");
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    private DaoSession daoSession;
    public DaoSession getDaoSession() {
        return daoSession;
    }
}
