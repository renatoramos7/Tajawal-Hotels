package com.renatoramos.tajawal.presentation.ui.hotel.detail

import com.renatoramos.tajawal.data.model.HotelModel
import com.renatoramos.tajawal.data.store.HotelsRepository
import com.renatoramos.tajawal.presentation.base.BasePresenter
import javax.inject.Inject

class DetailsPresenter @Inject constructor(view: DetailsContract.View, private val hotelsRepository: HotelsRepository)
    : BasePresenter<DetailsContract.View>(view), DetailsContract.Presenter {

    private var hotelId: Int? = 0

    override fun onStart() {
        view.receiveIntent()
        view.setToolbar()
        view.addOnClickToolbar()
        view.loadHotelById()
    }

    override fun attachHotelId(hotelId: Int?) {
        this.hotelId = hotelId
    }

    override fun getHotelById() {
        addDisposable(hotelsRepository.getHotelById(hotelId)
                .subscribe(
                        { list -> onSuccess(list!!) },
                        { throwable -> onError(throwable) }
                ))
    }

    override fun onSuccess(hotelModel: HotelModel?) {
        view.showToolbarTitle(hotelModel!!.summary?.hotelName)
        view.showImage(hotelModel.image?.get(0)?.url)
        view.showHotelLocation(hotelModel.location!!.latitude!!, hotelModel.location!!.longitude!!)
        view.showGoogleMap()
        view.showHotelName(hotelModel.summary?.hotelName)
        view.showDiscountPrice(hotelModel.summary?.lowRate.toString(), hotelModel.summary?.highRate.toString())
        view.showAddress(hotelModel.location?.address)
        view.addOnClickHotelImage(hotelModel.image?.get(0)?.url)
    }

    override fun onError(throwable: Throwable) {
        view.showError(throwable.message.orEmpty())
    }
}