package ic.yao.roomdatabase.data.repository

import androidx.annotation.WorkerThread
import ic.yao.roomdatabase.data.database.dao.CountryDao
import ic.yao.roomdatabase.data.database.entities.CountryEntity
import kotlinx.coroutines.flow.Flow

class CountryRepository (private val countryDao: CountryDao) {

    val countries : Flow<List<CountryEntity>> = countryDao.getAllCountries()

    @WorkerThread
    suspend fun insert(country: CountryEntity) {
        countryDao.insertCountry(country)
    }

}