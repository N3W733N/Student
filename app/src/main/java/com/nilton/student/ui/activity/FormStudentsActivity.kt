package com.nilton.student.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nilton.student.R
import com.nilton.student.database.ScheduleDatabase
import com.nilton.student.database.dao.StudentDAO
import com.nilton.student.model.Student
import com.nilton.student.ui.activity.ConstantsActivities.Companion.STUDENT_KEY
import kotlinx.android.synthetic.main.activity_form_students.*

class FormStudentsActivity : AppCompatActivity() {
    private val TITLE_APPBAR_NEW_STUDENT = "Novo aluno"
    private val TITLE_APPBAR_EDIT_STUDENT = "Edita aluno"
    private var dao: StudentDAO? = null
    private var student: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_students)
        val database = ScheduleDatabase.getInstance(this)
        dao = database.roomStudentDAO
        setupViews()
        setStudent()
    }

    private fun setStudent() {
        if (intent.hasExtra(STUDENT_KEY)) {
            title = TITLE_APPBAR_EDIT_STUDENT
            student = intent.getSerializableExtra(STUDENT_KEY) as Student?
            fillInFields()
        } else {
            title = TITLE_APPBAR_NEW_STUDENT
            student = Student()
        }
    }

    private fun fillInFields() {
        student_name_activity_form.setText(student?.name)
//        student_phone_activity_form.setText(student?.cellPhone)
//        student_landline_activity_form.setText(student?.landline)
        student_email_activity_form.setText(student?.email)
    }

    private fun endForm() {
        fillStudent()
        if (student?.hasValidID() == true) {
            dao?.editStudent(student)
        } else {
            dao?.saveStudent(student)
        }
        finish()
    }

    private fun setupViews() {
        save_student_activity_form.setOnClickListener {
            endForm()
        }
    }

    private fun fillStudent() {
        val name = student_name_activity_form.text.toString()
        val phone = student_phone_activity_form.text.toString()
        val landline = student_landline_activity_form.text.toString()
        val email = student_email_activity_form.text.toString()
        student?.name = name
//        student?.cellPhone = phone
//        student?.landline = landline
        student?.email = email
    }
}