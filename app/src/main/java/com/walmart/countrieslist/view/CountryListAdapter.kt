package com.walmart.countrieslist.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.walmart.countrieslist.R
import com.walmart.countrieslist.databinding.ListItemBinding
import com.walmart.countrieslist.model.Country

class CountryListAdapter(var countries: ArrayList<Country>): RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {
    fun updateCountries(newCountries: List<Country>){
        countries.clear()
        countries.addAll(newCountries)
        val sizeOld = countries.size
        val sizeNew = newCountries.size
        if(sizeOld == sizeNew){
            notifyItemRangeChanged(0,sizeNew)
        }else if(sizeOld>sizeNew) {
            notifyItemRangeChanged(0, sizeNew)
            notifyItemRangeRemoved(sizeNew, sizeOld - sizeNew)
        }else{
            notifyItemRangeChanged(0, sizeOld)
            notifyItemRangeInserted(sizeOld, sizeNew - sizeOld)
        }

    }
    class CountryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private var binding = ListItemBinding.bind(itemView)
        fun bind(country: Country){
            binding.countryName.text = country.countryName
            binding.countryCapital.text = country.countryCapital
            binding.countryRegion.text = country.countryRegion
            binding.countryCode.text = country.countryCode
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountryViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
    )

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }
}


