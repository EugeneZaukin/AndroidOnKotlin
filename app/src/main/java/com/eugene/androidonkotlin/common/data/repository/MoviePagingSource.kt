package com.eugene.androidonkotlin.common.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.eugene.androidonkotlin.common.extensions.toMovie
import com.eugene.androidonkotlin.listMovie.screen.model.Movie

class MoviePagingSource(
    private val movieAPi: MovieAPi
) : PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val pageIndex = params.key ?: 1

        return try {
            val movies = movieAPi.getMovies(pageIndex).results.map { it.toMovie() }
            return LoadResult.Page(
                data = movies,
                prevKey = if (pageIndex == 0) null else pageIndex - 1,
                nextKey = if (movies.isNotEmpty()) pageIndex + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }
}