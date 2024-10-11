package com.stathis.episodes.ui.details

import androidx.lifecycle.ViewModel
import com.stathis.common.di.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
internal class EpisodeDetailsViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    fun fetchEpisodeDetails(episodeId: Int) {
        //FIXME: add logic to fetch episode details
    }
}
