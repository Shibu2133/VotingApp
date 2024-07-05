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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        setContent {
            MainScreen(auth, db)
        }
    }
}

@Composable
fun MainScreen(auth: FirebaseAuth, db: FirebaseFirestore) {
    var selectedLanguage by remember { mutableStateOf("English") }
    val languages = listOf("English", "Zulu", "Xhosa", "Afrikaans", "Sepedi", "Tswana", "Southern Sotho", "Tsonga", "Swazi", "Venda", "Southern Ndebele")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Select Language", style = MaterialTheme.typography.headlineMedium)
        languages.forEach { language ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                RadioButton(
                    selected = selectedLanguage == language,
                    onClick = { selectedLanguage = language }
                )
                Text(text = language, modifier = Modifier.padding(start = 8.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {


            // Navigate to AuthenticationActivity
        }) {
            Text(" Sign Up")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {

        }) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            // Navigate to VotingActivity
        }) {
            Text("Vote")
        }
    }
}
