package com.dongnh.permissionsummary.view

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dongnh.permissionsummary.R
import com.dongnh.permissionsummary.base.BaseFragment
import com.dongnh.permissionsummary.databinding.DetailAppFragmentBinding
import com.dongnh.permissionsummary.singleton.SingletonArgument
import com.dongnh.permissionsummary.viewmodel.DetailAppViewModel

class DetailAppFragment : BaseFragment<DetailAppFragmentBinding, DetailAppViewModel>(R.layout.detail_app_fragment) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        _dataBinding.viewModel = _viewModel

        _viewModel.appPermission.value = SingletonArgument.appPermission
        SingletonArgument.appPermission.permissions?.let { _viewModel.adapterDefault.addDataList(it) }
        _dataBinding.viewDetail.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

}
