package com.putu.lightnovel.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.putu.lightnovel.database.LightNovelEntity
import com.putu.lightnovel.model.LightNovelData
import com.putu.lightnovel.repository.LightNovelRepository
import com.putu.lightnovel.ui.common.SearchState
import com.putu.lightnovel.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: LightNovelRepository): ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<LightNovelEntity>>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _searchState = mutableStateOf(SearchState())
    val searchState: State<SearchState> = _searchState


    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllLightNovel().collect { lightNovel ->
                when (lightNovel.isEmpty()) {
                    true -> repository.insertAllLightNovel(LightNovelData.lightNovel)
                    else -> _uiState.value = UiState.Success(lightNovel)
                }
            }
        }
    }

    private fun searchLightNovel(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.searchLightNovel(query)
                .catch { _uiState.value = UiState.Error(it.message.toString()) }
                .collect { _uiState.value = UiState.Success(it) }
        }
    }

    fun onQueryChange(query: String) {
        _searchState.value = _searchState.value.copy(query = query)
        searchLightNovel(query)
    }
}