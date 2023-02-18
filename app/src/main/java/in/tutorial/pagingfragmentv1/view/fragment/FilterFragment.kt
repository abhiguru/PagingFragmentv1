package `in`.tutorial.pagingfragmentv1.view.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import `in`.tutorial.pagingfragmentv1.R
import `in`.tutorial.pagingfragmentv1.databinding.FragmentFilterBinding
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 * create an instance of this fragment.
 */
class FilterFragment : Fragment() {
    private var binding:FragmentFilterBinding? = null
    private var calFrom = Calendar.getInstance()
    private lateinit var dateSetListenerFrom: DatePickerDialog.OnDateSetListener
    private var calTo = Calendar.getInstance()
    private lateinit var dateSetListenerTo: DatePickerDialog.OnDateSetListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFilterBinding.inflate(inflater, container, false)
        initDates()
        return binding!!.root
    }
    private fun initDates(){
        dateSetListenerFrom = DatePickerDialog.OnDateSetListener{
                view, year, month, dayOfMonth ->
            calFrom.set(Calendar.YEAR, year)
            calFrom.set(Calendar.MONTH, month)
            calFrom.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateFromDateInView()
        }
        dateSetListenerTo = DatePickerDialog.OnDateSetListener{
                view, year, month, dayOfMonth ->
            calTo.set(Calendar.YEAR, year)
            calTo.set(Calendar.MONTH, month)
            calTo.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateToDateInView()
        }
        calFrom.set(Calendar.YEAR, 2020)
        calFrom.set(Calendar.MONTH, 1)
        calFrom.set(Calendar.DAY_OF_MONTH, 1)
        updateFromDateInView()
        updateToDateInView()
    }
    private fun updateFromDateInView(){
        val myFormat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        binding?.etDateFrom?.setText(sdf.format(calFrom.time).toString())
    }
    private fun updateToDateInView(){
        val myFormat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        binding?.etDateTo?.setText(sdf.format(calTo.time).toString())
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.etDateFrom?.setOnClickListener {
            DatePickerDialog(requireContext(),
                dateSetListenerFrom,
                calFrom.get(Calendar.YEAR),
                calFrom.get(Calendar.MONTH),
                calFrom.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        binding?.etDateTo?.setOnClickListener {
            DatePickerDialog(requireContext(),
                dateSetListenerTo,
                calTo.get(Calendar.YEAR),
                calTo.get(Calendar.MONTH),
                calTo.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        binding?.submit?.setOnClickListener {
            val text = binding?.etGrno?.text.toString()
            val dateFrom = binding?.etDateFrom?.text.toString()
            val dateTo = binding?.etDateTo?.text.toString()

            if(findNavController().currentDestination?.id == R.id.pagingListFragment) {
                findNavController().navigate(
                    GRPagingListFragmentDirections.actionPagingListFragmentToFilterFragment()
                )
                findNavController().navigate(
                    FilterFragmentDirections.actionFilterFragmentToPagingListFragment(
                        text, dateFrom, dateTo
                    )
                )
            }
            if(findNavController().currentDestination?.id == R.id.dispatchFiltersFragment){
                findNavController().navigate(
                    DispatchPagingListFragmentDirections.actionDispatchFiltersFragmentToFilterFragment()
                )
                findNavController().navigate(
                    FilterFragmentDirections.actionFilterFragmentToDispatchFiltersFragment(
                        text, dateFrom, dateTo
                    )
                )
            }
        }
    }
    override fun onStart() {
        super.onStart()
        val dateFrom = binding?.etDateFrom?.text.toString()
        val dateTo = binding?.etDateTo?.text.toString()
        if(findNavController().currentDestination?.id == R.id.pagingListFragment) {
            findNavController().navigate(
                GRPagingListFragmentDirections.actionPagingListFragmentToFilterFragment()
            )
        }
        findNavController().navigate(
            FilterFragmentDirections.actionFilterFragmentToPagingListFragment(
                "", dateFrom, dateTo
            )
        )
    }
}