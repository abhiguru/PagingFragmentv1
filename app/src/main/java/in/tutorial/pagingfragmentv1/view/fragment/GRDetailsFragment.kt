package `in`.tutorial.pagingfragmentv1.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import `in`.tutorial.pagingfragmentv1.R

/**
 * A simple [Fragment] subclass.
 * Use the [GRDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GRDetailsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_g_r_details, container, false)
    }
}