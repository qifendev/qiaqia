package site.qifen.qiaqia.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import site.qifen.qiaqia.App
import site.qifen.qiaqia.data.Message
import site.qifen.qiaqia.data.QiaDatabase
import site.qifen.qiaqia.fromMine

class DetailNewsViewModel : ViewModel() {

    private lateinit var newsDetail: LiveData<List<Message>>

    fun syncDetailNews(messageFrom: String): LiveData<List<Message>> {
        newsDetail= if (messageFrom == App.username) {
            QiaDatabase.instance.messageDao().detailMineMessage(messageFrom)
        } else {
            QiaDatabase.instance.messageDao().detailMessage(messageFrom)
        }
        return newsDetail
    }

}