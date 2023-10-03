package com.example.blocodenota.presentation

import com.example.blocodenota.data.Task
import java.io.Serializable

enum class ActionType  {
    DELETE,
    UPDATE,
    CREATE,

}

data class  TaskAction(
    val task: Task?, //tarefa vai ser opcional "?"
    val actionType: String
): Serializable