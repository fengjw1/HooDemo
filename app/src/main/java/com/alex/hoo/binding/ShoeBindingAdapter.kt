package com.alex.hoo.binding

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
        if (imageUrl.contains("a")){
            loadCenterCrop(view, R.drawable.a)
        }else if (imageUrl.contains("b")){
            loadCenterCrop(view, R.drawable.b)
        }else if (imageUrl.contains("c")){
            loadCenterCrop(view, R.drawable.c)
        }else if (imageUrl.contains("d")){
            loadCenterCrop(view, R.drawable.d)
        }else if (imageUrl.contains("e")){
            loadCenterCrop(view, R.drawable.e)
        }else if (imageUrl.contains("f")){
            loadCenterCrop(view, R.drawable.f)
        }

    }
}

fun loadCenterCrop(view: ImageView, drawable: Int){
    Glide.with(view.context)
            .load(drawable)
//            .placeholder(R.drawable.glide_placeholder)
            .centerCrop()
            .into(view)
}


// 加载带圆角的头像
@BindingAdapter("imageTransFromUrl")
fun bindImageTransFromUrl(view:ImageView,imageUrl:String?){
    if(!imageUrl.isNullOrEmpty()){
        if (imageUrl.contains("a")){
            loadRundCorner(view, R.drawable.a)
        }else if (imageUrl.contains("b")){
            loadRundCorner(view, R.drawable.b)
        }else if (imageUrl.contains("c")){
            loadRundCorner(view, R.drawable.c)
        }else if (imageUrl.contains("d")){
            loadRundCorner(view, R.drawable.d)
        }else if (imageUrl.contains("e")){
            loadRundCorner(view, R.drawable.e)
        }else if (imageUrl.contains("f")){
            loadRundCorner(view, R.drawable.f)
        }

    }
}

fun loadRundCorner(view: ImageView, drawable: Int){
    Glide.with(view.context)
            .load(drawable)
            .apply(bitmapTransform(RoundedCornersTransformation(20, 0, RoundedCornersTransformation.CornerType.ALL)))
            .into(view)
}

// 文本监听器
@BindingAdapter("addTextChangedListener")
fun addTextChangedListener(editText: EditText, simpleWatcher: SimpleWatcher) {
    editText.addTextChangedListener(simpleWatcher)
}
