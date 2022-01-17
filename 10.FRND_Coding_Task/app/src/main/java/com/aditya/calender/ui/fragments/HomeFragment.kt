package com.aditya.calender.ui.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.aditya.calender.R
import com.aditya.calender.databinding.FragmentHomeBinding
import com.aditya.calender.remote.interfaces.OnClickDate
import com.aditya.calender.remote.response.CreateTaskClass
import com.aditya.calender.remote.response.Task
import com.aditya.calender.remote.response.TaskDetail
import com.aditya.calender.ui.adapters.AppAdapter
import com.aditya.calender.viewmodels.AppViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.add_task_dialgoue.view.*
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
@RequiresApi(Build.VERSION_CODES.O)
class HomeFragment : Fragment(), OnClickDate {

    private lateinit var homeFragmentBinding: FragmentHomeBinding
    lateinit var selectedDate: LocalDate
    private val viewModel: AppViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return homeFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectedDate = LocalDate.now()
        setMonthView()
        homeFragmentBinding.btnPrevious.setOnClickListener {
            previousButtonClicked()
        }
        homeFragmentBinding.btnForward.setOnClickListener {
            nextButtonClicked()
        }
        homeFragmentBinding.btnFab.setOnClickListener {
            fabButtonClicked()
        }
    }

    private fun monthYearFromDate(date: LocalDate): String {
        val dateFormatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        return date.format(dateFormatter)
    }

    fun daysInMonthArray(date: LocalDate): ArrayList<String> {
        val daysInMonthArray: ArrayList<String> = arrayListOf()
        val yearMonth = YearMonth.from(date)

        val daysInMonth: Int = yearMonth.lengthOfMonth()

        val firstOfMonth: LocalDate = date.withDayOfMonth(1)
        val dayOfWeek: Int = firstOfMonth.dayOfWeek.value

        for (i in 1..42) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthArray.add("")
            } else {
                daysInMonthArray.add((i - dayOfWeek).toString())
            }
        }
        return daysInMonthArray
    }

    private fun setMonthView() {
        homeFragmentBinding.monthYearTV.text = monthYearFromDate(selectedDate)
        val daysInMonth: ArrayList<String> = daysInMonthArray(selectedDate)
        val adapter = AppAdapter(daysInMonth, this)
        homeFragmentBinding.calendarRecyclerView.layoutManager = GridLayoutManager(context, 7)
        homeFragmentBinding.calendarRecyclerView.adapter = adapter
    }

    private fun previousButtonClicked() {
        selectedDate = selectedDate.minusMonths(1)
        setMonthView()
    }

    private fun nextButtonClicked() {
        selectedDate = selectedDate.plusMonths(1)
        setMonthView()
    }

    fun fabButtonClicked() {
        Navigation.findNavController(requireView())
            .navigate(R.id.action_homeFragment_to_eventFragment)
    }

    override fun DateClick(position: Int, dayText: String) {
        var datetoStore = "$dayText ${monthYearFromDate(selectedDate)}"

        //Adding the Task Through Dialgoue Box
        val mDialog = LayoutInflater.from(context).inflate(R.layout.add_task_dialgoue, null)
        val mBuilder = AlertDialog.Builder(requireContext()).setView(mDialog)
        val mAlertDialog = mBuilder.show()

        mAlertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        mDialog.tv_Date_To_Save.setText(datetoStore)
        val title = mDialog.et_Task_to_added.text
        val descri = mDialog.et_Descripition_to_added.text

        mDialog.btn_toSaveTheTask.setOnClickListener {
            // Adding Details to Api
            val task = TaskDetail(datetoStore,
                title = title.toString(),
                description = descri.toString())
            val response = CreateTaskClass(1001, task)
            viewModel.createNewTask(response)
            mAlertDialog.dismiss()
        }
    }
}

