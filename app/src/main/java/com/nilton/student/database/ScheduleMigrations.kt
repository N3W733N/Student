package com.nilton.student.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.nilton.student.model.PhoneType
import com.nilton.student.model.PhoneType.LANDLINE

internal object ScheduleMigrations {
    private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE Student ADD COLUMN LastName TEXT")
        }
    }
    private val MIGRATION_2_3: Migration = object : Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Criar nova tabela com as informações desejadas
            database.execSQL(
                "CREATE TABLE IF NOT EXISTS `Aluno_novo` " +
                        "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        "`name` TEXT, " +
                        "`phone` TEXT, " +
                        "`email` TEXT)"
            )

            // Copiar dados da tabela antiga para a nova
            database.execSQL(
                "INSERT INTO new_student (id, name, phone, email) " +
                        "SELECT id, name, phone, email FROM Student"
            )

            // Remove tabela antiga
            database.execSQL("DROP TABLE Student")

            // Renomear a tabela nova com o nome da tabela antiga
            database.execSQL("ALTER TABLE new_student RENAME TO Student")
        }
    }
    private val MIGRATION_3_4: Migration = object : Migration(3, 4) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE Student ADD COLUMN timeOfRegistration INTEGER")
        }
    }

    private val MIGRATION_4_5: Migration = object : Migration(4, 5) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                "CREATE TABLE IF NOT EXISTS `New_student` " +
                        "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        "`name` TEXT, " +
                        "`cellPhone` TEXT, " +
                        "`landline` TEXT, " +
                        "`email` TEXT, " +
                        "`timeOfRegistration` INTEGER)"
            )

            database.execSQL(
                "INSERT INTO New_student (id, name, cellPhone, email,timeOfRegistration) " +
                        "SELECT id, name, phone, email,timeOfRegistration FROM Student"
            )

            database.execSQL("DROP TABLE Student")

            database.execSQL("ALTER TABLE New_student RENAME TO Student")
        }
    }

    val MIGRATION_5_6 = object : Migration(5, 6) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(
                "CREATE TABLE IF NOT EXISTS `New_student` (" +
                        "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                        " `name` TEXT," +
                        " `email` TEXT," +
                        " `timeOfRegistration` INTEGER)"
            )

            database.execSQL(
                "INSERT INTO New_student (id, name, email, timeOfRegistration) " +
                        "SELECT id, name, email,timeOfRegistration FROM Student"
            )

            database.execSQL(
                "CREATE TABLE IF NOT EXISTS `Phone` (" +
                        "`id` INTEGER PRIMARY KEY AUTOINCREMENT," +
                        " `number` TEXT," +
                        " `phoneType` TEXT," +
                        " `studentId` INTEGER," +
                        " FOREIGN KEY(`studentId`)" +
                        " REFERENCES `Student`(`id`)" +
                        " ON UPDATE CASCADE ON DELETE CASCADE )"
            )

            database.execSQL(
                "INSERT INTO Phone (number,studentId) " +
                        "SELECT phone, id FROM Student"
            )

            database.execSQL("UPDATE Phone SET type = ?", arrayOf(LANDLINE))
            database.execSQL("DROP TABLE Student")
            database.execSQL("ALTER TABLE New_student RENAME TO Student")
        }
    }

    val ALL_MIGRATIONS = arrayOf(
        MIGRATION_1_2,
        MIGRATION_2_3,
        MIGRATION_3_4,
        MIGRATION_4_5,
        MIGRATION_5_6
    )
}