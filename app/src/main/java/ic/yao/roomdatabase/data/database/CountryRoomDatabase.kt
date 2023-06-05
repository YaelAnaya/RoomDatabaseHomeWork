package ic.yao.roomdatabase.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import ic.yao.roomdatabase.data.database.dao.CountryDao
import ic.yao.roomdatabase.data.database.entities.CountryEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [CountryEntity::class], version = 1, exportSchema = false)
abstract class CountryRoomDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao

    companion object {
        /**
         * Se sigue el patrón Singleton para asegurar que solo
         * haya una instancia de la base de datos abierta al mismo tiempo.
         * */
        @Volatile
        private var INSTANCE: CountryRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): CountryRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CountryRoomDatabase::class.java,
                    "country_database"
                ).addCallback(CountryDatabaseCallback(scope)).build()
                INSTANCE = instance
                instance
            }
        }

        private class CountryDatabaseCallback(private val scope: CoroutineScope) : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch {
                        populateDatabase(database.countryDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(countryDao: CountryDao) {
            // Borrar la base de datos
            countryDao.deleteAllCountries()

            // Agregar países
            var country = CountryEntity("Argentina", 54)
            countryDao.insertCountry(country)
            country = CountryEntity("Bolivia", 591)
            countryDao.insertCountry(country)
            country = CountryEntity("Brasil", 55)
            countryDao.insertCountry(country)
            country = CountryEntity("Chile", 56)
            countryDao.insertCountry(country)
            country = CountryEntity("Colombia", 57)
            countryDao.insertCountry(country)
            country = CountryEntity("Ecuador", 593)
            countryDao.insertCountry(country)
            country = CountryEntity("Guyana", 592)
            countryDao.insertCountry(country)
        }
    }
}


