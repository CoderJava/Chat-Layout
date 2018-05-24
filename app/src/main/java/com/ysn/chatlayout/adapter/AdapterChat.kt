/*
 * Created by YSN Studio on 5/24/18 9:53 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/23/18 4:39 PM
 */

package com.ysn.chatlayout.adapter

import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ysn.chatlayout.R
import com.ysn.chatlayout.adapter.model.Chat

class AdapterChat constructor(private val listViewType: List<Int>,
                              private val listChat: List<Chat>) : RecyclerView.Adapter<AdapterChat.ViewHolder>() {

    companion object {
        val VIEW_TYPE_MY_SELF = 1
        val VIEW_TYPE_USER = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_MY_SELF -> {
                val view = layoutInflater.inflate(R.layout.item_layout_chat_my_self, null)
                ViewHolderChatItemMySelf(view)
            }
            else -> {
                val view = layoutInflater.inflate(R.layout.item_layout_chat_user, null)
                ViewHolderChatItemUser(view)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val chat = listChat[position]
        listViewType[position].let {
            when (it) {
                VIEW_TYPE_MY_SELF -> {
                    val viewHolderChatItemMySelf = holder as ViewHolderChatItemMySelf
                    chat.apply {
                        viewHolderChatItemMySelf.textViewDateTime.text = this.dateTime
                        viewHolderChatItemMySelf.textViewMessage.text = this.message
                        val bitmap = BitmapFactory.decodeFile(this.image)
                        viewHolderChatItemMySelf.imageViewMessage.setImageBitmap(bitmap)
                        if (this.message.isEmpty()) {
                            viewHolderChatItemMySelf.textViewMessage.visibility = View.GONE
                            viewHolderChatItemMySelf.imageViewMessage.visibility = View.VISIBLE
                        } else {
                            viewHolderChatItemMySelf.textViewMessage.visibility = View.VISIBLE
                            viewHolderChatItemMySelf.imageViewMessage.visibility = View.GONE
                        }
                    }
                }
                else -> {
                    val viewHolderChatUser = holder as ViewHolderChatItemUser
                    chat.apply {
                        viewHolderChatUser.textViewDateTime.text = chat.dateTime
                        viewHolderChatUser.textViewMessage.text = chat.message
                        val bitmap = BitmapFactory.decodeFile(this.image)
                        viewHolderChatUser.imageViewMessage.setImageBitmap(bitmap)
                        if (this.message.isEmpty()) {
                            viewHolderChatUser.textViewMessage.visibility = View.GONE
                            viewHolderChatUser.imageViewMessage.visibility = View.VISIBLE
                        } else {
                            viewHolderChatUser.textViewMessage.visibility = View.VISIBLE
                            viewHolderChatUser.imageViewMessage.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int = listChat.size

    override fun getItemViewType(position: Int): Int = listViewType[position]

    open inner class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class ViewHolderChatItemMySelf constructor(itemView: View) : ViewHolder(itemView) {

        val textViewDateTime: TextView = itemView.findViewById(R.id.text_view_date_time_item_layout_chat_my_self)
        val textViewMessage: TextView = itemView.findViewById(R.id.text_view_message_item_layout_chat_my_self)
        val imageViewMessage: ImageView = itemView.findViewById(R.id.image_view_message_item_layout_chat_my_self)

    }

    inner class ViewHolderChatItemUser constructor(itemView: View) : ViewHolder(itemView) {

        val textViewDateTime: TextView = itemView.findViewById(R.id.text_view_date_time_item_layout_chat_user)
        val textViewMessage: TextView = itemView.findViewById(R.id.text_view_message_item_layout_chat_user)
        val imageViewMessage: ImageView = itemView.findViewById(R.id.image_view_message_item_layout_chat_user)

    }

}