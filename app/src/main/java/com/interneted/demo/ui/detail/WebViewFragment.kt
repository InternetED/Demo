package com.interneted.demo.ui.detail

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import com.interneted.demo.R
import com.interneted.demo.core.BaseFragment
import com.interneted.demo.databinding.FragmentWebviewBinding

/**
 * Creator: ED
 * Date: 2023/9/13 11:49
 * Mail: salahayo3192@gmail.com
 *
 * **/
class WebViewFragment : BaseFragment<FragmentWebviewBinding>() {

    companion object {
        const val BUNDLE_LINK = "bundle_link"
    }


    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentWebviewBinding {
        return FragmentWebviewBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        requireActivity().onBackPressedDispatcher.addCallback(object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (viewBinding.webView.canGoBack()) {
                    viewBinding.webView.goBack()
                } else {
                    isEnabled = false
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }
            }
        })


        val link = arguments?.run {
            getString(BUNDLE_LINK)
        }

        if (checkLinkFormat(link)) {
            with(viewBinding.webView) {
                settings.javaScriptEnabled = true
            }

            viewBinding.webView.loadUrl(link!!)
        }
    }


    override fun onPause() {
        super.onPause()
        viewBinding.webView.onPause()
    }

    override fun onResume() {
        super.onResume()
        viewBinding.webView.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        view?.findViewById<WebView>(R.id.webView)?.destroy()
    }

    private fun showAlertDialog(message: String) {
        AlertDialog.Builder(requireContext())
            .setMessage(message)
            .setPositiveButton(getString(R.string.confirm)) { _, _ ->
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
            .setOnCancelListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
            .show()
    }

    private fun checkLinkFormat(link: String?): Boolean {
        if (link.isNullOrEmpty()) {
            showAlertDialog(getString(R.string.alert_empty))
            return false
        } else {
            val formatResult = runCatching {
                Uri.parse(link)
            }
            if (formatResult.isFailure) {
                showAlertDialog(getString(R.string.alert_format_warn))
                return false
            }
        }
        return true
    }


    data class DetailArgument(
        val link: String
    )
}