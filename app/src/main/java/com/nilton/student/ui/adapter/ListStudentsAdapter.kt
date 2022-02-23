package com.nilton.student.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.nilton.student.R
import com.nilton.student.model.Student
import kotlinx.android.synthetic.main.item_student.view.*

class ListStudentsAdapter : BaseAdapter() {
    private val students = ArrayList<Student>()

    override fun getCount(): Int {
        return students.size
    }

    override fun getItem(position: Int): Student {
        return students[position]
    }

    override fun getItemId(position: Int): Long {
        return students[position].id.toLong()
    }

    override fun getView(position: Int, p1: View?, viewGroup: ViewGroup?): View {
        val viewCreated = createView(viewGroup, viewGroup?.context)
        val studentReturned = students[position]
        setupView(viewCreated, studentReturned)
        return viewCreated
    }

    private fun setupView(view: View, student: Student) {
        view.item_student_name.text = student.name
        view.item_student_phone.text = student.phone
    }

    private fun createView(viewGroup: ViewGroup?, context: Context?): View {
        return LayoutInflater
            .from(context)
            .inflate(R.layout.item_student, viewGroup, false)
    }

    fun updateStudent(value: ArrayList<Student>) {
        students.clear()
        students.addAll(value)
        notifyDataSetChanged()
    }

    fun remove(student: Student?) {
        students.remove(student)
        notifyDataSetChanged()
    }
}