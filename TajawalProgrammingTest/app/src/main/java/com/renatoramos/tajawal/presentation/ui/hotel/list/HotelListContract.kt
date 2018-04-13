package com.renatoramos.tajawal.presentation.ui.hotel.list

import com.renatoramos.tajawal.data.model.HotelModel
import com.renatoramos.tajawal.presentation.base.BaseView


interface HotelListContract {

    interface View : BaseView{

        fun createAdapter(hotelModelList: List<HotelModel>)

        fun showAdapter()

        fun isInternetConnected(): Boolean

        fun showError(error: String)

        fun showErrorInternetConnection()

        fun showProgressBar()

        fun hideProgressBar()

        fun openDetails(hotelId: Int)

        fun setupRecyclerView()

        fun loadHotelList()
    }

    interface Presenter {

        fun getHotelList()

        fun onSuccess(hotelModelList: List<HotelModel>)

        fun onError(throwable: Throwable)

        fun onItemClick(position: Int)
    }
}