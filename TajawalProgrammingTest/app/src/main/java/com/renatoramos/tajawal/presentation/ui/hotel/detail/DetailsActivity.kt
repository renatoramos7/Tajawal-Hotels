package com.renatoramos.tajawal.presentation.ui.hotel.detail

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.MAP_TYPE_NORMAL
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.renatoramos.tajawal.R
import com.renatoramos.tajawal.common.constants.AppConstants
import com.renatoramos.tajawal.common.extensions.loadWithGlide
import com.renatoramos.tajawal.common.extensions.makeTextToast
import com.renatoramos.tajawal.presentation.base.BaseActivity
import com.renatoramos.tajawal.presentation.ui.hotel.imageviewer.ImageViewerActivity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.toolbar_base_with_back.*
import javax.inject.Inject

class DetailsActivity : BaseActivity(), DetailsContract.View, OnMapReadyCallback {

    @Inject
    lateinit var presenter: DetailsPresenter

    private lateinit var latLng: LatLng

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initialize()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        MapsInitializer.initialize(this)

        if (googleMap != null && this::latLng.isInitialized) {
            with(googleMap) {
                moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13f))
                addMarker(MarkerOptions().position(latLng))
                mapType = MAP_TYPE_NORMAL
            }
        }
    }

    override fun showHotelLocation(lat: Double, lng: Double) {
        latLng = LatLng(lat, lng)
    }

    override fun showGoogleMap() {
        with(mapView) {
            onCreate(null)
            getMapAsync(this@DetailsActivity)
        }
    }

    override fun showImage(url: String?) {
        url?.let { hotelImageView.loadWithGlide(it) }
    }

    override fun showToolbarTitle(title: String?) {
        toolbarBaseWithBack.title = title
    }

    override fun showHotelName(hotelName: String?) {
        hotelNameTextView.text = hotelName
    }

    override fun showError(error: String) {
        baseContext.makeTextToast(error, Toast.LENGTH_LONG).show()
    }

    override fun showDiscountPrice(lowRate: String?, highRate: String?) {
        val spannedFlag = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        var boldLowRate = SpannableString(lowRate)
        var strikeHighRate = SpannableString(highRate)
        strikeHighRate.setSpan(ForegroundColorSpan(Color.RED), 0, strikeHighRate.length, spannedFlag)
        strikeHighRate.setSpan(RelativeSizeSpan(1.0f), 0, strikeHighRate.length, spannedFlag)
        strikeHighRate.setSpan(StrikethroughSpan(), 0, strikeHighRate.length, spannedFlag)
        boldLowRate.setSpan(StyleSpan(Typeface.BOLD), 0, boldLowRate.length, spannedFlag)

        var spannableStringBuilder = SpannableStringBuilder()
        spannableStringBuilder.append(getString(R.string.HOTEL_PRICE_SAVE))
        spannableStringBuilder.append(" €")
        spannableStringBuilder.append(boldLowRate)
        spannableStringBuilder.append("\n")
        spannableStringBuilder.append("\n")
        spannableStringBuilder.append(getString(R.string.HOTEL_PRICE_SUGGESTED))
        spannableStringBuilder.append(" €")
        spannableStringBuilder.append(strikeHighRate)
        priceTextView.text = spannableStringBuilder
    }

    override fun showAddress(address: String?) {
        addressTextView.text = address
    }

    override fun addOnClickHotelImage(imageUrl: String?) {
        hotelImageView.setOnClickListener({
            val intent = Intent(this, ImageViewerActivity::class.java)
            intent.putExtra(AppConstants.IMAGE_URL, imageUrl)
            startActivity(intent)
        })
    }

    override fun loadHotelById() {
        presenter.getHotelById()
    }

    override fun receiveIntent() {
        presenter.attachHotelId(intent.getIntExtra(AppConstants.HOTEL_ID, -1))
    }

    override fun setToolbar() {
        setSupportActionBar(toolbarBaseWithBack)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    override fun addOnClickToolbar() {
        toolbarBaseWithBack.setNavigationOnClickListener({ finish() })
    }

    private fun initialize() {
        presenter.onStart()
    }

}
