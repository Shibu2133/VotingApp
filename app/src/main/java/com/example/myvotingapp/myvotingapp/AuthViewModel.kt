package com.example.myvotingapp.myvotingapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState

    private val _signUpState = MutableStateFlow(SignUpState())
    val signUpState: StateFlow<SignUpState> = _signUpState

    fun login(id: String, password: String) {
        viewModelScope.launch {
            auth.signInWithEmailAndPassword(id, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _loginState.value = LoginState(success = true)
                    } else {
                        _loginState.value = LoginState(success = false)
                    }
                }
        }
    }

    fun signUp(name: String, phone: String, id: String, password: String) {
        viewModelScope.launch {
            auth.createUserWithEmailAndPassword(id, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        val userData = hashMapOf(
                            "name" to name,
                            "phone" to phone,
                            "id" to id,
                            "password" to password
                        )
                        user?.let {
                            firestore.collection("users").document(it.uid).set(userData)
                        }
                        _signUpState.value = SignUpState(success = true)
                    } else {
                        _signUpState.value = SignUpState(success = false)
                    }
                }
        }
    }

    data class LoginState(val success: Boolean = false)
    data class SignUpState(val success: Boolean = false)
}

