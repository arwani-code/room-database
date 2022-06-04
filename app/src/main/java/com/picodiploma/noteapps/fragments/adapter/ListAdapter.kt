package com.picodiploma.noteapps.fragments.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.picodiploma.noteapps.R
import com.picodiploma.noteapps.data.local.entity.UserEntity
import com.picodiploma.noteapps.fragments.list.ListFragment
import com.picodiploma.noteapps.fragments.list.ListFragmentDirections
import kotlinx.android.synthetic.main.item_user.view.*

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var listUsers = emptyList<UserEntity>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = listUsers[position]
        holder.itemView.textView.text = item.id.toString()
        holder.itemView.textView2.text = item.firstName
        holder.itemView.textView3.text = item.lastName
        holder.itemView.textView4.text = item.age.toString()

        holder.itemView.row_item.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(item)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return listUsers.size
    }

    fun setData(userEntity: List<UserEntity>){
        this.listUsers = userEntity
        notifyDataSetChanged()
    }
}