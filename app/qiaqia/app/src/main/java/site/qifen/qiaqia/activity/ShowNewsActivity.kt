package site.qifen.qiaqia.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_show_news.*
import kotlinx.android.synthetic.main.toolbar.*
import site.qifen.qiaqia.*
import site.qifen.qiaqia.adapter.NewsDetailAdapter
import site.qifen.qiaqia.data.Message
import site.qifen.qiaqia.socket.SocketOwner
import site.qifen.qiaqia.viewmodel.DetailNewsViewModel

class ShowNewsActivity : BaseActivity() {


    private var messageTo: String? = null
    private var type: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun initLayout() = R.layout.activity_show_news


    override fun initView() {
        super.initView()
        messageTo = intent.getStringExtra("to")
        type = intent.getIntExtra("type",0)
        toolbar?.title = messageTo

        val newsDetailViewModel = ViewModelProvider(this).get(DetailNewsViewModel::class.java)
        messageTo?.let { messageTo ->
            newsDetailViewModel.syncDetailNews(messageTo).observe(this, {
                it?.let {
                    recyclerView.layoutManager = LinearLayoutManager(this)
                    recyclerView.scrollToPosition(it.size - 1)
                    recyclerView.adapter = NewsDetailAdapter(it, messageTo)
                }
            })
        }

        sendBtn.setOnClickListener {
            val text = text(sendText)
            if (notEmpty(text))
                messageTo?.let { to ->
                    SocketOwner.send(to, text,messageType = type)
                    sendText.text = edit("")
                }

        }

        toolbar.setNavigationOnClickListener {
            finish()
        }

    }


    override fun showToolbarBack() = true


    override fun initData() {
    }


}