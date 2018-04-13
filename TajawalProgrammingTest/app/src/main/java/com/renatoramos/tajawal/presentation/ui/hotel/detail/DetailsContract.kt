package com.renatoramos.tajawal.presentation.ui.hotel.detail

import com.renatoramos.tajawal.data.model.HotelModel
import com.renatoramos.tajawal.presentation.base.BaseView

interface DetailsContract {

    interface View : BaseView {

        fun showHotelLocation(lat: Double, lng: Double)

        fun showGoogleMap()

        fun showImage(url: String?)

        fun showToolbarTitle(title: String?)

        fun showHotelName(hotelName: String?)

        fun showDiscountPrice(lowRate: String?, highRate: String?)

        fun showAddress(address: String?)

        fun showError(error: String)

        fun addOnClickHotelImage(imageUrl: String?)

        fun receiveIntent()

        fun addOnClickToolbar()

        fun loadHotelById()

    }

    interface Presenter {

        fun attachHotelId(hotelId: Int?)

        fun getHotelById()

        fun onSuccess(hotelModel: HotelModel?)

        fun onError(throwable: Throwable)
    }
}