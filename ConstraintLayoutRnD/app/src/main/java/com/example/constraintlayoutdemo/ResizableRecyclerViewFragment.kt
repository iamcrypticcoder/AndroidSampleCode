package com.example.constraintlayoutdemo

import android.content.res.Configuration
import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.constraintlayoutdemo.adapters.ResizableRecyclerViewAdapter
import com.example.constraintlayoutdemo.models.DemoData
import com.example.constraintlayoutdemo.models.Person
import com.example.constraintlayoutdemo.util.CustomGridItemDecoration
import kotlinx.android.synthetic.main.fragment_resizable_recycler_view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ResizableRecyclerViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResizableRecyclerViewFragment : Fragment() {
    private val TAG = ResizableRecyclerViewFragment::class.java.simpleName

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val mHandler = Handler(Looper.getMainLooper())

    private var personList : List<Person> = emptyList()
    private var adapter : ResizableRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resizable_recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated()");

        initRecyclerView()
        loadRecyclerView();
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig);
        Log.d(TAG, "onConfigurationChanged()");

    }

    private fun initRecyclerView() {
        Log.d(TAG, "initRecyclerView()");
        val itemSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 94f, resources.displayMetrics).toInt()
        val spacingInPx = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, resources.displayMetrics).toInt()

        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        recyclerView.addItemDecoration(CustomGridItemDecoration(requireContext(), 3, false))
    }

    private fun loadRecyclerView() {
        Log.d(TAG, "loadRecyclerView()");
        personList = DemoData.getPersonList()
        adapter = ResizableRecyclerViewAdapter(requireContext(), personList)
        recyclerView.adapter = adapter
        adapter?.notifyDataSetChanged()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ResizableRecyclerViewFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ResizableRecyclerViewFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}