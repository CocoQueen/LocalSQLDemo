package com.example.localsqldemo;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.localsqldemo.entity.Student;

import java.util.List;

/**
 * @Author Coco
 * @ClassName StudentInfoAdapter
 * @Date 2020/9/3 10:09
 * @Description TODO
 */
public class StudentInfoAdapter extends BaseQuickAdapter<Student, BaseViewHolder> {

    public StudentInfoAdapter(@Nullable List<Student> data) {
        super(R.layout.item_student_info, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Student item) {
        TextView textView = helper.getView(R.id.tv);
        String name = item.getName();
        String address = item.getAddress();
        String age = item.getAge();
        String grade = item.getGrade();
        String schoolName = item.getSchoolName();
        String sex = item.getSex();
        String studentNo = item.getStudentNo();
        String telPhone = item.getTelPhone();

        textView.setText("姓名：" + name + "\n年龄：" + age + "\n性别：" + sex +
                "\n学生编号：" + studentNo + "\n手机号：" + telPhone + "\n年级：" + grade + "\n住址：" + address + "\n学校名字：" + schoolName);
    }
}
