package site.qifen.qiaqia.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_news.*
import site.qifen.qiaqia.App
import site.qifen.qiaqia.MESSAGE_FRIEND
import site.qifen.qiaqia.MESSAGE_GROUP
import site.qifen.qiaqia.R
import site.qifen.qiaqia.activity.ShowNewsActivity
import site.qifen.qiaqia.adapter.MainNewsAdapter
import site.qifen.qiaqia.viewmodel.MainNewsViewModel


class NewsFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_news, container, true)
    }

    override fun initView() {
        super.initView()
        val newsViewModel = ViewModelProvider(this).get(MainNewsViewModel::class.java)
        newsViewModel.syncMainNews().observe(this, {
            it?.let {
                recyclerView.layoutManager = LinearLayoutManager(activity)
                val mainNewsAdapter = MainNewsAdapter(it, activity as Context)
                recyclerView.adapter = mainNewsAdapter
                mainNewsAdapter.setOnItemClickListener(object :
                    MainNewsAdapter.OnItemClickListener {
                    override fun onItemClick(position: Int) {
                        val message = it[position]
                        val intent = Intent(activity, ShowNewsActivity::class.java)
                        if (message.messageType == MESSAGE_FRIEND) {
                            if (message.messageFrom == message.messageTo) {
                                intent.putExtra("to", message.messageTo)
                            } else if (message.messageFrom == App.username) {
                                intent.putExtra("to", message.messageTo)
                            } else if (message.messageTo == App.username) {
                                intent.putExtra("to", message.messageFrom)
                            }
                        } else if (message.messageType == MESSAGE_GROUP) {
                            if (message.messageFrom == App.username) {
                                intent.putExtra("to", message.messageTo)
                            } else if (message.messageTo == App.username) {
                                intent.putExtra("to", message.messageFrom)
                            } else {
                                intent.putExtra("to", message.messageTo)
                            }


                        }


                        intent.putExtra("type", message.messageType)


                        startActivity(intent)
                    }
                })

            }
        })
    }

    override fun initData() {

    }


}