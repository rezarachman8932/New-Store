package com.app.store.presentation.landing.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.store.R
import kotlinx.android.synthetic.main.frag_home.*

class HomeFragment : Fragment() {

    private val layoutId: Int = R.layout.frag_home

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_quote_of_the_day.setOnClickListener {  }
        button_quote_list.setOnClickListener {  }
        button_log_out.setOnClickListener {  }
    }

}