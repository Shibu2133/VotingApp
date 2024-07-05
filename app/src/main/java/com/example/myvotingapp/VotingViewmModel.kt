package com.example.myvotingapp

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

class VotingViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()

    fun voteForParty(party: String, onComplete: () -> Unit) {
        db.collection("votes").add(mapOf("party" to party)).addOnCompleteListener {
            onComplete()
        }
    }
}
