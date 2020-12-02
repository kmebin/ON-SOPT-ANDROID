package org.kmebin.Seminar27th.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProfileData(
    val title : String,
    val subTitle : String,
    val contents : String
) : Parcelable