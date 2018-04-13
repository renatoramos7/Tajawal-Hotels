package com.renatoramos.tajawal.hotel.imageviewer

import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.renatoramos.tajawal.presentation.ui.hotel.imageviewer.ImageViewerContract
import com.renatoramos.tajawal.presentation.ui.hotel.imageviewer.ImageViewerPresenter
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

class ImageViewerPresenterTest {

    @get:Rule
    var rule = MockitoJUnit.rule()

    @InjectMocks
    private lateinit var presenter: ImageViewerPresenter

    @Mock
    private lateinit var view: ImageViewerContract.View


    @Test
    fun `OnStart`() {
        // Act
        presenter.onStart()

        verify(view, times(1)).addImageClick()
    }

    @Test
    fun `attachUrl`() {
        presenter.attachUrl("url")

        verify(view, times(1)).loadImage("url")
    }


}