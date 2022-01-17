package com.aditya.calender.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.aditya.calender.R
import com.aditya.calender.databinding.RecyclerToshowTaskBinding
import com.aditya.calender.remote.interfaces.OnCLickDelete

class TasklistAdaptor(
    var listoftask: List<com.aditya.calender.remote.responses.getResponse.Task>,
    val listener: OnCLickDelete
) : RecyclerView.Adapter<TaskListHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListHolder {

        val recyclerToshowTaskBinding: RecyclerToshowTaskBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.recycler_toshow_task, parent, false)

        return TaskListHolder(recyclerToshowTaskBinding,listener)
    }

    override fun onBindViewHolder(holder: TaskListHolder, position: Int) {

        val list = listoftask[position]
        holder.setData(list)
        holder.recyclerToshowTaskBinding.btnDeleteTask.setOnClickListener {
            listener.DeleteClick(position,holder.recyclerToshowTaskBinding.task?.taskId.toString().toInt())
        }
    }
    override fun getItemCount(): Int {
        return listoftask.size
    }
}

class TaskListHolder(
    val recyclerToshowTaskBinding: RecyclerToshowTaskBinding,
    val listener : OnCLickDelete
) : RecyclerView.ViewHolder(recyclerToshowTaskBinding.root) {

    fun setData(task: com.aditya.calender.remote.responses.getResponse.Task) {
        recyclerToshowTaskBinding.tvTtile.text = "task.taskdetails.title"

        Log.d("TAG", "setData: ${task.taskdetails}")
        recyclerToshowTaskBinding.task = task
        recyclerToshowTaskBinding.onClick = listener
        recyclerToshowTaskBinding.taskidone.setText(task.taskId.toString())
    }

}
