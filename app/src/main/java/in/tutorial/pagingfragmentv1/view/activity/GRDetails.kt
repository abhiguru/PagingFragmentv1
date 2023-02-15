package `in`.tutorial.pagingfragmentv1.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import `in`.tutorial.pagingfragmentv1.R

class GRDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grdetails)
        val grId = intent.getStringExtra("grId")
        val tv = findViewById<TextView>(R.id.tv_grId)
        tv.text = grId
    }
}