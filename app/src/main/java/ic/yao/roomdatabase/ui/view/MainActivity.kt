package ic.yao.roomdatabase.ui.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ic.yao.roomdatabase.CountriesApplication
import ic.yao.roomdatabase.R
import ic.yao.roomdatabase.data.database.entities.CountryEntity
import ic.yao.roomdatabase.databinding.ActivityMainBinding
import ic.yao.roomdatabase.ui.adapter.CountryListAdapter
import ic.yao.roomdatabase.ui.viewmodel.CountryViewModel
import ic.yao.roomdatabase.ui.viewmodel.CountryViewModelFactory

class MainActivity : AppCompatActivity() {
    private val countryViewModel: CountryViewModel by viewModels {
        CountryViewModelFactory((application as CountriesApplication).repository)
    }
    private val newCountryActivityRequestCode = 1
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = CountryListAdapter()
        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            fab.setOnClickListener {
                val intent = Intent(this@MainActivity, NewCountryActivity::class.java)
                startActivityForResult(intent, newCountryActivityRequestCode)
            }
        }


        countryViewModel.countries.observe(this) { countries ->
            countries?.let { adapter.submitList(it) }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == newCountryActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra("COUNTRY_NAME")?.let { countryName ->
                data.getIntExtra("COUNTRY_CODE", 0).let { countryCode ->
                    val country = CountryEntity(countryName, countryCode)
                    countryViewModel.insert(country)
                }
            }
        }
    }
}