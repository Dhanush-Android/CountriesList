package com.walmart.countrieslist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.walmart.countrieslist.R
import com.walmart.countrieslist.databinding.ActivityMainBinding
import com.walmart.countrieslist.viewmodel.ListViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var countriesAdapter = CountryListAdapter(arrayListOf())
    lateinit var viewModel: ListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[ListViewModel::class.java]
        viewModel.refresh()
        binding.CountryList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = countriesAdapter
        }
        observeVM()

    }

    private fun observeVM() {
        viewModel.countries.observe(this, Observer { countries->
            countries?.let{
                countriesAdapter.updateCountries(it)
            }
        })
        viewModel.countriesLoadError.observe(this,Observer{error->
            error?.let{
                binding.listError.visibility = if(it) View.VISIBLE else View.GONE
                }
        })

    }
}