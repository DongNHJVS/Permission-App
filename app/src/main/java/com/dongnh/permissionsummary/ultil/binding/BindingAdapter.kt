package com.dongnh.permissionsummary.ultil.binding

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.dongnh.permissionsummary.ultil.exts.getParentActivity
import timber.log.Timber

@BindingAdapter("adapterRecyclerView")
fun setAdapterRecyclerView(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility =
            value ?: View.VISIBLE
        })
    }
}

@BindingAdapter("mutableTextString")
fun setMutableTextString(view: TextView, text: String?) {
    if (view.text != text.toString()) {
        view.text = text.toString()
    }
}

@BindingAdapter("mutableImage")
fun setMutableImage(view: ImageView, drawable: Drawable?) {
    view.setImageDrawable(drawable)
}

@BindingAdapter("mutableImageInt")
fun setMutableImage(view: ImageView, icon: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && icon != null) {
        icon.observe(parentActivity, Observer { value ->
            if (value != null) {
                view.setImageResource(value)
            }
        })
    }
}

@BindingAdapter("mutableTextIntColor")
fun mutableTextIntColor(view: TextView, text: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value ->
            view.setTextColor(ContextCompat.getColor(parentActivity, value))
        })
    }
}

@BindingAdapter("mutableBackgound")
fun setMutableBackgound(view: View, isChangeValue: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && isChangeValue != null) {
        isChangeValue.observe(parentActivity, Observer {
                value ->
            if ((value != null)) {
                view.setBackgroundResource(value)
            }
        })
    }
}

@BindingAdapter("mutableTextString")
fun mutableTextString(view: TextView, text: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if (parentActivity != null && text != null) {
        text.observe(parentActivity, Observer { value ->
            if (value != null) {
                try {
                    view.text = parentActivity.getString(value)
                } catch (e: Exception) {
                    Timber.e(e)
                }
            }
        })
    }
}
