package com.example.blocodenota

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao

interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task)

    @Query("Select * From task")
    fun getAll(): List<Task>

    //UPDATE encontrar pelo id a tarefa que queremos alterar

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(task: Task)

    //DELETE: encontrar por ID

    @Query("DELETE from task")
    fun deleteAll()

    //DELETANDO pelo id

    @Query("DELETE from task WHERE id =:id ")
    fun DeleteById(id:Int)
}