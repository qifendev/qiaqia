package site.qifen.qiaqia.adapter;

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import site.qifen.qiaqia.MESSAGE_FRIEND
import site.qifen.qiaqia.R
import site.qifen.qiaqia.activity.ShowNewsActivity
import site.qifen.qiaqia.data.Friend

public class FriendsAdapter(private val friendsList: List<Friend>, val context: Context) :
    RecyclerView.Adapter<FriendsAdapter.ViewHolder>() {

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
        val friend = friendsList[position]
        holder.friendsName.text = friend.friendTo
        holder.friendsContent.text = friend.friendFrom
        Glide.with(context)
            .load("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3363295869,2467511306&fm=26&gp=0.jpg")
            .into(holder.friendsImageView)
        holder.friendsBtn.visibility = View.INVISIBLE
        holder.itemView.setOnClickListener {
            val intent = Intent(context, ShowNewsActivity::class.java)
            intent.putExtra("to", friend.friendTo)
            intent.putExtra("type", MESSAGE_FRIEND)
            context.startActivity(intent)
        }
    }

    private lateinit var clickListener: OnItemClickListener

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.clickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun getItemCount() = friendsList.size

}











