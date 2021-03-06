package com.dongnh.permissionsummary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dongnh.permissionsummary.R
import com.dongnh.permissionsummary.databinding.ItemListPermissionBinding
import com.dongnh.permissionsummary.model.AppPermission
import com.dongnh.permissionsummary.ultil.interfaces.OnItemClickListener
import com.dongnh.permissionsummary.viewmodel.ItemAppPermissionViewModel

class AdapterAppPermission : RecyclerView.Adapter<AdapterAppPermission.ViewHolder>() {
    lateinit var listenerClick: OnItemClickListener
    private var dataList: ArrayList<AppPermission> = arrayListOf()

    // Create view of Adapter
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemListPermissionBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_list_permission,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    // Bind data
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position], listenerClick)
    }

    // Get item count
    override fun getItemCount(): Int {
        return dataList.size
    }

    // add view when data change
    fun addDataList(appPermissions: ArrayList<AppPermission>) {
        this.dataList.clear()
        this.dataList.addAll(appPermissions)
        notifyDataSetChanged()
    }

    // Set listener
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listenerClick = listener
    }

    // Class binding data
    class ViewHolder(private val binding: ItemListPermissionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val viewModel = ItemAppPermissionViewModel()

        fun bind(permission: AppPermission, listener: OnItemClickListener) {
            viewModel.binding(permission)
            binding.viewModel = viewModel

            binding.btnSetting.setOnClickListener {
                listener.onClickSetting(it, permission)
            }

            binding.btnHideShow.setOnClickListener {
                listener.onClickHide(it, permission)
            }

            binding.btnLockUnlock.setOnClickListener {
                listener.onClickLock(it, permission)
            }
        }
    }
}