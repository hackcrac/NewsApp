package com.regxl.news.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.regxl.news.databinding.ItemArticleBinding
import com.regxl.news.network.Article

class ArticleListAdapter(_onItemClickListener: OnItemClickListener): ListAdapter<Article, ArticleListAdapter.ArticleViewHolder>(DiffCallback) {
    companion object DiffCallback: DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

    }

    interface OnItemClickListener{
        fun onItemClick(article: Article)
    }

    private var onItemClickListener:OnItemClickListener = _onItemClickListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleViewHolder {
        return ArticleViewHolder(ItemArticleBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    class ArticleViewHolder(private var binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article){
            binding.article = article
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(article)
        }
        holder.bind(article)
    }


}