package ic.yao.roomdatabase.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ic.yao.roomdatabase.data.database.entities.CountryEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface CountryDao {
    @Query("SELECT * FROM countries_table ORDER BY name ASC")
    fun getAllCountries(): Flow<List<CountryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountry(country: CountryEntity)

    @Query("DELETE FROM countries_table")
    suspend fun deleteAllCountries()
}