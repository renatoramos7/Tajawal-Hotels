package com.renatoramos.tajawal.hotel.detail

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.renatoramos.tajawal.data.model.HotelModel
import com.renatoramos.tajawal.data.model.ImageModel
import com.renatoramos.tajawal.data.model.LocationModel
import com.renatoramos.tajawal.data.model.SummaryModel
import com.renatoramos.tajawal.data.store.HotelsRepository
import com.renatoramos.tajawal.presentation.ui.hotel.detail.DetailsContract
import com.renatoramos.tajawal.presentation.ui.hotel.detail.DetailsPresenter
import io.reactivex.Maybe
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit

class DetailsPresenterTest {

    @get:Rule
    var rule = MockitoJUnit.rule()

    @InjectMocks
    private lateinit var presenter: DetailsPresenter

    @Mock
    private lateinit var view: DetailsContract.View
    @Mock
    private lateinit var hotelsRepository: HotelsRepository

    private lateinit var hotelModelMock: HotelModel
    private lateinit var hotelModelListMock: List<HotelModel>
    private lateinit var throwableMock: Throwable

    @Before
    fun setUp() {
        hotelModelMock = HotelModel(hotelId=4021140, image= listOf(ImageModel("https://az712897.vo.msecnd.net/images/full/82524502-8E66-46B1-8B04-82E414894020.jpeg")),
                location= LocationModel(address="The Corner 24Th And 9Th Street Near Al Qiyadah Metro Station", latitude=25.271263, longitude=55.328996),
                summary=SummaryModel(highRate=3130.41, hotelName="Al Manar Hotel Apartments", lowRate=2379.11))

        throwableMock = mock()
        hotelModelListMock = listOf<HotelModel>().plus(hotelModelMock)
    }

    @Test
    fun `attachHotelId`() {

        // Act
        presenter.attachHotelId(hotelModelMock.hotelId)
    }

    @Test
    fun `OnStart`() {
        // Act
        presenter.onStart()

        verify(view, times(1)).receiveIntent()
        verify(view, times(1)).setToolbar()
        verify(view, times(1)).addOnClickToolbar()
        verify(view, times(1)).loadHotelById()
    }


    @Test
    fun `onSuccess`() {
        `when`(hotelsRepository.getHotelById(1)).thenReturn(Maybe.just(hotelModelMock))

        presenter.onSuccess(hotelModelMock)

        verify(view, times(1)).showToolbarTitle(hotelModelMock.summary?.hotelName)
        verify(view, times(1)).showImage(hotelModelMock.image?.get(0)?.url)
        verify(view, times(1)).showHotelLocation(hotelModelMock.location!!.latitude!!, hotelModelMock.location!!.longitude!!)
        verify(view, times(1)).showGoogleMap()
        verify(view, times(1)).showHotelName(hotelModelMock.summary?.hotelName)
        verify(view, times(1)).showDiscountPrice(hotelModelMock.summary?.lowRate.toString(), hotelModelMock.summary?.highRate.toString())
        verify(view, times(1)).showAddress(hotelModelMock.location?.address)
        verify(view, times(1)).addOnClickHotelImage(hotelModelMock.image?.get(0)?.url)
    }


    @Test
    fun `onError`() {
        // Act
        presenter.onError(throwableMock)

        // Assert
        verify(view, times(1)).showError(throwableMock.message.orEmpty())
    }


}