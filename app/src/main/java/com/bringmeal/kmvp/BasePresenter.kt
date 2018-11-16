package com.bringmeal.kmvp


/**
 * Created by zzk on 2018/9/17.
 */
open class BasePresenter<V : BaseView> {

    lateinit var mView: V
}