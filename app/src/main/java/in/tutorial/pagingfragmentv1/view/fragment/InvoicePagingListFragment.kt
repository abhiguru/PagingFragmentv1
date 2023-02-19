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
import `in`.tutorial.pagingfragmentv1.data.remote.repository.flow.InvoiceFlowRepositoryImpl
import `in`.tutorial.pagingfragmentv1.data.remote.repository.paging.InvoiceFlowPagingSource
import `in`.tutorial.pagingfragmentv1.databinding.FragmentInvoicePagingListBinding
import `in`.tutorial.pagingfragmentv1.utils.ViewModelProviderFactory
import `in`.tutorial.pagingfragmentv1.view.activity.MainActivity
import `in`.tutorial.pagingfragmentv1.view.adapter.InvoicePagingDataAdapter
import `in`.tutorial.pagingfragmentv1.viewModel.InvoiceFlowPagerViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class InvoicePagingListFragment : Fragment() {
    private lateinit var pagingSource: InvoiceFlowPagingSource
    private lateinit var binding: FragmentInvoicePagingListBinding
    private lateinit var viewModel: InvoiceFlowPagerViewModel
    private lateinit var repository: InvoiceFlowRepositoryImpl
    private lateinit var pagingDataAdapter: InvoicePagingDataAdapter
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
        binding = FragmentInvoicePagingListBinding.inflate(inflater)
        val args : InvoicePagingListFragmentArgs by navArgs()
        pagingSource = InvoiceFlowPagingSource(Endpoint.AUTH_TOKEN,
            networkService = (requireActivity().application as MyApplication).networkService,
            args.dateFrom,args.dateTo,args.grNo)
        repository = InvoiceFlowRepositoryImpl(pagingSource = pagingSource)
        viewModel = ViewModelProvider(this, ViewModelProviderFactory(InvoiceFlowPagerViewModel::class){
            InvoiceFlowPagerViewModel(repository)
        }).get(InvoiceFlowPagerViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pagingDataAdapter = InvoicePagingDataAdapter()

        binding?.rvPagingInvoice?.apply {
            layoutManager = linearLayoutManager
            adapter = pagingDataAdapter
        }
        pagingDataAdapter.addLoadStateListener {
                loadState->
            binding?.pbInvoiceFlowPagingSource?.isVisible =
                loadState.source.refresh is LoadState.Loading
            val errorState = loadState.source.refresh as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.source.append as? LoadState.Error
            errorState?.let {
                showErrorSnackBar(it.error.message.toString())
            }
        }
        (activity as MainActivity).supportActionBar?.let {
            it.title = resources.getString(R.string.title_invoice_list)
        }
        observers()
    }
    private fun observers(){
        lifecycleScope.launch{
            viewModel.getInvoiceListPaging().collectLatest {
                pagingDataAdapter.submitData(lifecycle = lifecycle, it)
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
            setAnchorView(binding?.pbInvoiceFlowPagingSource)
        }.show()
    }
}