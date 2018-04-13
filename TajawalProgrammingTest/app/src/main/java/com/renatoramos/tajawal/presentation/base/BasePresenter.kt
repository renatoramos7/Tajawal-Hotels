package com.renatoramos.tajawal.presentation.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BasePresenter<out V> protected constructor(protected val view: V) {

    private var mCompositeDisposable: CompositeDisposable? = null


    /**
     * Contains common setup actions needed for all presenters, if any.
     * Subclasses may override this.
     */
    abstract fun onStart()

    /**
     * Contains common cleanup actions needed for all presenters, if any.
     * Subclasses may override this.
     */
    fun onStop() {
        getCompositeDisposable().clear()
    }


    protected fun addDisposable(disposable: Disposable) {
        getCompositeDisposable().add(disposable)
    }

    private fun getCompositeDisposable(): CompositeDisposable {
        if (mCompositeDisposable == null || mCompositeDisposable!!.isDisposed) {
            mCompositeDisposable = CompositeDisposable()
        }
        return mCompositeDisposable as CompositeDisposable
    }
}