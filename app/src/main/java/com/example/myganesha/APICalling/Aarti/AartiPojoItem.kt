package com.example.myganesha.APICalling.Aarti

import android.os.Parcel
import android.os.Parcelable

data class AartiPojoItem(
    val audio: String,
    val description: String,
    val id: String,
    val title: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(audio)
        parcel.writeString(description)
        parcel.writeString(id)
        parcel.writeString(title)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AartiPojoItem> {
        override fun createFromParcel(parcel: Parcel): AartiPojoItem {
            return AartiPojoItem(parcel)
        }

        override fun newArray(size: Int): Array<AartiPojoItem?> {
            return arrayOfNulls(size)
        }
    }
}