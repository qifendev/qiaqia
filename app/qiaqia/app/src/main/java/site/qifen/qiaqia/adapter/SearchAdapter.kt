package site.qifen.qiaqia.adapter;

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import site.qifen.qiaqia.App
import site.qifen.qiaqia.R
import site.qifen.qiaqia.data.User
import site.qifen.qiaqia.notEmpty

public class SearchAdapter(private val userList: List<User>, val context: Context) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var friendsImageView: CircleImageView = view.findViewById(R.id.friendImageView)
        var friendsName: TextView = view.findViewById(R.id.friendName)
        var friendsContent: TextView = view.findViewById(R.id.friendContent)
        var friendsBtn: Button = view.findViewById(R.id.friendBtn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.friend_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.friendsName.text = user.userMail
        holder.friendsContent.text = user.userNickName
        if (notEmpty(user.userAvatar)) {
            Glide.with(context)
                .load(user.userAvatar)
                .into(holder.friendsImageView)
        } else {
            Glide.with(context)
                .load(R.drawable.qiaqia)
                .into(holder.friendsImageView)
        }
        if (App.username == user.userMail) {
            holder.friendsBtn.text = "添加"
            holder.friendsBtn.isEnabled = false
        } else {
            holder.friendsBtn.text = "添加"
        }

        holder.friendsBtn.setOnClickListener {
            clickListener.onItemClick(position)
        }
    }

    private lateinit var clickListener: OnItemClickListener

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.clickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun getItemCount() = userList.size

}











