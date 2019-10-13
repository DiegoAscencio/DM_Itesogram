package iteso.mx.itesogram.adapters

import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import iteso.mx.itesogram.Feed
import iteso.mx.itesogram.R


class AdapterPhoto (private val feed: ArrayList<Feed>): RecyclerView.Adapter<ViewHolder>() {

         override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int = feed.size


        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(feed[position])
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameTitle: TextView = view.findViewById(R.id.name)
        private val iPhoto: ImageView = view.findViewById(R.id.image)
        private val likes: TextView = view.findViewById(R.id.nLikes)

        fun bind(user: Feed) {
            nameTitle.text = user.username
            likes.text = user.commentsNumber.toString()
            val myBitmap = BitmapFactory.decodeFile(user.photo.absolutePath)
            iPhoto.setImageBitmap(myBitmap)
    }}