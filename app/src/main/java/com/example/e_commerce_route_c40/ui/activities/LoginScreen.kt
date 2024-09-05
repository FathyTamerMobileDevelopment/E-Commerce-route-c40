package com.example.e_commerce_route_c40.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.e_commerce_route_c40.base.BaseActivity
import com.example.e_commerce_route_c40.databinding.LoginPageBinding

class LoginScreen : BaseActivity<LoginPageBinding>() {

    override fun inflateBinding(): LoginPageBinding {
        return LoginPageBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.createAcc.setOnClickListener {
            createAccount()
        }

        binding.buttonLogin.setOnClickListener {
            logIntoAccount()
        }
    }

    private fun logIntoAccount() {
        if (isValidate())
            Toast.makeText(this, "login successfully", Toast.LENGTH_SHORT).show()
    }

    private fun createAccount() {
        val intent = Intent(this, CreateAccount::class.java)
        startActivity(intent)
    }

    private fun isValidate(): Boolean {
        var isValid = true

        // Regex patterns
        val usernameRegex = "^[a-zA-Z0-9._]{3,15}$".toRegex()
        val passwordRegex =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$".toRegex()

        // Validate Username
        val username = binding.inputUserName.editText?.text.toString()
        if (username.isBlank()) {
            isValid = false
            binding.inputUserName.error = "Please enter your name"
            binding.inputUserName.editText?.requestFocus()
        } else if (!username.matches(usernameRegex)) {
            isValid = false
            binding.inputUserName.error =
                "Username must be 3-15 characters long and can contain letters, digits, underscores, and periods."
            binding.inputUserName.editText?.requestFocus()
        } else {
            binding.inputUserName.error = null
        }

        // Validate Password
        val password = binding.inputUserPass.editText?.text.toString()
        if (password.isBlank()) {
            isValid = false
            binding.inputUserPass.error = "Please enter your password"
            binding.inputUserPass.editText?.requestFocus()
        } else if (!password.matches(passwordRegex)) {
            isValid = false
            binding.inputUserPass.error =
                "Password must be at least 8 characters long and include uppercase, lowercase, digit, and special character."
            binding.inputUserPass.editText?.requestFocus()
        } else {
            binding.inputUserPass.error = null
        }

        return isValid
    }

}