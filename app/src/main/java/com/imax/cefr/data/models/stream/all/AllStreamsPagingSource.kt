package com.imax.cefr.data.models.stream.all

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.imax.cefr.core.base.result.ResultModel
import com.imax.cefr.core.base.result.Status
import com.imax.cefr.data.models.stream.StreamResponseData
import java.lang.Exception

private const val BASE_STARTING_PAGE_INDEX = 1
private const val DEFAULT_TOTAL_TOP = 20
class AllStreamsPagingSource(
    private val pagingResponse: suspend (page: Int) -> ResultModel<AllStreamsResponse>
) : PagingSource<Int, StreamResponseData>(){
    override fun getRefreshKey(state: PagingState<Int, StreamResponseData>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, StreamResponseData> {
        return try {
            val page = params.key ?: BASE_STARTING_PAGE_INDEX
            val response =pagingResponse(page)
            if (response.status == Status.SUCCESS && response.data != null) {
                val ads = response.data.streams
                val totalPage = DEFAULT_TOTAL_TOP
                LoadResult.Page(
                    data = ads,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = page + 1
                )
            } else {
                LoadResult.Error(throwable = java.lang.Exception("No Response"))
            }
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }

}
