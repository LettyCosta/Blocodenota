package com.example.blocodenota.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.blocodenota.data.Task

@Dao

interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(task: Task)

    @Query("Select * From task")
     fun getAll(): LiveData<List<Task>>

    //UPDATE encontrar pelo id a tarefa que queremos alterar
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(task: Task)

    //DELETE: encontrar por ID

    @Query("DELETE from task")
       suspend fun deleteAll()

    //DELETANDO pelo id

    @Query("DELETE from task WHERE id =:id ")
     suspend fun DeleteById(id:Int)
}