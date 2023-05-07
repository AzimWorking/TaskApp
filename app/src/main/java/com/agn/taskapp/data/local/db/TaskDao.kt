package com.agn.taskapp.data.local.db

import androidx.room.*
import com.agn.taskapp.model.Task

@Dao
interface TaskDao {

    // "SELECT * FROM task ORDER BY title ASC" для сортировки от я до а
    // "SELECT * FROM task ORDER BY title DESK" для сортировки от а до я
    @Query("SELECT * FROM task")  //sql запрос для получения даныых
    fun getAll(): List<Task>

    //Добавить
    @Insert
    fun insert(task: Task)

    //Удалить
    @Delete
    fun delete(task: Task)

    //Обновление данных
    @Update
    fun update(task: Task)

}