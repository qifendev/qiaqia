package site.qifen.qiaqia.activity

import android.app.Activity

object ActivityCollector {


    private val activities = mutableListOf<Activity>()


    fun addActivity(activity: Activity) {
        activities.add(activity)
    }

    fun removeActivity(activity: Activity) {
        activities.remove(activity)
    }


    fun finishAll() {
        for (activity in activities) {
            if (!activity.isFinishing) {
                activity.finish()
            }
        }
        activities.clear()
    }


}