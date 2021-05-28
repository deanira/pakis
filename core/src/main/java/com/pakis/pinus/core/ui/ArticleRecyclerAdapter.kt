package com.pakis.pinus.core.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pakis.pinus.core.R
import com.pakis.pinus.core.databinding.ItemArticleBinding
import com.pakis.pinus.core.domain.model.Article
import com.pakis.pinus.core.ui.webview.WebviewActivity
import com.pakis.pinus.core.ui.webview.WebviewActivity.Companion.URL
import com.pakis.pinus.core.utils.Helper.loadImage

class ArticleRecyclerAdapter : RecyclerView.Adapter<ArticleRecyclerAdapter.ListViewHolder>() {

    var mData = ArrayList<Article>()

    fun setData(items: ArrayList<Article>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemArticleBinding.bind(itemView)
        fun bind(items: Article) {
            binding.tvTitle.text = items.judul
            binding.tvDescription.text = items.deskripsi
            binding.ivBanner.loadImage(items.bannerPath)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, WebviewActivity::class.java)
                intent.putExtra(URL, items.link)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int {
        return mData.size
    }
}