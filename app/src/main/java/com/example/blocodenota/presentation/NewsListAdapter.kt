package com.example.blocodenota.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.blocodenota.R
import com.example.blocodenota.TasklistViewHolder
import com.example.blocodenota.data.News
import com.example.blocodenota.data.Task

class NewsListAdapter: ListAdapter <News, NewsListViewHolder>(NewsListAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
        val view: View = LayoutInflater
            .from(parent.context).inflate(
            R.layout.item_news,parent,false)

        return NewsListViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
        val news = getItem(position)
        holder.bind(news)
    }

    companion object: DiffUtil.ItemCallback<News>(){
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return  oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.Title == newItem.Title &&
                    oldItem.ImgURL == newItem.ImgURL
        }

    }


}

class NewsListViewHolder(
private val view:View
): RecyclerView.ViewHolder(view){

    private val tvTitle = view.findViewById<TextView>(R.id.tv_news_title)
    private val imgNews = view.findViewById<ImageView>(R.id.iv_news)

    fun bind(
        news: News
    ){
        tvTitle.text = news.Title
        imgNews.load(news.ImgURL){
            transformations(RoundedCornersTransformation(12f))
        }
    }
}