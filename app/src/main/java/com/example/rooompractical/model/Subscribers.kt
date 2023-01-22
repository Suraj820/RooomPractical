package com.example.rooompractical.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Subscribers_table")
data class Subscribers(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "subscribers_id")
    var id: Int,

    @ColumnInfo(name = "subscribers_name")
    var name : String,

    @ColumnInfo(name = "subscribers_email")
    var email : String
)
