package `in`.tutorial.pagingfragmentv1.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PageFilter:ViewModel() {
    val filters = MutableLiveData<Map<String, String>>()

    fun addFilter(filter:Map<String,String>){
        filters.value = filter
    }
}