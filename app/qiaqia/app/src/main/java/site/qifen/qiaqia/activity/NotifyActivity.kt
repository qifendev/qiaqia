package site.qifen.qiaqia.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_notify.*
import kotlinx.android.synthetic.main.toolbar.*
import site.qifen.qiaqia.MESSAGE_FRIEND
import site.qifen.qiaqia.R
import site.qifen.qiaqia.adapter.NotifyAdapter
import site.qifen.qiaqia.socket.SocketOwner
import site.qifen.qiaqia.viewmodel.AddViewModel

class NotifyActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun showToolbarBack() = true

    override fun initLayout() = R.layout.activity_notify


    override fun initView() {
        super.initView()
        toolbar.title = "通知"
        toolbar.setNavigationOnClickListener {
            finish()
        }


    }

    override fun initData() {
//        val addViewModel = ViewModelProvider(this).get(AddViewModel::class.java)
//        addViewModel.notifyMessage().observe(this, {
//            val notifyAdapter = NotifyAdapter(it, this)
//            addRecyclerView.adapter = notifyAdapter
//            notifyAdapter.setOnItemClickListener(object : NotifyAdapter.OnItemClickListener {
//                override fun onItemClick(position: Int) {
//                    val message = it[position]
//                    SocketOwner.send(message.messageTo, "", messageType = MESSAGE_FRIEND)
//
//                }
//            })
//        })


    }
}





