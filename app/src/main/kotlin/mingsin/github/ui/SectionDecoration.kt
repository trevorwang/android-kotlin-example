package mingsin.github.ui

import android.graphics.*
import android.text.TextPaint
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SectionDecoration(val callback: SectionDecorationCallback) : RecyclerView.ItemDecoration() {
    private val textPaint = TextPaint()
    private val paint = Paint()
    private val sectionHeight = 80

    init {
        paint.color = Color.BLUE
        textPaint.run {
            typeface = Typeface.DEFAULT_BOLD
            isAntiAlias = true
            textSize = 80f
            textAlign = Paint.Align.LEFT
            color = Color.WHITE
        }

    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        if (isFirstInGroup(position)) {
            outRect.top = sectionHeight
        } else {
            outRect.top = 0
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        val left = parent.paddingLeft.toFloat()
        val right = parent.width.toFloat() - parent.paddingRight
        val itemCount = parent.childCount
        var preGroupId: Int
        var groupId = -1
        for (i in 0 until itemCount) {
            val view = parent.getChildAt(i)
            val position = parent.getChildLayoutPosition(view)
            preGroupId = groupId
            groupId = callback.groupId(position)
            if (groupId < 0 || preGroupId == groupId) continue

            var textY = sectionHeight.coerceAtLeast(view.top).toFloat()
            if (position + 1 < itemCount) {
                val nextGroupId = callback.groupId(position + 1)
                println("bottom : ${view.bottom}   textY : $textY")
                if (nextGroupId != groupId && view.bottom < textY) {
                    textY = view.bottom.toFloat()
                }
            }
            c.drawRect(left, textY - sectionHeight, right, textY, paint)
            c.drawText(callback.groupFirstLine(position), left, textY, textPaint)
        }
    }

    private fun isFirstInGroup(position: Int): Boolean {
        return if (position == 0) {
            true
        } else {
            val preId = callback.groupId(position - 1)
            val curId = callback.groupId(position)
            preId != curId
        }
    }
}