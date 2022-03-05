package xyz.teamgravity.dictionary.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collectLatest
import xyz.teamgravity.dictionary.presentation.component.WordCard
import xyz.teamgravity.dictionary.presentation.viewmodel.SearchViewModel

@Composable
fun SearchScreen() {
    val viewmodel = hiltViewModel<SearchViewModel>()
    val scaffold = rememberScaffoldState()
    val state = viewmodel.state.value

    LaunchedEffect(key1 = true) {
        viewmodel.event.collectLatest { event ->
            when (event) {
                is SearchViewModel.SearchEvent.ShowSnackbar -> {
                    scaffold.snackbarHostState.showSnackbar(message = event.message)
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffold,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                TextField(
                    value = viewmodel.query.value,
                    onValueChange = viewmodel::onSearch,
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text(text = "Search...") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    itemsIndexed(state.wordInfos) { index, wordInfo ->
                        Spacer(modifier = Modifier.height(8.dp))
                        WordCard(wordInfo = wordInfo)
                        if (index < state.wordInfos.size - 1) Divider()
                    }
                }
            }
            if (state.loading) CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}