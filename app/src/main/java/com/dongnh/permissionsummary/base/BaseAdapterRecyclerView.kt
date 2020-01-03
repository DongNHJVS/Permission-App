package com.dongnh.permissionsummary.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapterRecyclerView <M, S: BaseAdapterRecyclerView.ViewHolderViewModel<M>> : RecyclerView.Adapter<S>() {

    // Data list for view
    private val datalist: ArrayList<M> = arrayListOf()

    final override fun getItemCount(): Int {
        return this@BaseAdapterRecyclerView.datalist.count()
    }

    override fun onBindViewHolder(holder: S, position: Int) {
        holder.bind(this@BaseAdapterRecyclerView.datalist[position], position)
    }

    abstract class ViewHolderViewModel<M>(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

        /**
         * Override this method to update data when holder inflated from adapter
         *
         * @param item M
         * @param position Int
         */
        open fun bind(item: M, position: Int) = Unit

    }
}