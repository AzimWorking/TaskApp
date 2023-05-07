package com.agn.taskapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "task")
data class Task(
    @PrimaryKey(autoGenerate = true) // индентифийировать данные дает ключи
    // autoGenerate = true если null
    val id: Int? = null,
    var title: String? = null,
    var desc: String? = null
) : Serializable


