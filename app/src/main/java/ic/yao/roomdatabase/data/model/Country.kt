package ic.yao.roomdatabase.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Country (
    val name: String,
    val code : Int): Parcelable