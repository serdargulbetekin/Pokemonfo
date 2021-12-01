package com.example.soostonetask.extensions

import android.content.Context
import android.widget.Toast

fun Context.showToast(message: Int, toastLength: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, getString(message), toastLength).show()
}