package com.raghu.employeeclouddet.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.raghu.employeeclouddet.R

class MainActivity : AppCompatActivity() {

    private lateinit var addnewuser :Button
    private lateinit var fetchuser:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       addnewuser=findViewById(R.id.btnInsertData)
       fetchuser=findViewById(R.id.btnFetchData)

        addnewuser.setOnClickListener{
            val intent=Intent(this, insertdata::class.java)
            startActivity(intent)
        }


        fetchuser.setOnClickListener{
            val intent=Intent(this, fetchactivity::class.java)
            startActivity(intent)
        }

   // val firebase :DatabaseReference=FirebaseDatabase.getInstance().reference

    }
}