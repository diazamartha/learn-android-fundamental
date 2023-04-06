package com.example.mynotesapp_latihan_sqlite.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.example.mynotesapp_latihan_sqlite.db.DatabaseContract.NoteColumns.Companion.TABLE_NAME
import com.example.mynotesapp_latihan_sqlite.db.DatabaseContract.NoteColumns.Companion._ID

// Setelah selesai dengan DDL mari buat sebuah kelas yang akan
// mengakomodasi kebutuhan DML bernama NoteHelper

class NoteHelper(context: Context) {

    // mendeklarasikan variabel dan constructor nya

    private var dataBaseHelper: DatabaseHelper = DatabaseHelper(context)
    private lateinit var database: SQLiteDatabase

    companion object {
        private const val DATABASE_TABLE = TABLE_NAME

        // buat 1 metode yang nantinya akan digunakan untuk menginisiasi database.
        private var INSTANCE: NoteHelper? = null
        fun getInstance(context: Context): NoteHelper =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: NoteHelper(context)
            }
    }

    //Setelah itu buat metode untuk membuka dan menutup koneksi ke database-nya.
    @Throws(SQLException::class)
    fun open() {
        database = dataBaseHelper.writableDatabase
    }
    fun close() {
        dataBaseHelper.close()
        if (database.isOpen)
            database.close()
    }

    // buat metode untuk melakukan proses CRUD-nya,
    // metode pertama adalah untuk mengambil data.
    fun queryAll(): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "$_ID ASC")
    }

    // Setelah itu, metode untuk mengambil data
    // dengan id tertentu.
    fun queryById(id: String): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            "$_ID = ?",
            arrayOf(id),
            null,
            null,
            null,
            null)
    }

    // Setelah itu, metode untuk menyimpan data.
    fun insert(values: ContentValues?): Long {
        return database.insert(DATABASE_TABLE, null, values)
    }

    // Lalu, metode untuk memperbarui data.
    fun update(id: String, values: ContentValues?): Int {
        return database.update(DATABASE_TABLE, values, "$_ID = ?", arrayOf(id))
    }

    // Terakhir metode untuk menghapus data.
    fun deleteById(id: String): Int {
        return database.delete(DATABASE_TABLE, "$_ID = '$id'", null)
    }


}