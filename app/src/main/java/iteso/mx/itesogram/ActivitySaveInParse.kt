package iteso.mx.itesogram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.parse.ParseObject
import com.parse.ParseQuery
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread

class ActivitySaveInParse : AppCompatActivity() {
    private lateinit var mValue: EditText
    private lateinit var mSave: Button
    private lateinit var mFetch: Button
    private lateinit var mValueText: TextView

    fun String.toEditable() = Editable.Factory.getInstance().newEditable(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_in_parse)

        mValue = find(R.id.activity_tiet_value)
        mSave = find(R.id.activity_btn_save)
        mFetch = find(R.id.acitivity_btn_fetch)
        mValueText = find(R.id.activity_tv_value)

        mSave.setOnClickListener {
            val value = mValue.text.toString()

            val studentObject = ParseObject("Tarea")
            studentObject.put("expediente", "is698470")
            studentObject.put("valor", value)
            studentObject.saveInBackground()
            mValue.text = "".toEditable()
        }

        mFetch.setOnClickListener {
            doAsync {
                val query = ParseQuery<ParseObject>("Tarea")
                query.whereEqualTo("expediente", "is698470")
                query.addDescendingOrder("createdAt")
                query.getFirstInBackground { obj, e ->
                    uiThread {
                        if (e == null)
                            mValueText.text = obj["valor"] as String
                        else
                            error { e }
                    }

                }
            }
        }
    }
}
