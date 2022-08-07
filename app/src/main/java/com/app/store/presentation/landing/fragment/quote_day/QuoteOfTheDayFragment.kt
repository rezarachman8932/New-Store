package com.app.store.presentation.landing.fragment.quote_day

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.store.R
import com.app.store.data.model.QuoteOfTheDayResponse
import com.app.store.shared.model.BaseResponse
import com.app.store.shared.model.ResultState
import kotlinx.android.synthetic.main.frag_quote_of_the_day.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuoteOfTheDayFragment : Fragment() {

    private val layoutId: Int = R.layout.frag_quote_of_the_day
    private val viewModel: QuoteOfTheDayViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.quoteOfTheDay.observe(viewLifecycleOwner) { constructList(it) }
        viewModel.getQuoteOfTheDay()
    }

    private fun constructList(result: ResultState<BaseResponse<QuoteOfTheDayResponse>>) {
        when (result) {
            is ResultState.Success -> { result.data?.let { bindData(it.data) } }
            is ResultState.Error -> {  }
        }
    }

    private fun bindData(response: QuoteOfTheDayResponse) {
        text_author_of_the_day.text = response.quote.author
        text_body_of_the_day.text = response.quote.body

        var tags = ""
        response.quote.tags.forEach {
            tags = "$it $tags"
        }

        text_tags_of_the_day.text = tags
    }

}