package com.dongnh.permissionsummary.ultil.binding

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.dongnh.permissionsummary.ultil.exts.getParentActivity

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