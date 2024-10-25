package com.example.happybirthday

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

data class Email(val sender: String, val message: String, val time: String)

class Adapter(private val context: Context, private val emails: List<Email>) : BaseAdapter() {

    override fun getCount(): Int {
        return emails.size
    }

    override fun getItem(position: Int): Any {
        return emails[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(context)
        val view = convertView ?: inflater.inflate(R.layout.item, parent, false)

        val email = emails[position]

        val textViewInitial = view.findViewById<TextView>(R.id.textViewInitial)
        val textViewSender = view.findViewById<TextView>(R.id.textViewSender)
        val textViewMessage = view.findViewById<TextView>(R.id.textViewMessage)
        val textViewTime = view.findViewById<TextView>(R.id.textViewTime)

        textViewInitial.text = email.sender.first().toString().toUpperCase()
        textViewSender.text = email.sender
        textViewMessage.text = email.message
        textViewTime.text = email.time

        return view
    }
}