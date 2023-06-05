package ic.yao.roomdatabase.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries_table")
data class CountryEntity (
    @PrimaryKey
    val name: String,
    val code : Int
    )