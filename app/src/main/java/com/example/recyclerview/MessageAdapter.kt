package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class MessageAdapter(
    private val onClick: (String) -> Unit
): Adapter<MessageAdapter.MessageAdapterViewHolder>() {
    private var itemList = mutableListOf<String>()

    fun addItem(item: String) {
        itemList.add(item)
        notifyItemInserted(itemList.indexOf(item))
    }

    fun updateList(list: MutableList<String>) {
        itemList = list
        notifyDataSetChanged()
    }

    inner class MessageAdapterViewHolder(val itemView: View): ViewHolder(itemView) {
        val nameText = itemView.findViewById<TextView>(R.id.card_name)

        fun onHolderClick(holderClickCallback: () -> Unit) {
            itemView.setOnClickListener {
                holderClickCallback()
            }
        }
//        val lastMessageText = itemView.findViewById<TextView>(R.id.last_message_text)
//        val timeText = itemView.findViewById<TextView>(R.id.time_text)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MessageAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(
            R.layout.list_item_card_view,
            parent,
            false
        )

        return MessageAdapterViewHolder(itemView)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: MessageAdapterViewHolder, position: Int) {
        val currentItem = itemList[position]

        holder.nameText.text = currentItem

        holder.onHolderClick {
            onClick(currentItem)
        }
    }
}