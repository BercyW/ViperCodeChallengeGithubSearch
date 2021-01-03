package com.example.vipercodechallengegithubsearch.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.vipercodechallengegithubsearch.R
import com.example.vipercodechallengegithubsearch.databinding.FragmentDetailsBinding
import com.example.vipercodechallengegithubsearch.model.Repo


class RepoSearchDetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    var repo : Repo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.window?.setSoftInputMode(
            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         val name: TextView = view.findViewById(R.id.repo_name)
         val description: TextView = view.findViewById(R.id.repo_description)
         val stars: TextView = view.findViewById(R.id.repo_stars)
         val forks: TextView = view.findViewById(R.id.repo_forks)

        /**
         * get data and set
         */
        arguments?.get("repo")?.let {item->
            repo = item as Repo
           name.text = item.name
           description.text = item.description
           stars.text = item.stars.toString()
           forks.text = item.forks.toString()
       }

        binding.button.setOnClickListener {
            repo?.url?.let { url ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                view.context.startActivity(intent)
            }
        }

    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

}