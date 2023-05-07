package com.article.libraryretrofitarticle.main.model.user.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonalDataEntity(
    @SerializedName("id") var id: Long? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("firstName") var firstName: String? = null,
    @SerializedName("lastName") var lastName: String? = null,
    @SerializedName("middleName") var middleName: String? = null
) : Parcelable {
    companion object {
        val emptyPersonalDataEntity = PersonalDataEntity(
            id = null,
            email = null,
            firstName = null,
            lastName = null,
            middleName = null
        )
    }
}