package com.bringmeal.kmvp

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import java.lang.reflect.ParameterizedType

/**
 * Created by zzk on 2018/9/17.
 */
@SuppressLint("Registered")
open class BaseMVPActivity<T : BasePresenter<*>> : Activity(), BaseView {
    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dismissProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    protected lateinit var mPresenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var startTime = System.currentTimeMillis()

        mPresenter = createPresenter()

        Log.i("kmvp", "create presenter use time:" + (System.currentTimeMillis() - startTime))
        startTime = System.currentTimeMillis()

        var declaredField = mPresenter.javaClass.getField("mView")
        declaredField.isAccessible = true
        declaredField.set(mPresenter, this)
        Log.i("kmvp", "injection mView use time:" + (System.currentTimeMillis() - startTime))

        var name = mPresenter.mView.javaClass.name
        var thisname = this.javaClass.name
        Log.i("kmvp", name)
        Log.i("kmvp", "thisname:" + thisname)
    }

    private fun createPresenter(): T {
        val pt = javaClass.genericSuperclass as ParameterizedType
        val type = pt.actualTypeArguments[0]
        val clazz = Class.forName(type.toString().split(" ")[1])
        return clazz.newInstance() as T
    }
}