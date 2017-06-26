package com.rodrigo.tigerspiketest.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.*

data class ImageItem(
        val title: String? = null,
        val link: String? = null,
        val media: ImageMedia? = null,
        @SerializedName("date_taken")
        val dateTaken: Date? = null,
        val published: Date? = null,
        val author: String? = null,
        val tags: String? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString()?.let { ImageMedia(medium = it) },
            parcel.readLong().let { if (it != 0L) Date(it) else null },
            parcel.readLong().let { if (it != 0L) Date(it) else null },
            parcel.readString(),
            parcel.readString()
    )

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.apply {
            writeString(title)
            writeString(link)
            writeString(media?.medium)
            writeLong(dateTaken?.time ?: 0L)
            writeLong(published?.time ?: 0L)
            writeString(author)
            writeString(tags)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    val CREATOR: Parcelable.Creator<ImageItem> = object : Parcelable.Creator<ImageItem> {
        override fun createFromParcel(source: Parcel): ImageItem {
            return ImageItem(source)
        }

        override fun newArray(size: Int): Array<ImageItem?> {
            return arrayOfNulls(size)
        }
    }

    data class ImageMedia(
            @SerializedName("m")
            val medium: String? = null
    )
}