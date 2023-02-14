package `in`.tutorial.pagingfragmentv1

import android.app.Application
import `in`.tutorial.pagingfragmentv1.data.remote.Network
import `in`.tutorial.pagingfragmentv1.data.remote.NetworkService

class MyApplication:Application() {
    lateinit var networkService: NetworkService
    private val baseUrl:String = "https://www.gurucold.in:2828/"
    override fun onCreate() {
        super.onCreate()
        networkService = Network.create(baseUrl, cacheDir, 1024*1024*10)
    }
}