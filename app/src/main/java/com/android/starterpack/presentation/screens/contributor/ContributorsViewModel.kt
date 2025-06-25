package com.android.starterpack.presentation.screens.contributor

import androidx.lifecycle.ViewModel
import com.android.starterpack.core.domain.onError
import com.android.starterpack.core.domain.onSuccess
import com.android.starterpack.core.presentation.state.UiState
import com.android.starterpack.domain.model.Contributor
import com.android.starterpack.domain.usecase.GetRemoteContributorsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * View model for contributor to define business logic for the list of contributors
 */
class ContributorsViewModel(
    private val getRemoteContributorsUseCase: GetRemoteContributorsUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow<UiState<List<Contributor>>>(UiState.Loading)
    val state: StateFlow<UiState<List<Contributor>>> = _state

    private val viewModelScope = CoroutineScope(Dispatchers.IO)

    init {
        loadRemoteContributors()
    }

    /**
     * Load remote contributors
     */
    private fun loadRemoteContributors() {
//        viewModelScope.launch {
//            contributorRepository.getRemoteContributors()
//        }
    }

    /**
     * Load local contributors
     */
    fun getContributors() {
        _state.value = UiState.Loading

        viewModelScope.launch {
            getRemoteContributorsUseCase()
                .onSuccess { result ->
                    _state.value = UiState.Success(result)
                }
                .onError { error ->
                    _state.value = UiState.Error(error)
                }
        }
    }
}