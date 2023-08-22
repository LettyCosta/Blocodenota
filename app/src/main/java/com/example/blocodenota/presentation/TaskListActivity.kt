package com.example.blocodenota

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import java.io.Serializable
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.blocodenota.data.AppDataBase
import com.example.blocodenota.data.Task
import com.example.blocodenota.presentation.TaskDetailActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {



    private lateinit var ctn_content : LinearLayout
    //ADAPTER
    private val adapter: TaskListAdapter by lazy {
        TaskListAdapter( ::onListItemClicked) }

    lateinit var dataBase : AppDataBase

    private val dao by lazy { dataBase.taskDao() }

    val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            //pegando resultado
            val data = result.data
            val taskAction = data?.getSerializableExtra(TASK_ACTION_RESULT) as TaskAction
           val task: Task = taskAction.task

            when (taskAction.actionType) {
                ActionType.DELETE.name -> DeleteById(task.id)
                ActionType.CREATE.name ->  insertIntoDataBase(task)
                ActionType.UPDATE.name ->  UpdateIntoDataBase(task)

            }

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)
        setSupportActionBar(findViewById(R.id.toolbar))



        ctn_content= findViewById(R.id.ctn_content)





        //RECYCLERVIEW    //KOTLIN,ADAPTER E RECYCLERVIEW, componentes para popular uma ui no onCreateViewHolder
       val rvtasks: RecyclerView= findViewById(R.id.ru_task_list)
        rvtasks.adapter = adapter


        val fab = findViewById<FloatingActionButton>(R.id.fab_add)
        fab.setOnClickListener{
            openTaskListDetail(null)
        }

    }

    override fun onStart() {
        super.onStart()

        dataBase = Room.databaseBuilder(
            applicationContext,
        AppDataBase::class.java, "blocodenota-database"
        ).build()

        ListFromDataBase()
    }

    private fun insertIntoDataBase(task: Task){
        CoroutineScope (IO).launch{
            dao.insert(task)
            ListFromDataBase()
        }

    }

    private fun UpdateIntoDataBase(task: Task){
        CoroutineScope (IO).launch {
            dao.update(task)
            ListFromDataBase()
        }
    }

    private fun DeleteAll(){
        CoroutineScope(IO).launch {
            dao.deleteAll()
            ListFromDataBase()
        }
    }

    private fun DeleteById(id:Int){
        CoroutineScope(IO).launch {
            dao.DeleteById(id)
            ListFromDataBase()
        }
    }


    private fun ListFromDataBase(){
        CoroutineScope(IO).launch {
            val myDataBaseList: List<Task> = dao.getAll()
            adapter.submitList(myDataBaseList)

        }
    }

    private fun  showMessage(view: View, message: String){
        Snackbar.make(view, message, Snackbar.LENGTH_LONG )
            .setAction("Action", null)
            .show()
    }


    //ACAO DE CLICK
   private fun onListItemClicked(task: Task) {
       openTaskListDetail(task)
    }

    private fun openTaskListDetail(task: Task? ){
        val intent= TaskDetailActivity.start(this, task)
        startForResult.launch(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_task_list, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_all_task -> {

                //Deletar todas as tarefas
                DeleteAll()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }

}
enum class ActionType  {
     DELETE,
     UPDATE,
     CREATE,
}

    data class  TaskAction(
        val task: Task,
        val actionType: String
    ): Serializable

const val TASK_ACTION_RESULT = "TASK_ACTION_RESULT"


