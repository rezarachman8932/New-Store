package com.app.store.presentation.landing.fragment.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.store.R
import com.app.store.presentation.user.UserLoginActivity
import kotlinx.android.synthetic.main.frag_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val layoutId: Int = R.layout.frag_home
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_quote_of_the_day.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_quoteOfTheDayFragment) }
        button_quote_list.setOnClickListener { findNavController().navigate(R.id.action_homeFragment_to_quoteListFragment) }
        button_log_out.setOnClickListener { viewModel.deleteSession() }

        viewModel.deleteSession.observe(viewLifecycleOwner) {
            activity?.let {
                val intent = Intent(it, UserLoginActivity::class.java)
                startActivity(intent)
                it.finish()
            }
        }
    }

}