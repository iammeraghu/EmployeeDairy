package com.raghu.employeeclouddet.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.raghu.employeeclouddet.R
import com.raghu.employeeclouddet.empadapter
import com.raghu.employeeclouddet.employeemodel

class fetchactivity : AppCompatActivity() {
    private lateinit var emprecyclerview:RecyclerView
    private lateinit var tvloadingdata:TextView
    private lateinit var emplist:ArrayList<employeemodel>
    private lateinit var dbref:DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetchactivity)
        emprecyclerview=findViewById(R.id.recyclerview)
        emprecyclerview.layoutManager =LinearLayoutManager(this)
        emprecyclerview.setHasFixedSize(true)
        tvloadingdata=findViewById(R.id.tvLoadingData)
        emplist= arrayListOf<employeemodel>()
        getemployeesdata()

    }

    private fun getemployeesdata() {
        emprecyclerview.visibility =View.GONE
        tvloadingdata.visibility=View.VISIBLE
        val dbref =FirebaseDatabase.getInstance().getReference("Employees")
        dbref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                emplist.clear()
                if (snapshot.exists()) {
                    for (empSnap in snapshot.children)
                    {
                        val empdata=empSnap.getValue(employeemodel::class.java)
                        emplist.add(empdata!!)
                    }
                        val madapter =empadapter(emplist)
                        emprecyclerview.adapter=madapter
                    madapter.setonitemclicklistner(object :empadapter.onItemClickListner
                    {
                        override fun onitemclick(position: Int) {
                            val intent =Intent(this@fetchactivity,employeedetailsactivity::class.java)
                            intent.putExtra("empid",emplist[position].mempid)
                            intent.putExtra("empname",emplist[position].mempname)

                            intent.putExtra("empage",emplist[position].mempage)
                            intent.putExtra("empsalary",emplist[position].mempsalary)
                            intent.putExtra("empdoj",emplist[position].mdoj)
                            intent.putExtra("emppno",emplist[position].mempphoneno)
                            startActivity(intent)

                        }

                    })




                    emprecyclerview.visibility =View.VISIBLE
                    tvloadingdata.visibility=View.GONE

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}