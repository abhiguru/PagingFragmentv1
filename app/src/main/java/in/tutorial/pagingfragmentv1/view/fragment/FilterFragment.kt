package `in`.tutorial.pagingfragmentv1.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import `in`.tutorial.pagingfragmentv1.R
import `in`.tutorial.pagingfragmentv1.databinding.FragmentFilterBinding
import `in`.tutorial.pagingfragmentv1.viewModel.PageFilter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class FilterFragment : Fragment() {
    private var binding:FragmentFilterBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.submit?.setOnClickListener {
            val text = binding?.etGrNo?.text.toString()
            if(text.isNotEmpty()){
                if(findNavController().currentDestination?.id == R.id.pagingListFragment) {
                    findNavController().navigate(
                        PagingListFragmentDirections.actionPagingListFragmentToFilterFragment()
                    )
                }
                findNavController().navigate(
                    FilterFragmentDirections.actionFilterFragmentToPagingListFragment(
                        text, "2021-04-01", "2023-03-31"
                    )
                )
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if(findNavController().currentDestination?.id == R.id.pagingListFragment) {
            findNavController().navigate(
                PagingListFragmentDirections.actionPagingListFragmentToFilterFragment()
            )
        }
        findNavController().navigate(
            FilterFragmentDirections.actionFilterFragmentToPagingListFragment(
                "", "2021-04-01", "2023-03-31"
            )
        )
    }
}