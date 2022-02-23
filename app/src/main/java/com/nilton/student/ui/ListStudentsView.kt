package com.nilton.student.ui

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.MenuItem
import android.widget.AdapterView.AdapterContextMenuInfo
import android.widget.ListView
import com.nilton.student.database.ScheduleDatabase
import com.nilton.student.database.dao.StudentDAO
import com.nilton.student.model.Student
import com.nilton.student.ui.adapter.ListStudentsAdapter

class ListStudentsView {
    private var adapter: ListStudentsAdapter? = null
    private var dao: StudentDAO? = null
    private var context: Context? = null

    fun listStudentsView(context: Context):ListStudentsView {
        this.context = context
        adapter = ListStudentsAdapter()
        dao = ScheduleDatabase.getInstance(context)
            .roomStudentDAO
        return this
    }

    fun confirmRemove(item: MenuItem) {
        AlertDialog.Builder(context)
            .setTitle("Removendo aluno")
            .setMessage("Tem certeza que quer remover o aluno?")
            .setPositiveButton("Sim") { dialogInterface: DialogInterface?, i: Int ->
                val menuInfo = item.menuInfo as AdapterContextMenuInfo
                val studentChosen = adapter?.getItem(menuInfo.position)
                remove(studentChosen)
            }
            .setNegativeButton("Não", null)
            .show()
    }

    fun updateStudents() {
        dao?.getAll()?.let {
            adapter?.updateStudent(it as ArrayList<Student>)
        }
    }

    private fun remove(student: Student?) {
        dao?.removeStudent(student)
        adapter?.remove(student)
    }

    fun configAdapter(studentListView: ListView) {
        studentListView.adapter = adapter
    }
}