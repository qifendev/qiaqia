package site.qifen.qiaqia.activity

import android.os.Bundle
import androidx.appcompat.widget.SearchView

import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import site.qifen.qiaqia.*
import site.qifen.qiaqia.adapter.GroupsAdapter
import site.qifen.qiaqia.adapter.MessagesAdapter
import site.qifen.qiaqia.adapter.SearchAdapter
import site.qifen.qiaqia.http.ApiService
import site.qifen.qiaqia.http.HttpRetrofit
import java.lang.Exception

class SearchActivity : BaseActivity() {

    var checkOption = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initLayout() = R.layout.activity_search


    override fun initView() {
        super.initView()




        searchView.isIconified = false
        searchView.setIconifiedByDefault(false)
        searchView.requestFocusFromTouch()
        searchView.isSubmitButtonEnabled = true
        searchView.queryHint = "搜搜"
        findFriends.isChecked = true
        findRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.findFriends -> {
                    checkOption = 1
                }
                R.id.findGroups -> {
                    checkOption = 2
                }
                R.id.findChats -> {
                    checkOption = 3
                }
            }
        }

    }

    override fun initData() {

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (notEmpty(query!!)) {
                    val co = CoroutineScope(Dispatchers.Main)
                    co.launch {
                        try {
                            val apiService = ApiService.create(HttpRetrofit::class.java)
                            when (checkOption) {
                                1 -> {
                                    val findUser = apiService.findUser(query)
                                    if (findUser.code == 200) {
                                        val searchAdapter =
                                            SearchAdapter(findUser.data, this@SearchActivity)
                                        findRecyclerView.adapter = searchAdapter
                                        searchAdapter.setOnItemClickListener(object :
                                            SearchAdapter.OnItemClickListener {
                                            override fun onItemClick(position: Int) {
                                                val user = findUser.data[position]
//                                                SocketOwner.send(
//                                                    user.userMail!!,
//                                                    "",
//                                                    messageType = MESSAGE_ADD_FRIEND
//                                                )
//                                                t("添加成功")

                                                co.launch {
                                                    val addFriend = apiService.addFriend(
                                                        App.username,
                                                        user.userMail!!
                                                    )
                                                    t(addFriend.message)
                                                }
                                            }
                                        })
                                    }
                                }
                                2 -> {

                                    val findGroup = apiService.findGroup(query)
                                    if (findGroup.code == 200 && findGroup.data.isNotEmpty()) {
                                        val groupsAdapter =
                                            GroupsAdapter(findGroup.data, this@SearchActivity)
                                        findRecyclerView.adapter = groupsAdapter
                                        groupsAdapter.setOnItemClickListener(object :
                                            GroupsAdapter.OnItemClickListener {
                                            override fun onItemClick(position: Int) {
                                                val group = findGroup.data[position]

                                                co.launch {
                                                    val addGroup = apiService.addGroup(
                                                        App.username,
                                                        group.groupName!!
                                                    )
                                                    t(addGroup.message)
                                                }

                                            }
                                        })


                                    }
                                }
                                3 -> {

                                    val findMessage = apiService.findMessage(query, App.username)
                                    if (findMessage.code == 200 && findMessage.data.isNotEmpty()) {
                                        findRecyclerView.adapter =
                                            MessagesAdapter(findMessage.data, this@SearchActivity)
                                    }

                                }
                            }
                        } catch (e: Exception) {
                            t(e.message.toString())
                        }
                    }
                }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }

        })
    }
}