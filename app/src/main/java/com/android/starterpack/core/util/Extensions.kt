package com.android.starterpack.core.util

import android.content.Context
import android.widget.Toast

/**
 * Extension function of Activity to show Toast
 */
fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

/**
 * Utility function to check given email is valid or not
 */
fun isValidEmail(email: String): Boolean {
    // Regular expression for validating an email address
    val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    return emailRegex.matches(email)
}