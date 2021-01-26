package com.alex.hoo.ui.activity

import android.animation.Animator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.alex.hoo.R
import com.alex.hoo.common.BaseConstant
import com.alex.hoo.databinding.DetailActivityBinding
import com.alex.hoo.utils.AppPrefsUtils
import com.alex.hoo.viewmodel.CustomViewModelProvider
import com.alex.hoo.viewmodel.DetailModel

/**
 * 展示鞋子细节的界面
 */
class DetailActivity : AppCompatActivity() {

    private val detailModel: DetailModel by viewModels {
        CustomViewModelProvider.providerDetailModel(
            this
            , intent.getLongExtra(BaseConstant.DETAIL_SHOE_ID, 1L)
            , AppPrefsUtils.getLong(BaseConstant.SP_USER_ID)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.detail_activity)

        val binding = DataBindingUtil.setContentView<DetailActivityBinding>(this, R.layout.detail_activity)
        binding.model = detailModel
        initListener(binding)
    }

    private fun initListener(binding: DetailActivityBinding) {
        binding.lifecycleOwner = this

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

        // 设置点击动画
        binding.fbFavourite.setOnClickListener {
            binding.fbFavourite.animate()
                .rotation(360.0f)
                .scaleX(0.0f)
                .scaleY(0.0f)
                .setListener(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(animation: Animator?) {

                    }

                    override fun onAnimationCancel(animation: Animator?) {
                    }

                    override fun onAnimationStart(animation: Animator?) {
                    }

                    override fun onAnimationEnd(animation: Animator?) {
                        detailModel.favourite()
                    }

                })
                .setDuration(200)
                .start()
        }
    }
}
