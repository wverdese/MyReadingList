package me.wverdese.example.myreadinglist.util

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.StringRes
import com.squareup.picasso.Picasso


fun ImageView.load(url: String) {
    Picasso.get()
        .load(url)
        .into(this)
}

fun Context.showToast(string: String) = Toast.makeText(this, string, Toast.LENGTH_LONG).show()
