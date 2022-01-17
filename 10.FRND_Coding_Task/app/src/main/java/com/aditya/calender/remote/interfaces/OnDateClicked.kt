package com.aditya.calender.remote.interfaces

interface OnDateClicked {

    fun onDateClicked(position: Int, date: String)
}

interface OnDeleteClicked {
    fun onDelete(position: Int, task_id: Int)
}

interface OnClickDate {

    fun DateClick(position: Int, dayText: String)

}

interface OnCLickDelete {

    fun DeleteClick(position: Int , taskid: Int)
}