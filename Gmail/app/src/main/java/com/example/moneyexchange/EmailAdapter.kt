package com.example.moneyexchange

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EmailAdapter(private val emailList: List<Email>) :
    RecyclerView.Adapter<EmailAdapter.EmailViewHolder>() {

    inner class EmailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val emailIcon: TextView = itemView.findViewById(R.id.email_icon)
        val emailSender: TextView = itemView.findViewById(R.id.email_sender)
        val emailTitle: TextView = itemView.findViewById(R.id.email_title)
        val emailTime: TextView = itemView.findViewById(R.id.email_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmailViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.email_item, parent, false)
        return EmailViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EmailViewHolder, position: Int) {
        val currentItem = emailList[position]
        holder.emailIcon.text = currentItem.sender.first().toString()
        holder.emailIcon.backgroundTintList = ColorStateList.valueOf(currentItem.color)
        holder.emailSender.text = currentItem.sender
        holder.emailTitle.text = currentItem.title
        holder.emailTime.text = currentItem.time
    }

    override fun getItemCount() = emailList.size
}
