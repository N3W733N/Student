package com.nilton.student.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.nilton.student.model.Phone

@Dao
interface PhoneDAO {
    @Query(
        "SELECT p.* FROM Phone p" +
                " JOIN Student s" +
                " ON p.studentId = s.id " +
                "WHERE p.studentId = :studentId " +
                "LIMIT 1"
    )
    fun findFirstStudentPhone(studentId: Int):Phone

}
