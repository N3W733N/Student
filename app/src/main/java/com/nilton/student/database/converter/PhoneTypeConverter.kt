package com.nilton.student.database.converter

import androidx.room.TypeConverter
import com.nilton.student.model.PhoneType

class PhoneTypeConverter {

    @TypeConverter
    fun paraString(value: PhoneType): String {
        return value.name
    }

    @TypeConverter
    fun toPhoneType(value: String?): PhoneType {
        var phoneType = PhoneType.LANDLINE
        value?.let {
            phoneType = PhoneType.valueOf(it)
        }
        return phoneType
    }
}
