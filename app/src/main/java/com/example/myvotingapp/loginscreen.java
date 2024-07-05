package com.example.myvotingapp;

import android.app.Activity;

public class loginscreen extends Activity {
}


@Composable
fun LoginScreen(navController: NavHostController, authViewModel: AuthViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
var id by remember { mutableStateOf("") }
var password by remember { mutableStateOf("") }
val loginState by authViewModel.loginState.collectAsState()

Column(
        modifier = Modifier
        .fillMaxSize()
            .padding(16.dp)
    ) {
TextField(value = id, onValueChange = { id = it }, label = { Text("South African ID") })
TextField(value = password, onValueChange = { password = it }, label = { Text("Password") }, visualTransformation = PasswordVisualTransformation())
Spacer(modifier = Modifier.height(16.dp))
Button(onClick = { authViewModel.login(id, password) }) {
Text("Log In")
        }
Spacer(modifier = Modifier.height(8.dp))
Button(onClick = { navController.navigate("signup") }) {
Text("Sign Up")
        }
                }

                if (loginState.success) {
        navController.navigate("dashboard")
    }
            }

