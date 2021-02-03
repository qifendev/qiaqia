package site.qifen.qiaqia.viewmodel;

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import site.qifen.qiaqia.data.Friend
import site.qifen.qiaqia.data.Group
import site.qifen.qiaqia.data.Message

public class FriendsViewModel : ViewModel() {


    private lateinit var friendList: LiveData<List<Friend>>
    private lateinit var groupList: LiveData<List<Group>>


    fun friend() {

    }

    fun group() {

    }


}
