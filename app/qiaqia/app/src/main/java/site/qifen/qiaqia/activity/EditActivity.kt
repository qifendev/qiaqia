package site.qifen.qiaqia.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import site.qifen.qiaqia.App
import site.qifen.qiaqia.R
import site.qifen.qiaqia.http.ApiService
import site.qifen.qiaqia.http.HttpRetrofit
import site.qifen.qiaqia.notEmpty
import site.qifen.qiaqia.t

class EditActivity : BaseActivity() {


    private var editOption = 0 //1群名


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun initView() {
        super.initView()
        toolbar.setNavigationOnClickListener {
            finish()
        }
        edit_back.setOnClickListener {
            finish()
        }


    }


    override fun initData() {
        editOption = intent.getIntExtra("option", 0)
        edit_info.text = intent.getStringExtra("info")
        toolbar.title = intent.getStringExtra("info")

        edit_conf.setOnClickListener {
            val editText = edit_text.text.toString()
            if (notEmpty(editText)) {
                when (editOption) {
                    1 -> {
                        CoroutineScope(Dispatchers.Main).launch {
                            val createGroup = ApiService.create(HttpRetrofit::class.java)
                                .createGroup(editText, App.username)
                            if (createGroup.code == 200) {
                                t(createGroup.message)
                                finish()
                            }

                        }

                    }
                }
            }
        }


    }


    override fun showToolbarBack() = true


    override fun initLayout() = R.layout.activity_edit

}