package com.nilton.student.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.nilton.student.database.converter.PhoneTypeConverter

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Student::class,
            parentColumns = ["id"],
            childColumns = ["studentId"],
            onUpdate = CASCADE,
            onDelete = CASCADE
        )
    ]
)
class Phone {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
    var number: String? = null
    var phoneType: PhoneType? = null
    var studentId: Int? = null
}