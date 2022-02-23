package com.nilton.student.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.ContextMenu.ContextMenuInfo
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.nilton.student.R
import com.nilton.student.model.Student
import com.nilton.student.ui.ListStudentsView
import com.nilton.student.ui.activity.ConstantsActivities.Companion.STUDENT_KEY
import kotlinx.android.synthetic.main.activity_list_students.*

class ListStudentsActivity : AppCompatActivity() {

    private val TITULO_APPBAR = "Lista de alunos"
    private var listStudentsView: ListStudentsView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_students)
        title = TITULO_APPBAR
        listStudentsView = ListStudentsView().listStudentsView(this)
        setupViews()
        configList()
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater
            .inflate(R.menu.activity_list_students_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        if (itemId == R.id.activity_lista_alunos_menu_remover) {
            listStudentsView?.confirmRemove(item)
        }
        return super.onContextItemSelected(item)
    }

    private fun setupViews() {
        new_student_button_activity_list_students.setOnClickListener {
            openFormModeAddStudent()
        }
    }

    private fun openFormModeAddStudent() {
        startActivity(Intent(this, FormStudentsActivity::class.java))
    }

    override fun onResume() {
        super.onResume()
        listStudentsView?.updateStudents()
    }

    private fun configList() {
        listStudentsView?.configAdapter(list_view_activity_list_students)
        configItemClickListener(list_view_activity_list_students)
        registerForContextMenu(list_view_activity_list_students)
    }

    private fun configItemClickListener(listView: ListView) {
        listView.onItemClickListener =
            OnItemClickListener { adapterView: AdapterView<*>, view: View?, posicao: Int, id: Long ->
                val studentChosen = adapterView.getItemAtPosition(posicao) as Student
                openFormModeEditStudent(studentChosen)
            }
    }

    private fun openFormModeEditStudent(student: Student) {
        val intent = Intent(
            this@ListStudentsActivity,
            FormStudentsActivity::class.java
        )
        intent.putExtra(STUDENT_KEY, student)
        startActivity(intent)
    }
}