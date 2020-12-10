package org.kmebin.Seminar27th.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListData(
    val firstName: String,
    val email: String,
    val avatar: String
) : Parcelable