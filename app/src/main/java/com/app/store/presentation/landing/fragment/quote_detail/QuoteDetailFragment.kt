package com.app.store.presentation.landing.fragment.quote_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.store.R
import com.app.store.data.model.QuoteOfTheDayDetail
import kotlinx.android.synthetic.main.frag_quote_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuoteDetailFragment : Fragment() {

    private val layoutId: Int = R.layout.frag_quote_detail
    private val viewModel: QuoteDetailViewModel by viewModel()

    private lateinit var adapter: QuoteDetailTagAdapter
    private lateinit var param: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            param = QuoteDetailFragmentArgs.fromBundle(requireArguments()).quoteId
        }

        adapter = QuoteDetailTagAdapter {  }
        rv_tag_quote_detail.adapter = adapter

        viewModel.quoteDetail.observe(viewLifecycleOwner) { bindData(it) }
        viewModel.getQuoteDetail(param)
    }

    private fun bindData(response: QuoteOfTheDayDetail) {
        text_author_detail.text = response.author
        text_quote_detail.text = response.body

        adapter.setItem(response.tags)
    }

}