package ic.yao.roomdatabase

import android.app.Application
import ic.yao.roomdatabase.data.database.CountryRoomDatabase
import ic.yao.roomdatabase.data.repository.CountryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CountriesApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    private val database by lazy { CountryRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { CountryRepository(database.countryDao()) }

    override fun onCreate() {
        super.onCreate()
    }
}