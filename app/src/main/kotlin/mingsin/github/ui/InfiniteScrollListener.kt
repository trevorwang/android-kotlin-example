package mingsin.github.ui

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager

/**
 * Created by trevorwang on 21/12/2016.
 */
abstract class InfiniteScrollListener(pageSize: Int) : RecyclerView.OnScrollListener() {
    private var visibleThreshold = pageSize
    private var nextPage = 0
    private var previousTotalItemCount = 0
    private var loading = false

    init {

    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val lm = recyclerView?.layoutManager
        var lastVisibleItemPosition = 0
        val totalItemCount = lm?.itemCount ?: 0
        when (lm) {
            is LinearLayoutManager -> {
                lastVisibleItemPosition = lm.findLastVisibleItemPosition()
            }

            is GridLayoutManager -> {
                lastVisibleItemPosition = lm.findLastVisibleItemPosition()
            }

            is StaggeredGridLayoutManager -> {
                val positions = lm.findLastVisibleItemPositions(IntArray(0))
                lastVisibleItemPosition = getLastVisibleItem(positions)
            }
        }

        if (loading && (totalItemCount > previousTotalItemCount)) {
            loading = false
            previousTotalItemCount = totalItemCount
        }

        if (!loading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
            nextPage += 1
            loadMore(nextPage)
            loading = true
        }
    }

    abstract fun loadMore(page: Int)

    fun resetState() {
        previousTotalItemCount = 0
        loading = true
    }

    private fun getLastVisibleItem(lastVisibleItemPos: IntArray): Int {
        var maxSize = 0
        for (i in 0..lastVisibleItemPos.size) {
            val last = lastVisibleItemPos[i]
            if (i == 0 || last > maxSize) {
                maxSize = last
            }
        }
        return maxSize
    }
}