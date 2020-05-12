package mingsin.github.ui

import androidx.recyclerview.widget.RecyclerView

/**
 * Created by trevorwang on 21/12/2016.
 */
abstract class InfiniteScrollListener(pageSize: Int) : RecyclerView.OnScrollListener() {
    private var visibleThreshold = pageSize
    private var nextPage = 0
    private var previousTotalItemCount = 0
    private var loading = false

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val lm = recyclerView.layoutManager
        var lastVisibleItemPosition = 0
        val totalItemCount = lm?.itemCount ?: 0
        when (lm) {
            is androidx.recyclerview.widget.LinearLayoutManager -> {
                lastVisibleItemPosition = lm.findLastVisibleItemPosition()
            }

            is androidx.recyclerview.widget.GridLayoutManager -> {
                lastVisibleItemPosition = lm.findLastVisibleItemPosition()
            }

            is androidx.recyclerview.widget.StaggeredGridLayoutManager -> {
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