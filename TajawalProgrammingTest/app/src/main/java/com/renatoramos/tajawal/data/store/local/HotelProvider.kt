package com.renatoramos.tajawal.data.store.local

import com.renatoramos.tajawal.data.model.HotelModel
import io.paperdb.Paper
import io.reactivex.Observable


class HotelProvider {

    private val TAG = HotelProvider::class.java!!.simpleName

    fun add(hotels: List<HotelModel>): Observable<List<HotelModel>> {
        return Observable.create<List<HotelModel>> { e ->
            try {

                Paper.book().delete(TAG)
                Paper.book().write(TAG, hotels)
                e.onNext(hotels)
                e.onComplete()
            } catch (exception: Exception) {
                e.onError(exception)
            }
        }
    }

    fun getAll(): Observable<List<HotelModel>> {
        val hotelList = Paper.book().read<List<HotelModel>>(TAG)

        return if (hotelList != null) {
            Observable.just<List<HotelModel>>(hotelList)
        } else {
            val hotelModelList: List<HotelModel> = listOf()
            Observable.just(hotelModelList)
        }
    }

    fun getById(id: Int?): Observable<HotelModel> {
        val hotelList = Paper.book().read<List<HotelModel>>(TAG)

        return if (hotelList != null) {
             Observable.fromIterable(hotelList).filter { hotel -> hotel.hotelId == id }.map{ customer -> customer }
         } else {
            val hotelModel: HotelModel? = null
            Observable.just<HotelModel>(hotelModel)
        }
    }

    fun delete(): Observable<Void> {
        return Observable.create { e ->
            try {
                Paper.book().delete(TAG)
                e.onComplete()
            } catch (exception: Exception) {
                e.onError(exception)
            }
        }
    }



}