package com.interneted.demo.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.interneted.demo.R
import com.interneted.demo.core.BaseFragment
import com.interneted.demo.databinding.FragmentMainBinding
import com.interneted.demo.di.ViewModelFactory
import com.interneted.demo.ui.detail.DetailFragment.Companion.BUNDLE_DESCRIPTION
import com.interneted.demo.ui.detail.DetailFragment.Companion.BUNDLE_IMAGE_URL
import com.interneted.demo.ui.detail.DetailFragment.Companion.BUNDLE_LINK
import com.interneted.demo.ui.detail.DetailFragment.Companion.BUNDLE_TITLE

/**
 * Creator: ED
 * Date: 2023/9/13 10:29
 * Mail: salahayo3192@gmail.com
 *
 * **/
class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel by lazy {
        ViewModelProvider(this, ViewModelFactory(requireContext()))[MainViewModel::class.java]
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.button.setOnClickListener {
            navController.navigate(
                R.id.action_main_to_detailFragment,
                bundleOf(
                    BUNDLE_IMAGE_URL to "imageUrl",
                    BUNDLE_TITLE to "title",
                    BUNDLE_DESCRIPTION to "description",
                    BUNDLE_LINK to "link"
                )
            )
        }

        viewModel.travelPlaceLiveData.observe(viewLifecycleOwner) {
            Log.d("alsomdoadm","$it")
        }

        viewModel.loadData()

    }
}