package com.dongnh.permissionsummary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dongnh.permissionsummary.R
import com.dongnh.permissionsummary.databinding.ItemPermisionBinding
import com.dongnh.permissionsummary.model.PermissionItem
import com.dongnh.permissionsummary.viewmodel.ItemPermissionViewModel

class AdaterDetailPermission : RecyclerView.Adapter<AdaterDetailPermission.ViewHolder>() {
    private var dataList:ArrayList<PermissionItem> = arrayListOf()

    // Create view of Adapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemPermisionBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_permision, parent, false)
        return ViewHolder(binding)
    }

    // Bind data
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    // Get item count
    override fun getItemCount(): Int {
        return dataList.size
    }

    // add view when data change
    fun addDataList(listItem: ArrayList<PermissionItem>){
        this.dataList.addAll(listItem)
        notifyDataSetChanged()
    }

    // Class binding data
    class ViewHolder(private val binding: ItemPermisionBinding): RecyclerView.ViewHolder(binding.root){
        private val viewModel = ItemPermissionViewModel()

        fun bind(permission: PermissionItem){
            viewModel.binding(permission)
            binding.viewModel = viewModel
        }
    }
}