package `in`.tutorial.pagingfragmentv1.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlin.reflect.KClass

class ViewModelProviderFactory<T:ViewModel> (
    private val kClass: KClass<T>,
    private val creator: () -> T ): ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(kClass.java)){
            return creator() as T
        }
        throw  java.lang.IllegalArgumentException("Unknown viewmodel class")
    }
}