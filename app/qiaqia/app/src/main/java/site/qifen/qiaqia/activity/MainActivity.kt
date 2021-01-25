package site.qifen.qiaqia.activity


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.SparseArray
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import site.qifen.qiaqia.App
import site.qifen.qiaqia.R
import site.qifen.qiaqia.adapter.FragmentAdapter
import site.qifen.qiaqia.data.Message
import site.qifen.qiaqia.fragment.FriendsFragment
import site.qifen.qiaqia.fragment.MomentsFragment
import site.qifen.qiaqia.fragment.MyFragment
import site.qifen.qiaqia.fragment.NewsFragment
import site.qifen.qiaqia.socket.SocketLifecycle
import site.qifen.qiaqia.socket.SocketOwner
import java.lang.Exception
import java.net.Socket


class MainActivity : BaseActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("InflateParams")
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



//        SocketOwner.send("2654299451@qq.com","你好")



    }


    override fun initData() {
        val sparseArray = SparseArray<Fragment>()
        sparseArray.put(0, NewsFragment())
        sparseArray.put(1, FriendsFragment())
        sparseArray.put(2, MomentsFragment())
        sparseArray.put(3, MyFragment())
        viewpager2.offscreenPageLimit = 3
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
                R.id.tab_moments -> viewpager2.setCurrentItem(2, true)
                R.id.tab_my -> viewpager2.setCurrentItem(3, true)
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