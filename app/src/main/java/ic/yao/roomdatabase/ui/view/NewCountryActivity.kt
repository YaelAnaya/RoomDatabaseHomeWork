package ic.yao.roomdatabase.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import ic.yao.roomdatabase.R
import ic.yao.roomdatabase.databinding.ActivityNewCountryBinding

class NewCountryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewCountryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewCountryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            addButton.setOnClickListener {
                val replyIntent = Intent()
                if (TextUtils.isEmpty(countryNameET.text) || TextUtils.isEmpty(countryCodeET.text)) {
                    setResult(RESULT_CANCELED, replyIntent)
                } else {
                    val countryName = countryNameET.text.toString()
                    val countryCode = countryCodeET.text.toString()
                    replyIntent.putExtra("COUNTRY_NAME", countryName)
                    replyIntent.putExtra("COUNTRY_CODE", countryCode.toInt())
                    setResult(RESULT_OK, replyIntent)
                }
                finish()
            }
        }
    }

}