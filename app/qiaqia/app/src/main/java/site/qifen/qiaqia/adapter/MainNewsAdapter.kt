package site.qifen.qiaqia.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import site.qifen.qiaqia.*
import site.qifen.qiaqia.data.Message
import java.text.SimpleDateFormat
import java.util.*

class MainNewsAdapter(private val messageList: List<Message>, val context: Context) :
    RecyclerView.Adapter<MainNewsAdapter.ViewHolder>() {


    private lateinit var clickListener: OnItemClickListener


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var newName: TextView = view.findViewById(R.id.newName)
        var newImageView: CircleImageView = view.findViewById(R.id.newImageView)
        var newContent: TextView = view.findViewById(R.id.newContent)
        var newNum: TextView = view.findViewById(R.id.newNum)
        var newTime: TextView = view.findViewById(R.id.newTime)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message: Message = messageList[position]




        if (message.messageType == MESSAGE_FRIEND) {
            if (message.messageFrom == message.messageTo) {
                holder.newName.text = message.messageTo
            } else if (message.messageFrom == App.username) {
                holder.newName.text = message.messageTo
            } else if (message.messageTo == App.username) {
                holder.newName.text = message.messageFrom
            }
        } else if (message.messageType == MESSAGE_GROUP) {
            if (message.messageFrom == App.username) {
                holder.newName.text = message.messageTo
            } else if (message.messageTo == App.username) {
                holder.newName.text = message.messageFrom
            } else {
                holder.newName.text = message.messageTo
            }
        }


        holder.newContent.text = edit(message.messageData)
        holder.newTime.text =
            edit(
                SimpleDateFormat(
                    "yyyy-MM-dd",
                    Locale.getDefault()
                ).format(Date(message.messageTime))
            )
        holder.newNum.text = edit("1")
        Glide.with(holder.newImageView)
            .load("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3363295869,2467511306&fm=26&gp=0.jpg")
            .into(holder.newImageView)

        holder.itemView.setOnClickListener {
            clickListener.onItemClick(position)
        }

    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.clickListener = onItemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }


    override fun getItemCount() = messageList.size
}