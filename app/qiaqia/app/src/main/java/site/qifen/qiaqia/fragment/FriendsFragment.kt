package site.qifen.qiaqia.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_friends.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import site.qifen.qiaqia.App
import site.qifen.qiaqia.App.Companion.username
import site.qifen.qiaqia.R
import site.qifen.qiaqia.activity.NotifyActivity
import site.qifen.qiaqia.activity.ShowNewsActivity
import site.qifen.qiaqia.adapter.FriendsAdapter
import site.qifen.qiaqia.adapter.GroupsAdapter
import site.qifen.qiaqia.adapter.MainGroupsAdapter
import site.qifen.qiaqia.data.Friend
import site.qifen.qiaqia.data.QiaDatabase
import site.qifen.qiaqia.http.ApiService
import site.qifen.qiaqia.http.HttpRetrofit
import site.qifen.qiaqia.t


class FriendsFragment : BaseFragment() {

    private var friendOption = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_friends, container, false)
    }

    override fun initData() {
    }


    override fun initView() {
        super.initView()


//        friendNotify.setOnClickListener {
//            startActivity(Intent(activity as Context, NotifyActivity::class.java))
//        }

        friendRadio.isChecked = true

        friendGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.friendRadio -> {
                    friendOption = 1
                    initRecycler()
                }
                R.id.groupRadio -> {
                    friendOption = 2
                    initRecycler()
                }
            }

        }

        initRecycler()

//        CoroutineScope(Dispatchers.Main).launch {
//            val allUser = ApiService.create(UserRetrofit::class.java).allUser()
//            if (allUser.size >= 0) {
//                val array = arrayOfNulls<String>(allUser.size)
//                for ((index, value) in allUser.withIndex()) {
//                    array[index] = value.userMail
//                }
//                listView.adapter =
//                    ArrayAdapter<String>(
//                        activity as Context,
//                        android.R.layout.simple_list_item_1,
//                        array
//                    )
//                listView.setOnItemClickListener { parent, view, position, id ->
//                    val intent =
//                        Intent(App.context, ShowNewsActivity::class.java)
//                    intent.putExtra("to", array[position])
//                    startActivity(intent)
//                }
//            }
//    }

    }


    private fun initRecycler() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val httpRetrofit = ApiService.create(HttpRetrofit::class.java)
                if (friendOption == 1) {
                    val data = httpRetrofit.myFriends(username)
                    if (data.code == 200 && data.data.isNotEmpty()) {
                        val friendsAdapter = FriendsAdapter(data.data, activity as Context)
                        friendRecyclerView.adapter = friendsAdapter
//                    friendsAdapter.setOnItemClickListener(object :FriendsAdapter.OnItemClickListener {
//                        override fun onItemClick(position: Int) {
//
//                        }
//                    })
                    }

                } else if (friendOption == 2) {
                    val myGroup = httpRetrofit.myGroup(username)
                    if (myGroup.code == 200 && myGroup.data.isNotEmpty()) {
                        friendRecyclerView.adapter =
                            MainGroupsAdapter(myGroup.data, activity as Context)
                    }
                }
            } catch (e: Exception) {
                e.message?.let { t(it) }
            }
        }
    }


}







