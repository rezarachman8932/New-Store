package com.app.store.presentation.landing.fragment.list_quotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.store.R
import com.app.store.data.model.QuoteListResponse
import com.app.store.shared.model.BaseResponse
import com.app.store.shared.model.ResultState
import kotlinx.android.synthetic.main.frag_quote_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuoteListFragment : Fragment() {

    private val layoutId: Int = R.layout.frag_quote_list
    private val viewModel: QuoteListViewModel by viewModel()

    private lateinit var adapter: QuoteListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = QuoteListAdapter {

        }
        rv_quote_list.adapter = adapter

        viewModel.quoteList.observe(viewLifecycleOwner) { constructList(it) }
        viewModel.getQuoteList()
    }

    private fun constructList(result: ResultState<BaseResponse<QuoteListResponse>>) {
        when (result) {
            is ResultState.Success -> { result.data?.let {
                if (it.data.quotes.size > 0) {
                    adapter.setItem(result.data.data.quotes)
                }
            } }
            is ResultState.Error -> {  }
        }
    }

}