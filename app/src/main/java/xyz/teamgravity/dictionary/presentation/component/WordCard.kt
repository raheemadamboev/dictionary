package xyz.teamgravity.dictionary.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import xyz.teamgravity.dictionary.domain.model.WordInfoModel

@Composable
fun WordCard(
    wordInfo: WordInfoModel
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = wordInfo.word,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        if (wordInfo.phonetic.isNotBlank()) Text(
            text = wordInfo.phonetic,
            fontWeight = FontWeight.Light
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (wordInfo.origin.isNotBlank()) Text(text = wordInfo.origin)

        wordInfo.meanings.forEach { meaning ->
            Text(
                text = meaning.partOfSpeech,
                fontWeight = FontWeight.Bold
            )
            meaning.definitions.forEachIndexed { index, definition ->
                Text(text = "${index + 1}. ${definition.definition}")
                Spacer(modifier = Modifier.height(8.dp))
                definition.example?.let { Text(text = "Example: $it") }
                Spacer(modifier = Modifier.height(8.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}