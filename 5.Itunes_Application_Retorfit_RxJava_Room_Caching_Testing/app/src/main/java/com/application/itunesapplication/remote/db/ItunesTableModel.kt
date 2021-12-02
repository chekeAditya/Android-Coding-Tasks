package com.application.itunesapplication.remote.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


data class ItunesTableModel(
    @PrimaryKey
    @ColumnInfo(name = "artistName")
    val artistName: String,
    @PrimaryKey
    @ColumnInfo(name = "songName")
    val songName: String,
    @PrimaryKey
    @ColumnInfo(name = "urlSong")
    val urlSong: String,
){
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int? = null
}