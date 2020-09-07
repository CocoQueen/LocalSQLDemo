package com.example.localsqldemo;

import com.database.greenDao.db.IdCardDao;
import com.database.greenDao.db.StudentDao;
import com.example.localsqldemo.entity.IdCard;
import com.example.localsqldemo.entity.Student;

import org.greenrobot.greendao.annotation.Id;

/**
 * @Author Coco
 * @ClassName DaoUtilsStore
 * @Date 2020/9/4 16:53
 * @Description TODO
 */
public class DaoUtilsStore {
    private volatile static DaoUtilsStore instance = new DaoUtilsStore();
    private CommonDaoUtils<Student> meiziDaoUtils;
    private CommonDaoUtils<IdCard> hanziDaoUtils;

    public static DaoUtilsStore getInstance() {
        return instance;
    }

    private DaoUtilsStore() {
        DaoManager mManager = DaoManager.getInstance();
        StudentDao studentDao = mManager.getDaoSession().getStudentDao();
        meiziDaoUtils = new CommonDaoUtils(Student.class, studentDao);

        IdCardDao idCardDao = mManager.getDaoSession().getIdCardDao();
        hanziDaoUtils = new CommonDaoUtils(IdCard.class, idCardDao);
    }

    public CommonDaoUtils<Student> getMeiziDaoUtils() {
        return meiziDaoUtils;
    }

    public CommonDaoUtils<IdCard> getHanziDaoUtils() {
        return hanziDaoUtils;
    }
}