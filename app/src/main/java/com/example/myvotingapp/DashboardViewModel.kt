package com.example.myvotingapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    private val _topParties = MutableStateFlow<List<String>>(emptyList())
    val topParties: StateFlow<List<String>> = _topParties

    init {
        fetchTopParties()
    }

    private fun fetchTopParties() {
        viewModelScope.launch {
            db.collection("votes")
                .get()
                .addOnSuccessListener { result ->
                    val partyVotes = result.documents.groupBy { it["party"] as String }
                        .mapValues { it.value.size }
                        .entries
                        .sortedByDescending { it.value }
                        .take(3)
                        .map { it.key }
                    _topParties.value = partyVotes
                }
        }
    }
}
