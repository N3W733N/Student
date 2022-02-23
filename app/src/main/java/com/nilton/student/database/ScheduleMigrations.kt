package com.nilton.student.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

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
    val ALL_MIGRATIONS = arrayOf(
        MIGRATION_1_2,
        MIGRATION_2_3,
        MIGRATION_3_4
    )
}