package com.example.moneyexchange

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var emailRecyclerView: RecyclerView
    private lateinit var emailList: ArrayList<Email>
    private lateinit var emailAdapter: EmailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailRecyclerView = findViewById(R.id.recycler_view)
        emailRecyclerView.layoutManager = LinearLayoutManager(this)
        emailRecyclerView.setHasFixedSize(true)

        emailList = arrayListOf(
            Email("Edurila.com", "$19 Only (First 10 spots)", "Are you looking to Learn Web Design?", "12:34 PM", Color.BLUE),
            Email("Chris Abad", "Help make Campaign Monitor better", "Let us know your thoughts!", "11:22 AM", Color.parseColor("#FF9800")),
            Email("Tuto.com", "Free training and tutorials", "Photoshop, SEO, Blender, CSS...", "11:04 AM", Color.parseColor("#4CAF50")),
            Email("Support", "Follow-up on your service", "Details about your account", "10:26 AM", Color.GRAY),
            Email("Matt from Ionic", "The new Ionic Creator is here!", "Announcing the all-new Creator...", "9:32 AM", Color.parseColor("#8BC34A"))
        )

        emailAdapter = EmailAdapter(emailList)
        emailRecyclerView.adapter = emailAdapter
    }
}
