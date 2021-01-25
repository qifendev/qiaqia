package site.qifen.qiaqia.adapter

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentAdapter(
    fragmentActivity: FragmentActivity,
    private var fragments: SparseArray<Fragment>
) : FragmentStateAdapter(fragmentActivity) {


    override fun getItemCount(): Int {
        return fragments.size()
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}