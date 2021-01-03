package com.example.vipercodechallengegithubsearch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.core.os.bundleOf
import com.example.vipercodechallengegithubsearch.R
import com.example.vipercodechallengegithubsearch.model.Repo

class MainActivity : AppCompatActivity(),RepoSearchResultFragment.DataHandleListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showResultFragment()
    }

    private fun showResultFragment() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                RepoSearchResultFragment(),"RepoSearchResultFragment")
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    /**
     * passing data to detail
     */
    override fun passRepo(repo: Repo?) {
        val repoSearchDetailsFragment = RepoSearchDetailsFragment()
        val bundle = bundleOf("repo" to repo)
        repoSearchDetailsFragment.arguments = bundle
        val transaction = this.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container,repoSearchDetailsFragment).addToBackStack("RepoSearchResultFragment")
        transaction.commit()
    }
}