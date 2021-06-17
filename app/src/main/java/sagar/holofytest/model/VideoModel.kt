package sagar.holofytest.model

import android.os.Parcel
import android.os.Parcelable

class VideoModel() : Parcelable {

    var url: String? = null
    var title: String? = null
    var desc: String? = null

    constructor(url: String?, title: String?, desc: String?) : this() {
        this.url = url
        this.title = title
        this.desc = desc
    }

    constructor(parcel: Parcel) : this() {
        url = parcel.readString()
        title = parcel.readString()
        desc = parcel.readString()
    }

    companion object CREATOR : Parcelable.Creator<VideoModel> {
        override fun createFromParcel(parcel: Parcel): VideoModel {
            return VideoModel(parcel)
        }

        override fun newArray(size: Int): Array<VideoModel?> {
            return arrayOfNulls(size)
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(url)
        parcel.writeString(title)
        parcel.writeString(desc)
    }

    override fun describeContents(): Int {
        return 0
    }

}
