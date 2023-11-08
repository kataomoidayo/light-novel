package com.putu.lightnovel.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.putu.lightnovel.database.LightNovelEntity
import com.putu.lightnovel.repository.LightNovelRepository
import com.putu.lightnovel.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailLightNovelViewModel @Inject constructor(private val repository: LightNovelRepository): ViewModel() {

    private val _uiState = MutableStateFlow<UiState<LightNovelEntity>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()


    fun getLightNovelById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getLightNovelById(id)
                .catch { _uiState.value = UiState.Error(it.message.toString()) }
                .collect { _uiState.value = UiState.Success(it) }
        }
    }

    fun updateFavoriteLightNovel(id: Int, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateFavoriteLightNovel(id, isFavorite)
        }
    }
}