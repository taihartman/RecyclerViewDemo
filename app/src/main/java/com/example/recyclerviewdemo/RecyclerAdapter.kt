package com.example.recyclerviewdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){
    public var titles = mutableListOf<String>()
    public var details = mutableListOf<String>()
    public var images = mutableListOf<Int>()

    //our own implimentation of viewholder
    //references card layout file
    inner class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView
        init {
            itemImage = itemView.findViewById(R.id.itemImage)
            itemTitle = itemView.findViewById(R.id.itemTitle)
            itemDetail = itemView.findViewById(R.id.itemDetail)
            itemView.setOnClickListener {v: View ->
                //which row was tapped
                var position = absoluteAdapterPosition
                Snackbar.make(v,"Click detected on item ${position + 1}", Snackbar.LENGTH_LONG).setAction("Action",null).show()
            }
        }
    }
    //inflate the card layout and create instance of view holder using that layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout,parent,false)
        return ViewHolder(v)
    }//onCreateViewHolder

    //populate the card layout view for the ViewHolder

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = titles[position]
        holder.itemDetail.text = details[position]
        holder.itemImage.setImageResource(images[position])


    }//onBindViewHolder

    override fun getItemCount(): Int {
        return titles.size
    }//getItemCount

}//RecyclerAdapter