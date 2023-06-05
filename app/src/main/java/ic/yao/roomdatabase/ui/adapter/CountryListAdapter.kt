package ic.yao.roomdatabase.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ic.yao.roomdatabase.R
import ic.yao.roomdatabase.data.database.entities.CountryEntity
import ic.yao.roomdatabase.databinding.CountryItemBinding

class CountryListAdapter : ListAdapter<CountryEntity, CountryListAdapter.CountryViewHolder>(CountriesComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(
            CountryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ).root
        )
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = getItem(position)
        holder.bind(country)
    }

    class CountryViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val countryTitle = itemView.findViewById<TextView>(R.id.country_title)
        private val countryCode = itemView.findViewById<TextView>(R.id.country_code)
        fun bind ( country : CountryEntity) {
            countryTitle.text = country.name
            countryCode.text = country.code.toString()
        }
    }

    class CountriesComparator : DiffUtil.ItemCallback<CountryEntity>() {
        override fun areItemsTheSame(oldItem: CountryEntity, newItem: CountryEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CountryEntity, newItem: CountryEntity): Boolean {
            return oldItem.code == newItem.code
        }
    }


}