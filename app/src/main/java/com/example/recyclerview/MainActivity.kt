package com.example.recyclerview

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity : AppCompatActivity() {
    private lateinit var rvList: RecyclerView
    private lateinit var updateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rv_list)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val itemList = mutableListOf(
            "Jhon Doe",
            "Joseph",
        )

        rvList = findViewById(R.id.rv_list)
        updateButton = findViewById(R.id.update_button)

        val messageAdapter = MessageAdapter { item ->
            startActivity(
                Intent(this, UserActivity::class.java)
                    .putExtra("user_name", item)
            )
        }

        messageAdapter.updateList(itemList)

        rvList.adapter = messageAdapter
        rvList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        updateButton.setOnClickListener {
//            itemList.add("Alex")
//            messageAdapter.updateList(itemList)
            messageAdapter.addItem("Alex")
        }

//        rvList.addItemDecoration(
//            DividerItemDecoration(
//                this,
//                RecyclerView.VERTICAL
//            )
//        )
//        rvList.layoutManager = GridLayoutManager(
//            this,
//            2
//        )
//        rvList.layoutManager = StaggeredGridLayoutManager(
//            2,
//            RecyclerView.VERTICAL
//        )
    }
}