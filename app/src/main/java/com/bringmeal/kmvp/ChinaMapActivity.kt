package com.bringmeal.kmvp

import android.os.Bundle
import android.util.Log

class ChinaMapActivity : BaseMVPActivity<ChinaMapPresenter>(), MapView {
    override fun print() {
        Log.i("kmvp", "print from ChinaMapActivity")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        mPresenter.mView = this

        var name = mPresenter.mView.javaClass.name
        Log.i("kmvp", name)

        mPresenter.callPrint()
    }
}
