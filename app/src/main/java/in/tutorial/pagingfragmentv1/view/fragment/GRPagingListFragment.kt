package `in`.tutorial.pagingfragmentv1.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import `in`.tutorial.pagingfragmentv1.MyApplication
import `in`.tutorial.pagingfragmentv1.R
import `in`.tutorial.pagingfragmentv1.data.remote.Endpoint
import `in`.tutorial.pagingfragmentv1.data.remote.repository.flow.GRFlowRepositoryImpl
import `in`.tutorial.pagingfragmentv1.data.remote.repository.paging.GRFlowPagingSource
import `in`.tutorial.pagingfragmentv1.databinding.FragmentGrPagingListBinding
import `in`.tutorial.pagingfragmentv1.utils.ViewModelProviderFactory
import `in`.tutorial.pagingfragmentv1.view.activity.MainActivity
import `in`.tutorial.pagingfragmentv1.view.adapter.GRPagingDataAdapter
import `in`.tutorial.pagingfragmentv1.viewModel.GRFlowPagerViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class GRPagingListFragment : Fragment() {
    private lateinit var viewModelFlow: GRFlowPagerViewModel
    private var bindings: FragmentGrPagingListBinding? = null
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
        bindings = FragmentGrPagingListBinding.inflate(layoutInflater)
        val args : GRPagingListFragmentArgs by navArgs()

        pagingSource = GRFlowPagingSource(
            Endpoint.AUTH_TOKEN,
            (requireActivity().application as MyApplication).networkService,
            args.dateFrom, args.dateTo, args.grNo
        )
        repositoryImpl = GRFlowRepositoryImpl(pagingSource!!)

        viewModelFlow = ViewModelProvider(this, ViewModelProviderFactory(GRFlowPagerViewModel::class){
            GRFlowPagerViewModel(repositoryImpl = repositoryImpl)
        }).get(GRFlowPagerViewModel::class.java)

        pagingDataAdapter = GRPagingDataAdapter(this@GRPagingListFragment)
        bindings?.rvPagingGr?.apply {
            layoutManager = linearLayoutManager
            adapter = pagingDataAdapter
        }
        pagingDataAdapter.addLoadStateListener {
                loadState->
            bindings?.pbDispatchFlowPagingSource?.isVisible =
                loadState.source.refresh is LoadState.Loading
            val errorState = loadState.source.refresh as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.source.append as? LoadState.Error
            errorState?.let {
                showErrorSnackBar(it.error.message.toString())
            }
        }
        flowObservers()
        (activity as MainActivity).supportActionBar?.let {
            it.title = resources.getString(R.string.title_gr_list)
        }
        return bindings!!.root
    }

    private fun flowObservers() {
        lifecycleScope.launch {
            viewModelFlow.getGRListPaging()
                .collectLatest {
                    pagingDataAdapter.submitData(lifecycle = lifecycle, it)
                }
        }
    }

    private fun setupActionBar(){

    }
    private fun showErrorSnackBar(msg:String){
        Snackbar.make(requireView(), msg, Snackbar.LENGTH_INDEFINITE).apply {
            setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.purple_700))
            setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            setActionTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            setAction(R.string.close){
                dismiss()
            }
            setAnchorView(bindings?.pbDispatchFlowPagingSource)
        }.show()
    }
}