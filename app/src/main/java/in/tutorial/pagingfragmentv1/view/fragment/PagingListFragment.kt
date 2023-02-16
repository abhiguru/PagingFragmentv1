package `in`.tutorial.pagingfragmentv1.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import `in`.tutorial.pagingfragmentv1.MyApplication
import `in`.tutorial.pagingfragmentv1.R
import `in`.tutorial.pagingfragmentv1.data.remote.Endpoint
import `in`.tutorial.pagingfragmentv1.data.remote.repository.flow.GRFlowRepositoryImpl
import `in`.tutorial.pagingfragmentv1.data.remote.repository.paging.GRFlowPagingSource
import `in`.tutorial.pagingfragmentv1.databinding.FragmentPagingListBinding
import `in`.tutorial.pagingfragmentv1.utils.ViewModelProviderFactory
import `in`.tutorial.pagingfragmentv1.view.adapter.GRPagingDataAdapter
import `in`.tutorial.pagingfragmentv1.viewModel.FlowPagerViewModel
import `in`.tutorial.pagingfragmentv1.viewModel.PageFilter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class PagingListFragment : Fragment() {
    private lateinit var viewModelFlow: FlowPagerViewModel
    private var bindings: FragmentPagingListBinding? = null
    private lateinit var repositoryImpl: GRFlowRepositoryImpl
    private var pagingSource: GRFlowPagingSource? = null
    private lateinit var pagingDataAdapter: GRPagingDataAdapter
    private val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(requireContext())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindings = FragmentPagingListBinding.inflate(layoutInflater)
        return bindings!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args : PagingListFragmentArgs by navArgs()
        paging(args.grNo, args.dateFrom, args.dateTo)
    }

    fun paging(grNo:String, dateFrom:String, dateTo:String){
        pagingSource = GRFlowPagingSource(
            Endpoint.AUTH_TOKEN,
            (requireActivity().application as MyApplication).networkService,
            dateFrom, dateTo, grNo
        )
        repositoryImpl = GRFlowRepositoryImpl(pagingSource!!)

        viewModelFlow = ViewModelProvider(this, ViewModelProviderFactory(FlowPagerViewModel::class){
            FlowPagerViewModel(repositoryImpl = repositoryImpl)
        }).get(FlowPagerViewModel::class.java)

        pagingDataAdapter = GRPagingDataAdapter(this@PagingListFragment)
        bindings?.rvPagingGr?.apply {
            layoutManager = linearLayoutManager
            adapter = pagingDataAdapter
        }

        flowObservers()
    }

    private fun flowObservers(){
        lifecycleScope.launch {
            viewModelFlow.getGRListPaging()
                .collectLatest {
                    pagingDataAdapter.submitData(lifecycle = lifecycle, it)
                }
        }
    }
}