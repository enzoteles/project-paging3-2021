package com.example.project_paging3_2021.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.project_paging3_2021.R
import com.example.project_paging3_2021.data.remote.Character

class CharacterAdapter: PagingDataAdapter<Character, CharacterAdapter.MyViewHolder>(DiffUtilCallBack()) {

    override fun onBindViewHolder(holder: CharacterAdapter.MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterAdapter.MyViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return MyViewHolder(inflate)
    }

    class MyViewHolder(inflate: View): RecyclerView.ViewHolder(inflate){
        var title : TextView = inflate.findViewById(R.id.tvTitle)
        var subtitle : TextView = inflate.findViewById(R.id.tvSubtitle)
        fun bind(item: Character?) {
            title.text = item?.name
            subtitle.text = item?.species
        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Character>(){
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id && oldItem.name == newItem.name
        }

    }
}