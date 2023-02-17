package `in`.tutorial.pagingfragmentv1.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import `in`.tutorial.pagingfragmentv1.R
import `in`.tutorial.pagingfragmentv1.databinding.FragmentDispatchPagingListBinding

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class DispatchPagingFragment : Fragment() {
    var binding: FragmentDispatchPagingListBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDispatchPagingListBinding.inflate(inflater)
        return binding!!.root
    }

    override fun onStart() {
        super.onStart()
        val args: DispatchPagingFragmentArgs by navArgs()
        binding?.tvGrNo?.text = args.grNo
    }

}