package site.qifen.qiaqia.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import site.qifen.qiaqia.LoadingDialog

abstract class BaseFragment : Fragment() {


    lateinit var loadPop: LoadingDialog

    fun load() = if (loadPop.isShowing) Unit else loadPop.show()

    fun unload() = if (loadPop.isShowing) loadPop.dismiss() else Unit

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
        initView()
    }


    open fun initView() {
        loadPop = LoadingDialog(activity as Context)
    }


    abstract fun initData()


}








