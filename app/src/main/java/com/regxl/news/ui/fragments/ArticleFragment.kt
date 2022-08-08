package com.regxl.news.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.regxl.news.NewsApplication
import com.regxl.news.R
import com.regxl.news.databinding.FragmentArticleBinding
import com.regxl.news.viewModels.NewsViewModel
import com.regxl.news.viewModels.NewsViewModelFactory
import com.regxl.news.repository.NewsRepository

class ArticleFragment : Fragment() {
    private lateinit var binding:FragmentArticleBinding

    private val viewModel : NewsViewModel by activityViewModels(){
        val newsRepository = NewsRepository((activity?.application as NewsApplication).dataBase)
        NewsViewModelFactory(newsRepository)
    }
    private val args : ArticleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_article,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article = args.article
        article.url?.let { binding.webView.loadUrl(it) }

        binding.fab.setOnClickListener{
            viewModel.saveArticle(article)
            Snackbar.make(view, "Article saved successfully", Snackbar.LENGTH_SHORT).show()
        }
    }
}