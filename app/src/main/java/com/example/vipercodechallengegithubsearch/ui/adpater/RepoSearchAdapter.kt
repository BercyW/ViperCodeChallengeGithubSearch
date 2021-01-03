package com.example.vipercodechallengegithubsearch.ui.adpater

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.vipercodechallengegithubsearch.R
import com.example.vipercodechallengegithubsearch.model.Repo

class RepoSearchAdapter(private val interaction: Interaction? = null) : PagingDataAdapter<Repo, RecyclerView.ViewHolder>(REPO_COMPARATOR) {


    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val repoItem = getItem(position)
        if (repoItem != null) {
            (holder as RepoSearchViewHolder).bind(repoItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return RepoSearchViewHolder(
            LayoutInflater.from(parent.context).inflate(
            R.layout.repo_item,
            parent,
            false
            ),
            interaction
        )
    }


    class RepoSearchViewHolder(view: View,private val interaction: Interaction?) : RecyclerView.ViewHolder(view) {
        private val name: TextView = view.findViewById(R.id.repo_name)
        private val description: TextView = view.findViewById(R.id.repo_description)
        private var repo: Repo? = null

        fun bind(repo: Repo?) = with(itemView){

            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, repo)
            }
            if (repo != null) {
                displayResult(repo)
            }
        }

        private fun displayResult(repo: Repo) {
            this.repo = repo
            name.text = repo.fullName

            if (repo.description != null) {
                description.text = repo.description
            }else {
                description.visibility = View.GONE
            }

        }

    }
    interface Interaction {
        fun onItemSelected(position: Int, item: Repo?)
    }
}