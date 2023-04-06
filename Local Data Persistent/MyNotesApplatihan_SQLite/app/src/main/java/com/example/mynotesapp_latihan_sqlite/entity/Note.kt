package com.example.mynotesapp_latihan_sqlite.entity
// langkah 1
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// Kelas model ini merepresentasikan data yang tersimpan
// dan memberi kemudahan menulis kode.
@Parcelize
data class Note(
    var id: Int = 0,
    var title: String? = null,
    var description: String? = null,
    var date: String? = null
): Parcelable
