package com.example.recyclerviewdemo

import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var layoutManager: RecyclerView.LayoutManager? = null
    private lateinit var adapter: RecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        adapter = RecyclerAdapter()
        adapter.titles = resources.getStringArray(R.array.titlesArray).toMutableList()
        adapter.details = resources.getStringArray(R.array.detailsArray).toMutableList()
        adapter.images = mutableListOf(R.drawable.android_image_1,R.drawable.android_image_2,R.drawable.android_image_3,R.drawable.android_image_4,R.drawable.android_image_5,R.drawable.android_image_6,R.drawable.android_image_7,R.drawable.android_image_8)
        binding.recyclerView.adapter = adapter
        setRecyclerViewItemTouchListener()
    }


    private fun setRecyclerViewItemTouchListener(){
        //What events to listen for : 2 params, 1 for drag direction, 1 for swipe direction
        //only interested in the swipe, so pass 0 to not respond to drag events
        val itemTouchCallback = object: ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                //return false
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.absoluteAdapterPosition
                adapter.titles.removeAt(position)
                adapter.details.removeAt(position)
                adapter.images.removeAt(position)
                adapter.notifyItemRemoved(position)
            }
        }//callback
        //init listener and attach to recycler view
        val itemTouchHelper= ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)


    }
}