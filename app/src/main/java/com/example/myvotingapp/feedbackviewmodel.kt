package com.example.myvotingapp.myvotingapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class FeedbackViewModel : ViewModel() {
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun submitFeedback(feedback: String) {
        viewModelScope.launch {
            val feedbackData = hashMapOf("feedback" to feedback)
            firestore.collection("feedback").add(feedbackData)
        }
    }
}
