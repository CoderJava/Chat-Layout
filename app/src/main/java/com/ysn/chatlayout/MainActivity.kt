/*
 * Created by YSN Studio on 5/12/18 11:26 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/12/18 11:24 AM
 */

package com.ysn.chatlayout

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.ysn.chatlayout.adapter.AdapterChat
import com.ysn.chatlayout.adapter.model.Chat
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var listViewType: MutableList<Int>
    private lateinit var listChat: MutableList<Chat>
    private lateinit var adapterChat: AdapterChat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edit_text_chat_activity_main.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val idTypeChat = radio_group_activity_main.checkedRadioButtonId
                val typeChat = if (idTypeChat == R.id.radio_button_my_self_activity_main) {
                    AdapterChat.VIEW_TYPE_MY_SELF
                } else {
                    AdapterChat.VIEW_TYPE_USER
                }
                val message = edit_text_chat_activity_main.text.toString().trim()
                if (message.isEmpty()) {
                    Toast.makeText(this@MainActivity, "Message is empty", Toast.LENGTH_SHORT)
                            .show()
                } else {
                    val dateTime = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.US)
                            .format(Date())
                    val chat = Chat(message = message, dateTime = dateTime)
                    listViewType.add(typeChat)
                    listChat.add(chat)
                    adapterChat.notifyDataSetChanged()
                }
            }
            true
        }
        setupAdapterRecyclerView()
    }

    private fun setupAdapterRecyclerView() {
        listViewType = mutableListOf()
        listChat = mutableListOf()
        adapterChat = AdapterChat(listViewType = listViewType, listChat = listChat)
        recycler_view_chat_activity_main.layoutManager = LinearLayoutManager(this)
        recycler_view_chat_activity_main.adapter = adapterChat
    }
}
