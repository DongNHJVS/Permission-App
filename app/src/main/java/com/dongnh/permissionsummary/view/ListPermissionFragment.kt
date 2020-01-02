package com.dongnh.permissionsummary.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.dongnh.permissionsummary.R
import com.dongnh.permissionsummary.base.BaseFragment
import com.dongnh.permissionsummary.databinding.ListPermissionFragmentBinding
import com.dongnh.permissionsummary.model.AppPermission
import com.dongnh.permissionsummary.ultil.interfaces.OnItemClickListener
import com.dongnh.permissionsummary.viewmodel.ListPermissionViewModel
import timber.log.Timber

class ListPermissionFragment : BaseFragment<ListPermissionFragmentBinding, ListPermissionViewModel>(R.layout.list_permission_fragment),
    OnItemClickListener {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        _dataBinding.viewModel = _viewModel
        _viewModel.adapterApp.listener = this@ListPermissionFragment
        _dataBinding.viewListApp.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        setUpEventForSwipe()

        // get data
        _viewModel.getAllApp()

        _viewModel.disableSwipe.observe(this@ListPermissionFragment, Observer {
            if (it != null && it) {
                _dataBinding.listSwipe.isRefreshing = false
            }
        })
    }

    private fun setUpEventForSwipe() {
        // ic_event reload list
        _dataBinding.listSwipe.setColorSchemeColors(
            ContextCompat.getColor(context!!, android.R.color.holo_blue_bright),
            ContextCompat.getColor(context!!, android.R.color.holo_green_light),
            ContextCompat.getColor(context!!, android.R.color.holo_orange_light),
            ContextCompat.getColor(context!!, android.R.color.holo_red_light)
        )

        _dataBinding.listSwipe.setOnRefreshListener {
            _viewModel.getAllApp()
        }
    }

    /**
     * Event lick on item
     */
    override fun onClick(view: View, entity: AppPermission) {
        Timber.e(entity.toString())
    }
}