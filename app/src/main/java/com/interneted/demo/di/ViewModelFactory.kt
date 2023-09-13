package com.interneted.demo.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.interneted.demo.ui.main.MainViewModel

/**
 * Creator: ED
 * Date: 2023/9/13 16:49
 * Mail: salahayo3192@gmail.com
 *
 * **/
class ViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(AppModule.getInstance(context).travelRepository) as T
        }

        return super.create(modelClass)
    }
}