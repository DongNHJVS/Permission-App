package com.dongnh.permissionsummary.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dongnh.permissionsummary.R
import com.dongnh.permissionsummary.base.BaseFragment
import com.dongnh.permissionsummary.databinding.DetailAppFragmentBinding
import com.dongnh.permissionsummary.singleton.SingletonArgument
import com.dongnh.permissionsummary.viewmodel.DetailAppViewModel

class DetailAppFragment : BaseFragment<DetailAppFragmentBinding, DetailAppViewModel>(R.layout.detail_app_fragment) {

    companion object {
        val REQUEST_CODE = 1001
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        _dataBinding.viewModel = _viewModel

        _viewModel.appPermission.value = SingletonArgument.appPermission
        SingletonArgument.appPermission.permissions?.let { _viewModel.adapterDefault.addDataList(it) }
        _dataBinding.viewDetail.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        _dataBinding.nextToSetting.setOnClickListener {
            this@DetailAppFragment.openAppSystemSettings()
        }
    }

    fun openAppSystemSettings() {
        startActivityForResult(Intent().apply {
            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            data = Uri.fromParts("package", SingletonArgument.appPermission.packagesName, null)
        }, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (REQUEST_CODE == requestCode) {
            // Back to main
            activity?.onBackPressed()
            SingletonArgument.reloadData.value = true
        }
    }

}
