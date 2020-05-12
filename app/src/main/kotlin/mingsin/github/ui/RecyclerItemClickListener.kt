package mingsin.github.ui

import android.content.Context
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerItemClickListener(val context: Context,
                                val recyclerView: RecyclerView,
                                private val onItemClickListener: OnItemClickListener? = null,
                                private val onItemLongClickListener: OnItemLongClickListener? = null
) : RecyclerView.OnItemTouchListener {

    interface OnItemClickListener {
        fun onItemClicked(view: View, position: Int)
    }

    interface OnItemLongClickListener {
        fun onItemLongClicked(view: View, position: Int)
    }

    private val gestureDetector = GestureDetector(context, object : SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            e?.let {
                val childView = recyclerView.findChildViewUnder(e.x, e.y)
                childView?.let {
                    onItemClickListener?.onItemClicked(childView, recyclerView.getChildLayoutPosition(childView))
                }
                return true
            }
            return false
        }

        override fun onLongPress(e: MotionEvent?) {
            e?.let {
                val childView = recyclerView.findChildViewUnder(e.x, e.y)
                childView?.let {
                    onItemLongClickListener?.onItemLongClicked(childView, recyclerView.getChildLayoutPosition(childView))
                }
            }
        }

    })


    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(e)
        return false
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
    }
}