package com.interneted.demo.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.interneted.demo.R
import com.interneted.demo.core.BaseFragment
import com.interneted.demo.databinding.FragmentDetailBinding

/**
 * Creator: ED
 * Date: 2023/9/13 10:32
 * Mail: salahayo3192@gmail.com
 *
 * **/
class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    companion object {
        const val BUNDLE_IMAGE_URL = "bundle_image_url"
        const val BUNDLE_TITLE = "bundle_title"
        const val BUNDLE_DESCRIPTION = "bundle_description"
        const val BUNDLE_LINK = "bundle_link"

    }


    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val detailArgument = requireArguments().run {
            DetailArgument(
                imageUrl = getString(BUNDLE_IMAGE_URL) ?: "",
                title = getString(BUNDLE_TITLE) ?: "",
                description = getString(BUNDLE_DESCRIPTION) ?: "",
                link = getString(BUNDLE_LINK) ?: ""
            )
        }

        (requireActivity() as AppCompatActivity).setSupportActionBar(viewBinding.toolbar)

        with((requireActivity() as AppCompatActivity).supportActionBar!!) {
            // 設定標題、返回按鈕
            title = detailArgument.title
            setDisplayHomeAsUpEnabled(true)
        }

        viewBinding.tvLink.setOnClickListener {
            navController.navigate(
                R.id.action_detail_to_webViewFragment,
                bundleOf(
                    WebViewFragment.BUNDLE_LINK to "https://google.com"
                )
            )
        }

    }


    data class DetailArgument(
        val imageUrl: String,
        val title: String,
        val description: String,
        val link: String
    )
}