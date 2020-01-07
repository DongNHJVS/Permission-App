package com.dongnh.permissionsummary.view

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dongnh.permissionsummary.R
import com.dongnh.permissionsummary.base.BaseFragment
import com.dongnh.permissionsummary.const.*
import com.dongnh.permissionsummary.databinding.FragmentListPermissionBinding
import com.dongnh.permissionsummary.model.AppPermission
import com.dongnh.permissionsummary.singleton.SingletonArgument
import com.dongnh.permissionsummary.ultil.exts.addFragmentSafeLy
import com.dongnh.permissionsummary.ultil.interfaces.OnItemClickListener
import com.dongnh.permissionsummary.viewmodel.ListPermissionViewModel
import timber.log.Timber

class ListPermissionFragment : BaseFragment<FragmentListPermissionBinding, ListPermissionViewModel>(R.layout.fragment_list_permission),
    OnItemClickListener {

    val checkedItems = booleanArrayOf(false, false, false, false,
        false, false, false, false, false, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        _dataBinding.viewModel = _viewModel
        _viewModel.adapterApp.listener = this@ListPermissionFragment
        _dataBinding.viewListApp.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        setUpEventForSwipe()

        _viewModel.disableSwipe.observe(this@ListPermissionFragment, Observer {
            if (it != null && it) {
                _dataBinding.listSwipe.isRefreshing = false
            }
        })

        SingletonArgument.reloadData.observe(this@ListPermissionFragment, Observer {
            if (it != null && it) {
                _viewModel.getAllApp()
            }
        })

        _dataBinding.toolBar.filterBtn.setOnClickListener {
            setUpDialogFilter()
        }
    }

    override fun onResume() {
        super.onResume()
        // get data
        _viewModel.getAllApp()
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
        SingletonArgument.appPermission = entity
        activity?.let {
            try {
                it as MainActivity
                it.addFragmentSafeLy(DetailAppFragment(),
                    DetailAppFragment::class.java.simpleName, false, R.id.main_layout, 0, 0, 0, 0)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun setUpDialogFilter() {
        // Set up the alert builder
        val builder = AlertDialog.Builder(context)

        // Add a checkbox list
        val permission = arrayOf(SMS, PHONE, EXTERNAL, CAMERA, LOCATION,
            RECORD_AUDIO, CALENDAR, INTERNET_FILTER, VOICEMAIL, SENSORS)
        builder.setMultiChoiceItems(permission, checkedItems) { dialog, which, isChecked ->
            // Reset all check box
            if (isChecked) {
                _viewModel.listPermissionFilter.add(permission[which])
            } else {
                _viewModel.listPermissionFilter.remove(permission[which])
            }

            // after updating the array
            ((dialog as AlertDialog).listView.adapter as BaseAdapter).notifyDataSetChanged()
        }

        // Add OK and Cancel buttons
        builder.setPositiveButton(getString(R.string.app_dialog_btn_ok)) { dialog, which ->
            // Show dot in filter
            if (_viewModel.listPermissionFilter.size > 0) {
                _dataBinding.toolBar.showDot.visibility = View.VISIBLE
            }
            _viewModel.getAllApp()
        }
        builder.setNegativeButton(getString(R.string.app_dialog_btn_cancel), null)

        // Create and show the alert dialog
        val dialog = builder.create()
        if (!activity?.isDestroyed!!) {
            dialog.show()
        }
    }
}
