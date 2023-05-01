package com.agn.taskapp.data.local.db

import androidx.room.*
import com.agn.taskapp.model.Task

@Dao
interface TaskDao {

   // "SELECT * FROM task ORDER BY title ASC" о
    @Query("SELECT * FROM task")
    fun getAll(): List<Task>

    //Добавить
    @Insert
    fun insert(task: Task)

    @Delete
    fun delete(task: Task)

    @Update
    fun update(task: Task)

}