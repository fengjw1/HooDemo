package com.alex.hoo.binding

import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.alex.hoo.R
import com.alex.hoo.common.listener.SimpleWatcher
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view:ImageView,imageUrl:String?){
    if(!imageUrl.isNullOrEmpty()){
        Log.d("bindImageFromUrl", imageUrl)
        Glide.with(view.context)
            .load(imageUrl)
//            .placeholder(R.drawable.glide_placeholder)
            .centerCrop()
            .into(view)
    }
}

// 加载带圆角的头像
@BindingAdapter("imageTransFromUrl")
fun bindImageTransFromUrl(view:ImageView,imageUrl:String?){
    if(!imageUrl.isNullOrEmpty()){
        when(imageUrl){
            is "a" ->
        }
        Glide.with(view.context)
            .load()
            .apply(bitmapTransform(RoundedCornersTransformation(20, 0, RoundedCornersTransformation.CornerType.ALL)))
            .into(view)
    }
}

// 文本监听器
@BindingAdapter("addTextChangedListener")
fun addTextChangedListener(editText: EditText, simpleWatcher: SimpleWatcher) {
    editText.addTextChangedListener(simpleWatcher)
}
