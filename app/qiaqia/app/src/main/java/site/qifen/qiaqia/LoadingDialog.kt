package site.qifen.qiaqia

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.widget.LinearLayout
import site.qifen.qiaqia.*
import site.qifen.qiaqia.fragment.BaseFragment


class LoadingDialog(context: Context) : Dialog(context) {


    init {
        setCancelable(true)
        window?.setDimAmount(0.5F)
        window?.setBackgroundDrawableResource(R.drawable.load_range)
        window?.setContentView(initView())

    }


    @SuppressLint("InflateParams")
    private fun initView(): LinearLayout {
        return layoutInflater.inflate(R.layout.loading_view, null) as LinearLayout
    }

}