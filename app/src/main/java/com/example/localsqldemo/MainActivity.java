package com.example.localsqldemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.database.greenDao.db.DaoSession;
import com.example.localsqldemo.entity.Student;

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
        daoSession.insert(student);

        List<Student> students = daoSession.loadAll(Student.class);
        StudentInfoAdapter adapter = new StudentInfoAdapter(students);
        recycler.setAdapter(adapter);
    }

    public void queryStudent(View view) {
        List<Student> students = daoSession.loadAll(Student.class);
        StudentInfoAdapter adapter = new StudentInfoAdapter(students);
        recycler.setAdapter(adapter);
    }
}
