package com.renatoramos.tajawal.hotel.list

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.renatoramos.tajawal.data.model.HotelModel
import com.renatoramos.tajawal.data.model.ImageModel
import com.renatoramos.tajawal.data.model.LocationModel
import com.renatoramos.tajawal.data.model.SummaryModel
import com.renatoramos.tajawal.data.store.HotelsRepository
import com.renatoramos.tajawal.presentation.ui.hotel.list.HotelListContract
import com.renatoramos.tajawal.presentation.ui.hotel.list.HotelListPresenter
import io.reactivex.Maybe
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit


class HotelListPresenterTest {

    @get:Rule
    var rule = MockitoJUnit.rule()

    @InjectMocks
    private lateinit var presenter: HotelListPresenter

    @Mock
    private lateinit var view: HotelListContract.View
    @Mock
    private lateinit var hotelsRepository: HotelsRepository

    private lateinit var hotelModelMock: HotelModel
    private lateinit var hotelModelListMock: List<HotelModel>
    private lateinit var throwableMock: Throwable

    @Before
    fun setUp() {
        hotelModelMock = HotelModel(hotelId=4021140, image= listOf(ImageModel("https://az712897.vo.msecnd.net/images/full/82524502-8E66-46B1-8B04-82E414894020.jpeg")),
                location= LocationModel(address="The Corner 24Th And 9Th Street Near Al Qiyadah Metro Station", latitude=25.271263, longitude=55.328996),
                summary= SummaryModel(highRate=3130.41, hotelName="Al Manar Hotel Apartments", lowRate=2379.11))

        throwableMock = mock()
        hotelModelListMock = listOf<HotelModel>().plus(hotelModelMock)
    }

    @Test
    fun `OnStart`() {
        // Act
        presenter.onStart()

        verify(view, times(1)).setupRecyclerView()
        verify(view, times(1)).setToolbar()
        verify(view, times(1)).loadHotelList()
    }

    @Test
    fun `should get list when internet is online`() {
        // Arrange
        `when`(view.isInternetConnected()).thenReturn(true)
        `when`(hotelsRepository.getHotelList()).thenReturn(Maybe.just(hotelModelListMock))

        // Act
        presenter.getHotelList()

        // Assert
        verify(view, times(1)).showProgressBar()
        verify(hotelsRepository, times(1)).getHotelList()
    }

    @Test
    fun `should get cached list when internet is offline`() {
        // Arrange
        `when`(view.isInternetConnected()).thenReturn(false)
        `when`(hotelsRepository.getHotelList()).thenReturn(Maybe.just(hotelModelListMock))

        // Act
        presenter.getHotelList()

        // Assert
        verify(view, times(1)).showProgressBar()
        verify(hotelsRepository, times(1)).getHotelList()
    }

    @Test
    fun `onSuccess`() {
        // Act
        presenter.onSuccess(hotelModelListMock)

        // Assert
        verify(view, times(1)).createAdapter(hotelModelListMock)
        verify(view, times(1)).showAdapter()
        verify(view, times(1)).hideProgressBar()
    }


    @Test
    fun `should open Details Screen in onItemClick`() {
        // Act
        presenter.onSuccess(hotelModelListMock)
        presenter.onItemClick(0)

        // Assert
        verify(view, times(1)).openDetails(hotelModelMock.hotelId!!)
    }

    @Test
    fun `onError`() {
        // Act
        presenter.onError(throwableMock)

        // Assert
        verify(view, times(1)).showError(throwableMock.message.orEmpty())
        verify(view, times(1)).hideProgressBar()
    }

    @Test
    fun `OnStop`() {
        presenter.onStop()
    }

}