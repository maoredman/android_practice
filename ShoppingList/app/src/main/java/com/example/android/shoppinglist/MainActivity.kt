package com.example.android.shoppinglist

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.Toast
import java.io.Serializable
import android.os.Parcelable



class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var addItemButton: Button

    private var listItems = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState!= null) {
            listItems = savedInstanceState?.getStringArrayList("listItems") as MutableList<String>
        }

        viewManager = LinearLayoutManager(this)
        viewAdapter = ShoppingListAdapter(listItems)
        recyclerView = findViewById<RecyclerView>(R.id.shopping_list_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)
            // use a linear layout manager
            layoutManager = viewManager
            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }

        addItemButton = findViewById(R.id.add_item_button)
        addItemButton.setOnClickListener { view: View ->
            addItem(view)
        }
    }

    private fun addItem(view: View) {
        if (listItems.size >= 20) {
            Toast.makeText(this.applicationContext,"Shopping list full!", Toast.LENGTH_SHORT).show()
        }
        else {
            val message = "Want to add item"
            val intent = Intent(this, AddItemActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, message)
            }
            startActivityForResult(intent, 2)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            val item = data.getStringExtra(EXTRA_MESSAGE)
            listItems.add(item)
            viewAdapter.notifyDataSetChanged()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putStringArrayList("listItems", ArrayList(listItems))
    }

}
