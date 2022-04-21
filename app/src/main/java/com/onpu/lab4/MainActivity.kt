package com.onpu.lab4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.onpu.lab4.databinding.ActivityMainBinding
import com.onpu.lab4.model.ItemModel
import com.onpu.lab4.ui.RVAdapter
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rvAdapter: RVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupBinding()
        setupRecycleView()
    }

    private fun setupBinding() {
        supportActionBar?.hide()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupRecycleView() {
        rvAdapter = RVAdapter {
            Toast.makeText(applicationContext, "Clicked on ${it.name}", Toast.LENGTH_SHORT).show()
        }
        binding.rvFeed.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = rvAdapter
        }

        rvAdapter.submitList(jsonRawDecode())
    }

    private fun jsonRawDecode(): List<ItemModel> {
        val itemsListType = object : TypeToken<List<ItemModel?>?>() {}.type

        val sb = StringBuffer()
        val br = BufferedReader(InputStreamReader(resources.openRawResource(R.raw.data_sample)))
        var temp: String?
        while (br.readLine().also { temp = it } != null) sb.append(temp)

        return Gson().fromJson(sb.toString(), itemsListType)
    }

}