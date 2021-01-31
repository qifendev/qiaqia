package site.qifen.qiaqia.activity


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.SparseArray
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.SimpleAdapter
import android.widget.SimpleCursorAdapter
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import site.qifen.qiaqia.App
import site.qifen.qiaqia.R
import site.qifen.qiaqia.adapter.FragmentAdapter
import site.qifen.qiaqia.adapter.MainNewsAdapter
import site.qifen.qiaqia.fragment.FriendsFragment
import site.qifen.qiaqia.fragment.MyFragment
import site.qifen.qiaqia.fragment.NewsFragment
import site.qifen.qiaqia.socket.SocketOwner
import site.qifen.qiaqia.t
import java.util.*


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        Thread.sleep(3000)
//        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//        val channel = createChannel("channelName", "channelId")
//        notificationManager.createNotificationChannel(channel)
//        val notification = createNotification("哈哈哈", "哈哈哈哈哈哈", "channelId")
//        notificationManager.notify(999, notification)


    }


    override fun initLayout(): Int = R.layout.activity_main


    override fun initView() {
        super.initView()
        toolbar?.title = App.username
        toolbar?.overflowIcon = ContextCompat.getDrawable(this, R.drawable.main_tool)


//        SocketOwner.send("858810078@qq.com", "你好")
//
//        SocketOwner.send(
//            App.username,
//            "",
//            messageType = MESSAGE_ADD_FRIEND
//        )


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_toolbar_menu, menu)
//        val searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
//        searchView.queryHint = "搜搜"
//        searchView.isSubmitButtonEnabled = true
//        searchView.isFocusable = true
//        searchView.onActionViewExpanded()
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
////                t(query!!)
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                return true
//            }
//        })
//
//
//        searchView.setOnQueryTextFocusChangeListener { _, hasFocus ->
//            if (hasFocus) {
//                searchView.requestFocus()
//                searchView.requestFocusFromTouch()
//                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
//            } else {
//                hideKeyBoard(searchView)
//            }
//        }


        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.main_search -> startActivity(Intent(this, SearchActivity::class.java))
            R.id.main_add -> {
                startActivity(Intent(this, SearchActivity::class.java))
            }
            R.id.main_create_group -> {
                val intent = Intent(this, EditActivity::class.java)
                intent.putExtra("option", 1)
                intent.putExtra("info", "填写群名")
                startActivity(intent)
            }

        }
        return super.onOptionsItemSelected(item)
    }


    override fun initData() {
        val sparseArray = SparseArray<Fragment>()
        sparseArray.put(0, NewsFragment())
        sparseArray.put(1, FriendsFragment())
//        sparseArray.put(2, MomentsFragment())
        sparseArray.put(2, MyFragment())
        viewpager2.offscreenPageLimit = 2
        viewpager2.orientation = ORIENTATION_HORIZONTAL
        viewpager2.adapter = FragmentAdapter(this, sparseArray)

        viewpager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bottomNavigationView.menu.getItem(position).isChecked = true
            }
        })

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.tab_message -> viewpager2.setCurrentItem(0, true)
                R.id.tab_contacts -> viewpager2.setCurrentItem(1, true)
//                R.id.tab_moments -> viewpager2.setCurrentItem(2, true)
                R.id.tab_my -> viewpager2.setCurrentItem(2, true)
            }
            true
        }


    }


    override fun onBackPressed() {
//        super.onBackPressed()
        val intent = Intent() // 创建Intent对象
        intent.action = Intent.ACTION_MAIN // 设置Intent动作
        intent.addCategory(Intent.CATEGORY_HOME) // 设置Intent种类
        startActivity(intent) // 将Intent传递给Activity

    }


}


//val config = BarConfig.newInstance()          // 创建配置对象
//    .fitWindow(true)                          // 布局是否侵入状态栏（true 不侵入，false 侵入）
//    .color(Color.RED)                         // 状态栏背景颜色（色值）
//    .colorRes(R.color.deepSkyBlue)            // 状态栏背景颜色（资源id）
//    .drawableRes(R.drawable.bg_gradient)      // 状态栏背景 drawable
//    .light(false)                             // light模式
//// 状态栏字体 true: 灰色，false: 白色 Android 6.0+
//// 导航栏按钮 true: 灰色，false: 白色 Android 8.0+
//
//UltimateBarX.with(this)                       // 对当前 Activity 或 Fragment 生效
//.config(config)                           // 使用配置
//.applyStatusBar()                         // 应用到状态栏
//
//UltimateBarX.with(this)                       // 对当前 Activity 或 Fragment 生效
//.config(config)                           // 使用配置
//.applyNavigationBar()                     // 应用到导航栏