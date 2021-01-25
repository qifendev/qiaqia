package site.qifen.qiaqia.viewmodel;

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import site.qifen.qiaqia.data.*


class MainNewsViewModel : ViewModel() {

    private lateinit var messageList: LiveData<List<Message>>


    fun syncMainNews(): LiveData<List<Message>> {
        messageList = QiaDatabase.instance.messageDao().mainNewsMessage()
        return messageList
    }


}