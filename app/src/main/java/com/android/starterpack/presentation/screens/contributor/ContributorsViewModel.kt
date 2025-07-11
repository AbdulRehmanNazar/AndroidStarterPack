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
import com.android.starterpack.core.domain.Result
import com.android.starterpack.domain.respository.ContributorRepository

/**
 * View model for contributor to define business logic for the list of contributors
 */
class ContributorsViewModel(
    private val getRemoteContributorsUseCase: GetRemoteContributorsUseCase,
    private val contributorRepository: ContributorRepository
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
        viewModelScope.launch {
            contributorRepository.getRemoteContributors()
        }
    }

    /**
     * Load local contributors
     */
    fun getContributors() {
        _state.value = UiState.Loading

        viewModelScope.launch {
            getRemoteContributorsUseCase()
                .collect { result ->
                    _state.value = when (result) {
                        is Result.Success -> UiState.Success(result.data)
                        is Result.Error -> UiState.Error(result.error)
                    }
                }
        }
    }
}