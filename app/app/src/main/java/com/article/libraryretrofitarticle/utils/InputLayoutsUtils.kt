package com.article.libraryretrofitarticle.utils

import android.widget.EditText
import com.article.libraryretrofitarticle.R
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.isBlank(editText: EditText): Boolean {
    return if (editText.text.toString().isNotBlank()) {
        this.isErrorEnabled = false
        true
    } else {
        this.error = this.context.getString(R.string.empty_field_error)
        false
    }
}

fun TextInputLayout.isTeapot(editText: EditText): Boolean {
    val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,250}\$"

    return if (editText.text.toString().length < 8) {
        this.error = this.context.getString(R.string.short_password_error)
        false
    } else {
        if (editText.text.toString().matches(Regex(passwordPattern))) {
            this.isErrorEnabled = false
            true
        } else {
            this.error = this.context.getString(R.string.teapot_password_error)
            false
        }
    }
}

fun TextInputLayout.isEmail(editText: EditText): Boolean {
    val emailPattern =
        "^(?=.{1,64}@)[A-Za-z0-9+_-]+(\\.[A-Za-z0-9+_-]+)*@" + "[^-][A-Za-z0-9+-]+(\\.[A-Za-z0-9+-]+)*(\\.[A-Za-z]{2,})$"

    return if (editText.text.toString().matches(Regex(emailPattern))) {
        this.isErrorEnabled = false
        true
    } else {
        this.error = this.context.getString(R.string.not_email_error)
        false
    }
}