package com.raghu.employeeclouddet

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import android.view.ViewGroup
import android.widget.TextView
import com.google.firebase.database.core.view.View

class empadapter (private val emplist:ArrayList<employeemodel>):RecyclerView.Adapter<empadapter.ViewHolder>()
{
     private lateinit var mlistner:onItemClickListner

        interface onItemClickListner{
            fun onitemclick(position: Int)
        }
    fun setonitemclicklistner(clickListner: onItemClickListner)
    {
        mlistner=clickListner
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =LayoutInflater.from(parent.context).inflate(R.layout.emp_list_item,parent,false)
        return ViewHolder(itemView,mlistner)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val currentemp =emplist[position]
        holder.tvempname.text=currentemp.mempname
    }


    override fun getItemCount(): Int {
return emplist.size
    }


    class ViewHolder(itemView:android.view.View,clickListner: onItemClickListner): RecyclerView.ViewHolder(itemView) {

        val tvempname :TextView= itemView.findViewById(R.id.listviewofnames)

        init {
            itemView.setOnClickListener{
                clickListner.onitemclick(adapterPosition)

            }

        }

    }

}