package ic.yao.roomdatabase.ui.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import ic.yao.roomdatabase.data.database.entities.CountryEntity
import ic.yao.roomdatabase.data.repository.CountryRepository
import kotlinx.coroutines.launch

class CountryViewModel ( private val repository : CountryRepository ) : ViewModel() {
    val countries: LiveData<List<CountryEntity>> = repository.countries.asLiveData()

    fun insert (country : CountryEntity) = viewModelScope.launch {
        repository.insert(country)
    }
}

class CountryViewModelFactory ( private val repository : CountryRepository ) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(CountryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CountryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}