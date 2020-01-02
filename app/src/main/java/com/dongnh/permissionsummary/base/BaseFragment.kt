package com.dongnh.permissionsummary.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AutoCompleteTextView
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.dongnh.permissionsummary.R
import com.dongnh.permissionsummary.ultil.interfaces.OnLoadDataFormAPI
import com.dongnh.permissionsummary.ultil.helper.DialogHelper
import org.koin.androidx.viewmodel.ext.android.getViewModel
import java.lang.reflect.ParameterizedType
import kotlin.reflect.KClass

abstract class BaseFragment<T: ViewDataBinding, K: BaseViewModel>(@LayoutRes private val layoutResId: Int): Fragment(),
    OnLoadDataFormAPI {

    val alert: DialogHelper by lazy {
        DialogHelper(this@BaseFragment.requireActivity())
    }

    override fun onComplete() {
        alert.progressDismiss()
    }

    override fun onLoading() {
        alert.progressShow(getString(R.string.app_loading))
    }

    // Data Binding
    val _viewModel: K by lazy { getViewModel(viewModelClass()) }
    lateinit var _dataBinding: T

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this@BaseFragment._dataBinding =
            DataBindingUtil.inflate(inflater, this@BaseFragment.layoutResId, container, false)
        this@BaseFragment._dataBinding.lifecycleOwner = this@BaseFragment

        _viewModel.interfaceLoad = this@BaseFragment
        setupUIHideKeyBoard(this@BaseFragment._dataBinding.root, activity as Activity)
        return this@BaseFragment._dataBinding.root
    }

    /**
     * get Class type for view model
     */
    @Suppress("UNCHECKED_CAST")
    private fun viewModelClass(): KClass<K> {
        // dirty hack to get generic type https://stackoverflow.com/a/1901275/719212
        return ((javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[1] as Class<K>).kotlin
    }

    /**
     * Setup hide keyboard
     */
    fun setupUIHideKeyBoard(view: View, activity: Activity) {
        // Set up touch listener for non-text box views to hide keyboard.
        if (view !is AutoCompleteTextView) {
            view.setOnTouchListener { _, _ ->
                hideSoftKeyboard(activity)
                false
            }
        }

        //If a layout container, iterate over children and seed recursion.
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                setupUIHideKeyBoard(innerView, activity)
                innerView.clearFocus()
            }
        }
    }

    // Hide keyboard
    private fun hideSoftKeyboard(activity: Activity) {
        try {
            val inputMethodManager = activity.getSystemService(
                Activity.INPUT_METHOD_SERVICE
            ) as InputMethodManager
            if (activity.currentFocus != null) {
                inputMethodManager.hideSoftInputFromWindow(
                    activity.currentFocus!!.windowToken, 0
                )
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}