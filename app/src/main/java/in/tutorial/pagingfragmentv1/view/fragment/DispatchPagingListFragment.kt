package `in`.tutorial.pagingfragmentv1.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import `in`.tutorial.pagingfragmentv1.MyApplication
import `in`.tutorial.pagingfragmentv1.R
import `in`.tutorial.pagingfragmentv1.data.remote.Endpoint
import `in`.tutorial.pagingfragmentv1.data.remote.repository.flow.DispatchFlowRepositoryImpl
import `in`.tutorial.pagingfragmentv1.data.remote.repository.paging.DispatchFlowPagingSource
import `in`.tutorial.pagingfragmentv1.databinding.FragmentDispatchPagingListBinding
import `in`.tutorial.pagingfragmentv1.utils.ViewModelProviderFactory
import `in`.tutorial.pagingfragmentv1.view.adapter.DispatchPagingDataAdapter
import `in`.tutorial.pagingfragmentv1.viewModel.DispatchFlowPagerViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class DispatchPagingListFragment : Fragment() {
    var binding: FragmentDispatchPagingListBinding? = null
    private lateinit var viewModel: DispatchFlowPagerViewModel
    private lateinit var repository: DispatchFlowRepositoryImpl
    private lateinit var pagingSource:DispatchFlowPagingSource
    private lateinit var pagingDataAdapter: DispatchPagingDataAdapter
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
        binding = FragmentDispatchPagingListBinding.inflate(inflater)
        val args: DispatchPagingListFragmentArgs by navArgs()

        pagingSource = DispatchFlowPagingSource(Endpoint.AUTH_TOKEN,
            (requireActivity().application as MyApplication).networkService,
            args.dateFrom, args.dateTo, args.grNo
        )
        repository = DispatchFlowRepositoryImpl(pagingSource)

        viewModel = ViewModelProvider(this, ViewModelProviderFactory(DispatchFlowPagerViewModel::class){
            DispatchFlowPagerViewModel(repository)
        }).get(DispatchFlowPagerViewModel::class.java)

        pagingDataAdapter = DispatchPagingDataAdapter()
        binding?.rvPagingDispatch?.apply {
            adapter = pagingDataAdapter
            layoutManager = linearLayoutManager
        }

        pagingDataAdapter.addLoadStateListener {
            loadState->
            binding?.pbDispatchFlowPagingSource?.isVisible =
                loadState.source.refresh is LoadState.Loading
            val errorState = loadState.source.refresh as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.source.append as? LoadState.Error
            errorState?.let {
                showErrorSnackBar(it.error.message.toString())
            }
        }
        observers()
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    private fun observers(){
        lifecycleScope.launch {
            viewModel.getDispatchListPaging()
                .collectLatest {
                    pagingDataAdapter.submitData(lifecycle, it)
                }
        }
    }

    private fun showErrorSnackBar(msg:String){
        Snackbar.make(requireView(), msg, Snackbar.LENGTH_INDEFINITE).apply {
            setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.purple_700))
            setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            setActionTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            setAction(R.string.close){
                dismiss()
            }
            setAnchorView(binding?.pbDispatchFlowPagingSource)
        }.show()
    }
}