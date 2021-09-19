package com.example.constraintlayoutdemo

import android.os.Bundle
import android.transition.TransitionManager
import android.util.Log
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.children
import androidx.core.view.updateLayoutParams
import kotlinx.android.synthetic.main.fragment_chain_spread.*
import kotlinx.android.synthetic.main.fragment_chain_spread_inside.*
import kotlinx.android.synthetic.main.fragment_chain_spread_inside.addImageButton
import kotlinx.android.synthetic.main.fragment_chain_spread_inside.contentView
import kotlinx.android.synthetic.main.fragment_chain_spread_inside.removeImageButton
import kotlinx.android.synthetic.main.fragment_chain_spread_inside.switchImageRatio

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChainSpreadInsideFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChainSpreadInsideFragment : Fragment() {
    private val TAG = ChainSpreadFragment::class.java.simpleName

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
        return inflater.inflate(R.layout.fragment_chain_spread_inside, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated()");

        addImageButton.setOnClickListener {
            // First and Last view reference
            var firstView = contentView.children.first() as ImageView;
            var lastView = contentView.children.last() as ImageView;

            // Create new image
            var imageView = ImageView(context)
            imageView.id = View.generateViewId();
            imageView.setImageDrawable(
                ResourcesCompat.getDrawable(
                resources,
                Util.getRandomSampleImageRes(requireActivity()),
                context?.getTheme()));
            imageView.layoutParams = ConstraintLayout.LayoutParams(lastView.layoutParams).apply {
                dimensionRatio =  (lastView.layoutParams as ConstraintLayout.LayoutParams).dimensionRatio
            }
            imageView.alpha = 0.0f;
            contentView.addView(imageView);
            imageView.animate().alpha(1.0f).setStartDelay(500)

            // Update constrains programmatically
            var set = ConstraintSet()
            set.clone(contentView);
            set.connect(lastView.id, ConstraintSet.END, imageView.id, ConstraintSet.START);
            set.connect(imageView.id, ConstraintSet.START, lastView.id, ConstraintSet.END);
            set.connect(imageView.id, ConstraintSet.END, contentView.id, ConstraintSet.END);
            set.connect(imageView.id, ConstraintSet.TOP, contentView.id, ConstraintSet.TOP);
            set.connect(imageView.id, ConstraintSet.BOTTOM, contentView.id, ConstraintSet.BOTTOM);
            TransitionManager.beginDelayedTransition(contentView)
            set.applyTo(contentView)
        };

        removeImageButton.setOnClickListener {
            if (contentView.childCount < 2) {
                Toast.makeText(context, "Can't remove last image", Toast.LENGTH_LONG).show();
                return@setOnClickListener;
            }

            // Remove last view
            var lastView = contentView.children.last()
            lastView.animate().alpha(0.0f).withEndAction {
                contentView.removeView(lastView)
                lastView = contentView.children.last()

                // Update constrains programmatically
                var set = ConstraintSet()
                set.clone(contentView);
                set.connect(lastView.id, ConstraintSet.END, contentView.id, ConstraintSet.END)
                TransitionManager.beginDelayedTransition(contentView)
                set.applyTo(contentView)
            }
        }

        switchImageRatio.setOnCheckedChangeListener { buttonView, isChecked ->
            Log.d(TAG, "onViewCreated() : isChecked = " + isChecked);
            val size = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100f, resources.displayMetrics).toInt()
            for (v in contentView.children) {
                if (isChecked) {
                    v.updateLayoutParams<ConstraintLayout.LayoutParams> {
                        width = 0
                        height = 0
                        dimensionRatio = "1:0.625"
                    }
                } else {
                    v.updateLayoutParams<ConstraintLayout.LayoutParams> {
                        width = size
                        height = size
                        dimensionRatio = null
                    }
                }
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ChainSpreadInsideFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChainSpreadInsideFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}