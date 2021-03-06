package com.alex.hoo.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alex.hoo.common.BaseConstant
import com.alex.hoo.databinding.ShoeRecyclerItemBinding
import com.alex.hoo.db.data.Shoe
import com.alex.hoo.ui.activity.DetailActivity
import com.joe.jetpackdemo.ui.adapter.ShoeDiffCallback

/**
 * 鞋子的适配器 配合Data Binding使用
 */
class ShoeAdapter constructor(val context: Context) :
    PagingDataAdapter<Shoe, ShoeAdapter.ViewHolder>(ShoeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ShoeRecyclerItemBinding.inflate(
                LayoutInflater.from(parent.context)
                , parent
                , false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val shoe = getItem(position)
        holder.apply {
            bind(onCreateListener(shoe!!.id), shoe)
            itemView.tag = shoe
        }
    }

    /**
     * Holder的点击事件
     */
    private fun onCreateListener(id: Long): View.OnClickListener {
        return View.OnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(BaseConstant.DETAIL_SHOE_ID, id)
            context.startActivity(intent)
        }
    }


    class ViewHolder(private val binding: ShoeRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: Shoe) {
            binding.apply {
                this.listener = listener
                this.shoe = item
                executePendingBindings()
            }
        }
    }
}