package com.example.constraintlayoutdemo.util

import android.content.Context
import android.graphics.Rect
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.example.constraintlayoutdemo.ResizableRecyclerViewFragment
import com.example.constraintlayoutdemo.Util

class CustomGridItemDecoration(
        private val context: Context,
        //private val itemSize: Int,
        private val spanCount: Int,
        // private val spaceInPx: Int,
        private val includeEdge: Boolean
) : RecyclerView.ItemDecoration() {
    private val TAG = ResizableRecyclerViewFragment::class.java.simpleName

    val isRTL = Util.isRTLMode(context)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        Log.d(TAG, "getItemOffsets()");

        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount

        val displayMetrics = context.resources.displayMetrics
        var horizontalSideMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24f, displayMetrics).toInt()
        var verticalSideMargin = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24f, displayMetrics).toInt()
        var topSpace = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, displayMetrics).toInt()

        val newItemSize = Util.getGridItemWidthInPx(context, horizontalSideMargin, spanCount)
        var spacing: Int = if (spanCount > 1) {
            ((displayMetrics.widthPixels - (newItemSize * spanCount) - (verticalSideMargin * 2)) / (spanCount - 1))
        } else 0

//        view.updateLayoutParams {
//            width = itemSize
//        }

        if (includeEdge) {
            outRect.left =
                    spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
            outRect.right =
                    (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)

            if (position < spanCount) { // top edge
                outRect.top = spacing
            }
            outRect.bottom = spacing // item bottom
        } else {
            if(isRTL){
                outRect.right = column * spacing / spanCount // column * ((1f / spanCount) * spacing)
                outRect.left =
                        spacing - (column + 1) * spacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            } else {
                outRect.left = column * spacing / spanCount // column * ((1f / spanCount) * spacing)
                outRect.right =
                        spacing - (column + 1) * spacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            }
            if (position >= spanCount) {
                outRect.top = topSpace // item top
            }
        }
    }
}