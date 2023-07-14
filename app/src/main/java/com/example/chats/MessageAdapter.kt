package com.example.chats

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(val context:Context,val messagelist:ArrayList<Message>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

     val ITEM_RECEIVE = 1
     val ITEM_SENT =2


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == 1){
            val view:View = LayoutInflater.from(context).inflate(R.layout.recive,parent,false)
            return ReceiveViewHolder(view)

        }else{
            val view:View = LayoutInflater.from(context).inflate(R.layout.send,parent,false)
            return SendViewHolder(view)

        }


    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val  currentMessage = messagelist[position]
        if (holder.javaClass==SendViewHolder::class.java){

          val viewHolder = holder as SendViewHolder
            holder.SentMessage.text =currentMessage.message

        }
        else{
            val viewHolder = holder as ReceiveViewHolder
            holder.receiveMessage.text =currentMessage.message
        }



    }
    override fun getItemCount(): Int {
        return messagelist.size
    }

    override fun getItemViewType(position: Int): Int {

        val currentMessage = messagelist[position]

        if(FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)){
          return ITEM_SENT
        }
        else{
          return  ITEM_RECEIVE
        }
    }

    class SendViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
         val SentMessage = itemView.findViewById<TextView>(R.id.txt_sent_message)
    }

    class ReceiveViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val receiveMessage = itemView.findViewById<TextView>(R.id.txt_receive_message)
    }
}