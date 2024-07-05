@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myvotingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

class VotingActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VotingScreen()
        }
    }
}

@Composable
fun VotingScreen(votingViewModel: VotingViewModel = viewModel()) {
    val parties = listOf("Party A", "Party B", "Party C", "Party D")
    var selectedParty by remember { mutableStateOf<String?>(null) }
    var feedback by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Vote for a Party", style = MaterialTheme.typography.headlineMedium)
        parties.forEach { party ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                RadioButton(
                    selected = selectedParty == party,
                    onClick = { selectedParty = party }
                )
                Text(text = party, modifier = Modifier.padding(start = 8.dp))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            selectedParty?.let { party ->
                votingViewModel.voteForParty(party) {
                    // Display thank you message
                }
            }
        }) {
            Text("Vote")
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = feedback,
            onValueChange = { feedback = it },
            label = { Text("Feedback") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
