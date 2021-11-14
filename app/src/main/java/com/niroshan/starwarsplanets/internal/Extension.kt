package com.niroshan.starwarsplanets.internal

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setAvatarImage(view: View, url: String) {
    Glide.with(view)
        .load(url)
        .centerCrop()
        .error(android.R.drawable.stat_notify_error)
        .into(this)
}