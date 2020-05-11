package mingsin.github.ui

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SimpleDividerDecoration : RecyclerView.ItemDecoration() {
    private val paint = Paint()

    private val dividerHeight = 5

    init {
        paint.color = Color.RED
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = dividerHeight
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        val childCount = parent.childCount
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        for (i in 0 until childCount) {
            val view = parent.getChildAt(i)
            val top = view.bottom
            val bottom = view.bottom + dividerHeight
            c.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), paint)
        }
    }
}
