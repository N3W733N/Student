package com.nilton.student.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.Calendar

@Entity
class Student : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var name: String? = null
    var email: String? = null
    var timeOfRegistration = Calendar.getInstance()

    override fun toString(): String {
        return name.toString()
    }

    fun hasValidID(): Boolean {
        return id > 0
    }
}