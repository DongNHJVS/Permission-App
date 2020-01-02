package com.dongnh.permissionsummary.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.dongnh.permissionsummary.R
import com.dongnh.permissionsummary.viewmodel.ListPermissionViewModel

class ListPermissionFragment : Fragment() {

    companion object {
        fun newInstance() = ListPermissionFragment()
    }

    private lateinit var viewModel: ListPermissionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_permission_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListPermissionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
