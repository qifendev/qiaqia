package site.qifen.qiaqia.viewmodel;

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import site.qifen.qiaqia.data.Message
import site.qifen.qiaqia.data.QiaDatabase

class AddViewModel : ViewModel() {

    private lateinit var notifyList: LiveData<List<Message>>

//    fun notifyMessage(): LiveData<List<Message>> {
//        notifyList = QiaDatabase.instance.messageDao().notifyMessage()
//        return notifyList
//    }


}
