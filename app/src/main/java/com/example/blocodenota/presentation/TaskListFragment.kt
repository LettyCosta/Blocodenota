package com.example.blocodenota.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.blocodenota.R
import com.example.blocodenota.TaskListAdapter
import com.example.blocodenota.data.Task


/**
 * A simple [Fragment] subclass.
 * Use the [TaskListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TaskListFragment : Fragment() {
    private lateinit var ctn_content : LinearLayout

    //ADAPTER
    private val adapter: TaskListAdapter by lazy {
        TaskListAdapter( ::openTaskListDetail)
    }

    private val viewModel: TaskListViewModel by lazy {
        TaskListViewModel.create(requireActivity().application)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ctn_content= view.findViewById(R.id.ctn_content)


        //RECYCLERVIEW    //KOTLIN,ADAPTER E RECYCLERVIEW, componentes para popular uma ui no onCreateViewHolder
        val rvtasks: RecyclerView = view.findViewById(R.id.ru_task_list)
        rvtasks.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        ListFromDataBase()
    }

    private fun ListFromDataBase() {
        //Observer
        val ListObserver = Observer<List<Task>> { ListTasks ->

            if (ListTasks.isEmpty()){
                ctn_content.visibility=View.VISIBLE
            }else{
                ctn_content.visibility=View.GONE
            }

            adapter.submitList(ListTasks)
        }

        //Livedata
        viewModel.TaskListLiveData.observe(this, ListObserver)
    }
    private fun openTaskListDetail(task: Task ){
        val intent= TaskDetailActivity.start(requireContext(), task)
        requireActivity().startActivity(intent)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         * @return A new instance of fragment TaskListFragment.
         */

        @JvmStatic
        fun newInstance() = TaskListFragment()
    }
}