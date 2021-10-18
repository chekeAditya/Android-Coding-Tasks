package com.example.unit_5assignment.remote.localDatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_tv_maze")
data class DataTable(
    @ColumnInfo(name = "countryName")
    var countryName: String?,
    @ColumnInfo(name = "birthDate")
    var birthDate: String?,
    @ColumnInfo(name = "deathDate")
    var deathDate: String?,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null

)