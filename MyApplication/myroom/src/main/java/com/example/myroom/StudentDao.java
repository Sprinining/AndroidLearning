package com.example.myroom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDao {
    // 增
    @Insert
    void insertStudents(Student... students);

    // 改
    @Update
    void updateStudents(Student... students);

    // 条件删除
    @Delete
    void deleteStudents(Student... students);

    // 删除所有  @Delete单个条件删除
    @Query("DELETE FROM Student")
    void deleteAllStudents();

    // 查询所有
    @Query("SELECT * FROM Student ORDER BY ID DESC")
    List<Student> getAllStudent();
}
