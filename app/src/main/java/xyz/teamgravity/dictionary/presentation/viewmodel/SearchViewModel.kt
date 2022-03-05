package xyz.teamgravity.dictionary.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import xyz.teamgravity.dictionary.core.util.Resource
import xyz.teamgravity.dictionary.domain.model.WordInfoModel
import xyz.teamgravity.dictionary.domain.usecase.GetWordInfos
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getWordInfos: GetWordInfos
) : ViewModel() {

    private val _query = mutableStateOf("")
    val query: State<String> = _query

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    private val _event = Channel<SearchEvent> { }
    val event: Flow<SearchEvent> = _event.receiveAsFlow()

    private var job: Job? = null

    fun onSearch(query: String) {
        _query.value = query
        job?.cancel()
        job = viewModelScope.launch {
            delay(500L)
            getWordInfos(query)
                .onEach { result ->
                    when (result) {
                        is Resource.Loading -> {
                            _state.value = _state.value.copy(
                                wordInfos = result.data ?: emptyList(),
                                loading = true
                            )
                        }

                        is Resource.Error -> {
                            _state.value = _state.value.copy(
                                wordInfos = result.data ?: emptyList(),
                                loading = false
                            )
                            _event.send(SearchEvent.ShowSnackbar(result.message ?: "Unknown error occurred!"))
                        }

                        is Resource.Success -> {
                            _state.value = _state.value.copy(
                                wordInfos = result.data ?: emptyList(),
                                loading = false
                            )
                        }
                    }
                }.launchIn(this)
        }
    }

    data class SearchState(
        val wordInfos: List<WordInfoModel> = emptyList(),
        val loading: Boolean = false
    )

    sealed class SearchEvent {
        data class ShowSnackbar(val message: String) : SearchEvent()
    }
}