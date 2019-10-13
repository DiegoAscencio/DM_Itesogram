package iteso.mx.itesogram

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ParseQuery
import iteso.mx.itesogram.adapters.AdapterPhoto
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import java.io.File


class ActivityMain: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.activity_main_elements)
        val feedList = arrayListOf<Feed>()

        //doAsync {
            val query = ParseQuery.getQuery<ParseObject>("UserList")
            query.findInBackground{objects, e ->
                if (e == null) {
                    for (armor in objects) {
                        val file = armor.get("photo") as ParseFile
                        val user = Feed(
                             armor.get("name").toString(),
                             file.file,
                             0)
                        //Log.d("User",user.toString())
                         feedList.add(Feed(
                             armor.get("name").toString(),
                             file.file,
                             0))
                        //Log.d("A ver si verga jala", feedList.toString())
                        //Log.d("A ver si verga jala", armor.get("name").toString())
                    }
                } else {
                    Log.d("score", "Error: " + e!!.message)
                }
           // }
            Log.d("A ver si verga jala", feedList.toString())
            recyclerView.adapter = AdapterPhoto(feedList)
            recyclerView.layoutManager = LinearLayoutManager(this@ActivityMain)
        }
    }
}

data class Feed(
    var username: String,
    var photo: File,
    var commentsNumber: Int)

