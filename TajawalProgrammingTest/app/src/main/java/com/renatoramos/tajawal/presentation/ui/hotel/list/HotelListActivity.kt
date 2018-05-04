package com.renatoramos.tajawal.presentation.ui.hotel.list

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.widget.Toast
import com.renatoramos.tajawal.R
import com.renatoramos.tajawal.R.id.mainRecyclerView
import com.renatoramos.tajawal.R.id.toolbarTitleTextView
import com.renatoramos.tajawal.common.constants.AppConstants
import com.renatoramos.tajawal.common.extensions.isInternetConnected
import com.renatoramos.tajawal.common.extensions.makeTextToast
import com.renatoramos.tajawal.data.model.HotelModel
import com.renatoramos.tajawal.presentation.base.BaseActivity
import com.renatoramos.tajawal.presentation.ui.hotel.detail.DetailsActivity
import com.renatoramos.tajawal.presentation.ui.hotel.list.adapters.HotelListAdapterListener
import com.renatoramos.tajawal.presentation.ui.hotel.list.adapters.HotelListRecyclerAdapter
import kotlinx.android.synthetic.main.activity_hotel_list.*
import kotlinx.android.synthetic.main.toolbar_base_with_title.*
import javax.inject.Inject


class HotelListActivity : BaseActivity(), HotelListContract.View, HotelListAdapterListener {

    @Inject
    lateinit var presenter: HotelListPresenter

    private lateinit var hotelListRecyclerAdapter: HotelListRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_list)
        initialize()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun isInternetConnected(): Boolean {
        return baseContext.isInternetConnected()
    }

    override fun showError(error: String) {
        baseContext.makeTextToast(error, Toast.LENGTH_LONG).show()
    }

    override fun showErrorInternetConnection() {
        baseContext.makeTextToast(getString(R.string.MSG_ERROR_INTERNET_CONNECTION), Toast.LENGTH_LONG).show()
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun showAdapter() {
        mainRecyclerView.adapter = hotelListRecyclerAdapter
    }

    override fun loadHotelList() {
        presenter.getHotelList()
    }
    
    override fun createAdapter(hotelModelList: List<HotelModel>) {
        hotelListRecyclerAdapter = HotelListRecyclerAdapter(
                baseContext,
                hotelModelList,
                this
        )
    }

    override fun onItemClick(position: Int) {
        presenter.onItemClick(position)
    }

    override fun openDetails(hotelId: Int) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(AppConstants.HOTEL_ID, hotelId)
        startActivity(intent)
    }

    override fun setupRecyclerView() {
        mainRecyclerView.layoutManager = LinearLayoutManager(this)
        mainRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    override fun setToolbar() {
        toolbarTitleTextView.text = getString(R.string.HOTEL_LIST_TITLE)
    }

    private fun initialize() {
        presenter.onStart()
    }

}
