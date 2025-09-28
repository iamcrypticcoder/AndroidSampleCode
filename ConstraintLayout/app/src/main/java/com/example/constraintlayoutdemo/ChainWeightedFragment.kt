package com.example.constraintlayoutdemo

import android.os.Bundle
import android.transition.TransitionManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.constraintlayout.widget.ConstraintSet
import kotlinx.android.synthetic.main.fragment_chain_spread_inside.*
import kotlinx.android.synthetic.main.fragment_chain_weighted.*
import kotlinx.android.synthetic.main.fragment_chain_weighted.contentView
import kotlinx.android.synthetic.main.fragment_chain_weighted.imageView1
import kotlinx.android.synthetic.main.fragment_chain_weighted.imageView2

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChainDemoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChainWeightedFragment : Fragment() {
    private val TAG = ChainWeightedFragment::class.java.simpleName

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chain_weighted, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated()");

        weightSeekBar.progress = 50;
        weightSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.d(TAG, "onViewCreated() : onProgressChanged() : progress = " + progress);
                if (false == fromUser) return;
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                Log.d(TAG, "onViewCreated() : onStartTrackingTouch()");
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                Log.d(TAG, "onViewCreated() : onStopTrackingTouch()");
                val set = ConstraintSet();
                set.clone(contentView);
                set.setHorizontalWeight(imageView1.id, seekBar?.progress!!.toFloat());
                set.setHorizontalWeight(imageView2.id, (100 - seekBar?.progress!!).toFloat());
                TransitionManager.beginDelayedTransition(contentView)
                set.applyTo(contentView)
            }

        })
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ChainDemoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChainWeightedFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
