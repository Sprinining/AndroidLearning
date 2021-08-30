package com.example.myroom.manager;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.myroom.Student;
import com.example.myroom.StudentDao;
import com.example.myroom.StudentDatabase;

import java.util.List;

public class DBEngine {
    private StudentDao studentDao;

    public DBEngine(Context context){
        StudentDatabase studentDatabase = StudentDatabase.getInstance(context);
        studentDao = studentDatabase.getStudentDao();
    }

    // 对dao的增删改查
    // 插入
    public void insertStudents(Student... students){
        new InsertAsyncTask(studentDao).execute(students);
    }

    // 更新
    public void updateStudents(Student... students){
        new UpdateAsyncTask(studentDao).execute(students);
    }

    // 条件删除
    public void deleteStudents(Student... students){
        new DeleteAsyncTask(studentDao).execute(students);
    }

    // 全部删除
    public void deleteAllStudents(){
        new DeleteAllAsyncTask(studentDao).execute();
    }
    
    // 全部查询
    public void queryAllStudents(){
        new QueryAllAsyncTask(studentDao).execute();
    }



    // 异步操作
    // 插入
    static class InsertAsyncTask extends AsyncTask<Student, Void, Void>{
        private StudentDao dao;

        public InsertAsyncTask(StudentDao studentDao){
            dao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            dao.insertStudents(students);
            return null;
        }
    }

    // 更新
    static class UpdateAsyncTask extends AsyncTask<Student, Void, Void>{
        private StudentDao dao;

        public UpdateAsyncTask(StudentDao studentDao){
            dao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            dao.updateStudents(students);
            return null;
        }
    }

    // 条件删除
    static class DeleteAsyncTask extends AsyncTask<Student, Void, Void>{
        private StudentDao dao;

        public DeleteAsyncTask(StudentDao studentDao){
            dao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) { // 条件删除
            dao.deleteStudents(students);
            return null;
        }
    }

    // 全部删除
    static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void>{
        private StudentDao dao;

        public DeleteAllAsyncTask(StudentDao studentDao){
            dao = studentDao;
        }
        
        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllStudents();
            return null;
        }
    }

    // 全部查询
    static class QueryAllAsyncTask extends AsyncTask<Void, Void, Void>{
        private StudentDao dao;

        public QueryAllAsyncTask(StudentDao studentDao){
            dao = studentDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            List<Student> allStudent = dao.getAllStudent();

            for (Student student : allStudent) {
                Log.d("wmj", "doInBackground: " + student.toString());
            }

            return null;
        }
    }

}
