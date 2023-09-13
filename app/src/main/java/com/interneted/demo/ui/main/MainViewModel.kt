package com.interneted.demo.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interneted.demo.model.TravelRepository
import com.interneted.demo.model.data.TravelPlace
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Creator: ED
 * Date: 2023/9/13 10:35
 * Mail: salahayo3192@gmail.com
 *
 * **/
class MainViewModel(
    private val repository: TravelRepository
) : ViewModel() {
    private var currentPage = 0
    private val _travelPlaceLiveData = MutableLiveData<List<TravelPlace>>()
    val travelPlaceLiveData: LiveData<List<TravelPlace>> = _travelPlaceLiveData


    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            val d =  repository.getTravelPlace(currentPage++)
            Log.d("asdniadn:","$d")

            _travelPlaceLiveData.value = d
        }
    }

}