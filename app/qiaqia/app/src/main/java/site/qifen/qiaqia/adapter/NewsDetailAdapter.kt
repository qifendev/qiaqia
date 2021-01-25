package site.qifen.qiaqia.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import site.qifen.qiaqia.R
import site.qifen.qiaqia.data.Message
import site.qifen.qiaqia.edit
import site.qifen.qiaqia.fromMine

class NewsDetailAdapter(private val messageList: List<Message>, private val messageTo: String) :
    RecyclerView.Adapter<NewsDetailAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val messageName: TextView = view.findViewById(R.id.messageName)
        val messageContent: TextView = view.findViewById(R.id.messageContent)
        val messageImageView: CircleImageView = view.findViewById(R.id.messageImageView)

    }

    override fun getItemViewType(position: Int): Int {
        val message = messageList[position]
        return if (fromMine(message)) {
            1
        } else {
            2
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = if (viewType == 1) {
            LayoutInflater.from(parent.context).inflate(R.layout.right_pop, parent, false)
        } else  {
            LayoutInflater.from(parent.context).inflate(R.layout.left_pop, parent, false)
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val message: Message = messageList[position]
        holder.messageContent.text = edit(message.messageData)
        holder.messageName.text = edit(message.messageFrom)
        Glide.with(holder.messageImageView)
            .load("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3363295869,2467511306&fm=26&gp=0.jpg")
            .into(holder.messageImageView)


    }

    override fun getItemCount() = messageList.size
}

















