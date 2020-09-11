package com.example.localsqldemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.database.greenDao.db.DaoSession;
import com.database.greenDao.db.MyDaoMaster;
import com.database.greenDao.db.StudentDao;
import com.example.localsqldemo.entity.BankCard;
import com.example.localsqldemo.entity.Father;
import com.example.localsqldemo.entity.IdCard;
import com.example.localsqldemo.entity.Person;
import com.example.localsqldemo.entity.Son;
import com.example.localsqldemo.entity.Student;
import com.google.gson.Gson;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Coco
 * @time 2020/9/3 9:35
 * @classname MainActivity
 * description:
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "=============";
    @BindView(R.id.studentNo)
    EditText studentNo;
    @BindView(R.id.age)
    EditText age;
    @BindView(R.id.phone)
    EditText phone;
    @BindView(R.id.sex)
    EditText sex;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.adress)
    EditText adress;
    @BindView(R.id.schoolName)
    EditText schoolName;
    @BindView(R.id.grade)
    EditText grade;
    @BindView(R.id.mBtnInsert)
    Button mBtnInsert;
    @BindView(R.id.mBtnQuery)
    Button mBtnQuery;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        daoSession = ((MyApp) getApplication()).getDaoSession();

        List<Student> students = daoSession.loadAll(Student.class);
        StudentInfoAdapter adapter = new StudentInfoAdapter(students);
        recycler.setAdapter(adapter);
    }

    public void insertStudent(View view) {

        Student student = new Student();
        student.setAddress(adress.getText().toString().trim());
        student.setAge(age.getText().toString().trim());
        student.setGrade(grade.getText().toString().trim());
        student.setName(name.getText().toString().trim());
        student.setSchoolName(schoolName.getText().toString().trim());
        student.setStudentNo(studentNo.getText().toString().trim());
        student.setSex(sex.getText().toString().trim());
        student.setTelPhone(phone.getText().toString().trim());

        List<Student> students1 = daoSession.loadAll(Student.class);
        for (int i = 0; i < students1.size(); i++) {
            String studentNoo = students1.get(i).getStudentNo();
            if (studentNo.getText().toString().trim().equals(studentNoo)) {
                Toast.makeText(this, "该学号已存在", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        daoSession.insert(student);

        //插入对应的IdCard数据
        IdCard idCard = new IdCard();
        idCard.setUserName(name.getText().toString().trim());
        String strRand = "";
        for (int i = 0; i < 19; i++) {
            strRand += String.valueOf((int) (Math.random() * 10));
        }
        idCard.setIdNo(strRand);
        Log.e(TAG, "insertStudent: " + name.getText().toString().trim());
        Log.e(TAG, "insertStudent: " + strRand);
        daoSession.insert(idCard);

        List<Student> students = daoSession.loadAll(Student.class);
        List<IdCard> idCards = daoSession.loadAll(IdCard.class);
        String idCardStr = new Gson().toJson(idCards);
        Log.e(TAG, "insertStudent: " + idCardStr);
        StudentInfoAdapter adapter = new StudentInfoAdapter(students);
        recycler.setAdapter(adapter);
    }

    public void queryStudent(View view) {
        queryByMessage();
//        queryListByMessage();
    }

    //查询所有性别为女的数据
    public List queryListByMessage() {
        DaoSession daoSession = ((MyApp) getApplication()).getDaoSession();
        QueryBuilder<Student> qb = daoSession.queryBuilder(Student.class);
        QueryBuilder<Student> studentQueryBuilder = qb.where(StudentDao.Properties.Sex.eq("女"),
                qb.and(StudentDao.Properties.StudentNo.eq(5),
                        StudentDao.Properties.Age.eq(15)));
        List<Student> studentList = studentQueryBuilder.list();
        String s = new Gson().toJson(studentList);
        Log.e("===========", "queryListByMessage: " + s);
        return studentList;
    }

    public List queryByMessage() {
        DaoSession daoSession = ((MyApp) getApplication()).getDaoSession();
        QueryBuilder<Student> qb = daoSession.queryBuilder(Student.class);
        QueryBuilder<IdCard> qb2 = daoSession.queryBuilder(IdCard.class);
        List<Student> list = qb.build().list();
        List<IdCard> list1 = qb2.build().list();
        for (Student sss : list) {
            String name = sss.getName();
            if ("甄姬".equals(name)) {
                String studentNo = sss.getStudentNo();
                String idNo = sss.getIdCard().getIdNo();
                Log.e(TAG, "queryByMessage: " + studentNo + "====" + idNo);
            }
        }
        return list;
    }

    public void clearSQL(View view) {
        daoSession.clear();
    }

    public void insertSon(View view) {

        for (int i = 0; i < 5; i++) {
            Son son = new Son();
            Father father = new Father();
            son.setName("儿子" + i);
            son.setAge(i + "");
            father.setAge("" + (i + 20));
            father.setName("父亲" + i);
            daoSession.insert(son);
            daoSession.insert(father);
        }
        List<Son> sons = daoSession.loadAll(Son.class);
        List<Father> fathers = daoSession.loadAll(Father.class);
        String s = new Gson().toJson(sons);
        String s1 = new Gson().toJson(fathers);
        Log.e(TAG, "insertSonson:" + s);
        Log.e(TAG, "insertSon: " + s1);

        for (Son sB : sons) {
            String name = sB.getFather().getName();
            String name1 = sB.getName();
            Log.e(TAG, "insertSon: " + name1 + "AND" + name);
        }

    }

    public void insertBank(View view) {
//        daoSession.deleteAll(Person.class);
//        daoSession.deleteAll(BankCard.class);
        List<Person> list = daoSession.queryBuilder(Person.class).list();
        for (Person son : list) {
            List<BankCard> bankCards = son.getBankCards();
            for (BankCard father : bankCards) {
                String name1 = father.getName();
                if ("rt".equals(name1)) {
                    Log.e(TAG, "insertBank: " + father.getCardNum() + "===" + son.getName());
                }
            }
        }

    }

    public void insertPerson(View view) {
        Person person = new Person();
        person.setName(name.getText().toString().trim());
        daoSession.insert(person);
        BankCard bankCard = new BankCard();
        String strRand = "";
        for (int i = 0; i < 10; i++) {
            strRand += String.valueOf((int) (Math.random() * 10));
        }
        bankCard.setCardNum(strRand);
        bankCard.setName(name.getText().toString().trim());
        bankCard.setBankId(person.getId());
        daoSession.insert(bankCard);

        List<Person> people = daoSession.loadAll(Person.class);
        List<BankCard> bankCards = daoSession.loadAll(BankCard.class);
        String peo = new Gson().toJson(people);
        String bank = new Gson().toJson(bankCards);
        Log.e(TAG, "insertPersonpeo: " + peo);
        Log.e(TAG, "insertPerson: " + bank);
    }
}
