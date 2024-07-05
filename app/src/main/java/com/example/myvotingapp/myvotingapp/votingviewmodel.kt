package com.example.myvotingapp.myvotingapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class VotingViewModel : ViewModel() {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun vote(nationalParty: String, provincialParty: String) {
        viewModelScope.launch {
            val voteData = hashMapOf(
                "nationalParty" to nationalParty,
                "provincialParty" to provincialParty
            )
            firestore.collection("votes").add(voteData)
        }
    }
}

