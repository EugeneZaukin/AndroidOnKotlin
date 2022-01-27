package com.eugene.androidonkotlin.model

import com.eugene.androidonkotlin.R
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie (
        val title: String = "Властелин колец: Братство кольца",
        val image: Int = R.drawable.lordofthering,
        val rating: String = "Кинопоиск 8.6",
        val description: String = "Волшебник Гэндальф-Серый, приехавший на день рождения к своему давнишнему приятелю Бильбо, становится свидетелем странного события — в разгар празднества, под многочисленными взглядами друзей и соседей, Бильбо... исчезает в воздухе прямо посреди толпы. Под грозным взглядом Гэндальфа он признается, что проделал этот трюк с помощью кольца, найденного им во время странствий. Кольцо необычно — в нем нет драгоценного камня, и Гэндальф понимает, что это — утерянное тысячу лет назад Кольцо Всевластья, выкованное Темным Властелином в огне вулкана и дающее владельцу невероятную силу и власть."
) : Parcelable

        fun getListMovies() = listOf(
                Movie(),
                Movie("Властелин колец: Две крепости"),
                Movie("Властелин колец: Возвращение короля"),
                Movie("Матрица"),
                Movie("Матрица: Перезагрузка"),
                Movie("Матрица: Революция")
                )