package com.regxl.news.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.regxl.news.NewsApplication
import com.regxl.news.R
import com.regxl.news.adapters.ArticleListAdapter
import com.regxl.news.databinding.FragmentEntertainmentBinding
import com.regxl.news.viewModels.NewsViewModel
import com.regxl.news.viewModels.NewsViewModelFactory
import com.regxl.news.network.Article
import com.regxl.news.repository.NewsRepository

class EntertainmentFragment : Fragment(),ArticleListAdapter.OnItemClickListener{

    private lateinit var binding: FragmentEntertainmentBinding
    private val viewModel : NewsViewModel by activityViewModels(){
        val newsRepository = NewsRepository((activity?.application as NewsApplication).dataBase)
        NewsViewModelFactory(newsRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_entertainment,container,false)
        binding.lifecycleOwner = this
        binding.newsViewModel = viewModel
        binding.recyclerView.adapter = ArticleListAdapter(this)
        return binding.root
    }

    override fun onItemClick(article: Article) {
        val bundle = Bundle().apply { putSerializable("article",article) }
        findNavController().navigate(R.id.action_mainFragment_to_articleFragment, bundle)
    }
}