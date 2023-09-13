package com.interneted.demo.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import com.interneted.demo.R

/**
 * Creator: ED
 * Date: 2023/9/13 10:45
 * Mail: salahayo3192@gmail.com
 *
 * **/
abstract class BaseFragment<VB : ViewBinding> : Fragment() {
    private var _viewBinding: VB? = null
    val viewBinding get() = _viewBinding!!

    val navController: NavController
        get() {
            return Navigation.findNavController(requireActivity(),R.id.nav_host_fragment)
        }

    abstract fun createViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = createViewBinding(inflater, container)
        return viewBinding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

}