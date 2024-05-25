package com.example.githubsearchapp.ui.user.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.GithubUserData
import com.example.githubsearchapp.databinding.ItemUserBinding

class GithubUserListAdapter : RecyclerView.Adapter<GithubUserListAdapter.ViewHolder>() {

    private var githubUsers: List<GithubUserData>? = null
    private lateinit var listener: OnItemClickListener

    fun submitData(users: List<GithubUserData>) {
        this.githubUsers = users
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemUserBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        githubUsers?.let { holder.bind(it[position]) }
    }

    override fun getItemCount(): Int = githubUsers?.size ?: 0

    fun setListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onItemClicked(username: String)
    }

    inner class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: GithubUserData) {
            binding.tvUser.text = user.login
            Glide.with(binding.ivAva.context)
                .load(user.avatarUrl)
                .into(binding.ivAva)
            binding.root.setOnClickListener { listener.onItemClicked(user.login) }
        }
    }
}