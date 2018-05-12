/*
 * Created by YSN Studio on 5/12/18 11:26 AM
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 5/12/18 11:24 AM
 */

package com.ysn.chatlayout

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.ysn.chatlayout.adapter.AdapterChat
import com.ysn.chatlayout.adapter.model.Chat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listViewType = mutableListOf<Int>()
        listViewType.add(1)
        listViewType.add(2)
        listViewType.add(1)
        listViewType.add(2)
        val listChat = mutableListOf<Chat>()
        listChat.add(Chat(message = "Hello", dateTime = "12-05-2018 16:36"))
        listChat.add(Chat(message = "Hi", dateTime = "12-05-2018 16:40"))
        listChat.add(Chat(message = "How are you?", dateTime = "12-05-2018 16:41"))
        listChat.add(Chat(message = "I'm fine, Thanks. You?", dateTime = "12-05-2018 16:42"))
        val adapterChat = AdapterChat(listViewType = listViewType, listChat = listChat)
        recycler_view_chat_activity_main.layoutManager = LinearLayoutManager(this)
        recycler_view_chat_activity_main.adapter = adapterChat
    }
}
