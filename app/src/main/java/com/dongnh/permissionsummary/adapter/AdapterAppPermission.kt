package com.dongnh.permissionsummary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dongnh.permissionsummary.R
import com.dongnh.permissionsummary.databinding.ItemAppPermissionBinding
import com.dongnh.permissionsummary.model.AppPermission
import com.dongnh.permissionsummary.viewmodel.ItemAppPermissionViewModel

class AdapterAppPermission : RecyclerView.Adapter<AdapterAppPermission.ViewHolder>() {
    private var dataList:ArrayList<AppPermission> = arrayListOf()

    // Create view of Adapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemAppPermissionBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_app_permission, parent, false)
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
    fun addDataList(appPermissions: ArrayList<AppPermission>){
        this.dataList.addAll(appPermissions)
        notifyDataSetChanged()
    }


    // Class binding data
    class ViewHolder(private val binding: ItemAppPermissionBinding): RecyclerView.ViewHolder(binding.root){
        private val viewModel = ItemAppPermissionViewModel()

        fun bind(permission: AppPermission){
            viewModel.binding(permission)
            binding.viewModel = viewModel
        }
    }
}