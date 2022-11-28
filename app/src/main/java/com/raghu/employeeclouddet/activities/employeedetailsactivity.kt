package com.raghu.employeeclouddet.activities

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.raghu.employeeclouddet.R

class employeedetailsactivity: AppCompatActivity() {


    private lateinit var tvEmpId: TextView
    private lateinit var tvEmpName: TextView
    private lateinit var tvEmpAge: TextView
    private lateinit var tvEmpSalary: TextView
    private lateinit var tvempdoj: TextView
    private lateinit var tvemppno:TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employeedetailsacivity)


        initView()
        setValuesToViews()



    }


    private fun initView() {
        tvEmpId = findViewById(R.id.tvEmpId)
        tvEmpName = findViewById(R.id.tvEmpName)
        tvEmpAge = findViewById(R.id.tvEmpAge)
        tvEmpSalary = findViewById(R.id.tvEmpSalary)
        tvempdoj =findViewById(R.id.tvEmpdateofjoining)
        tvemppno =findViewById(R.id.tvEmpphonno)

       // btnUpdate = findViewById(R.id.btnUpdate)
       // btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {
        tvEmpId.text = intent.getStringExtra("empid")
        tvEmpName.text = intent.getStringExtra("empname")
        tvEmpAge.text = intent.getStringExtra("empage")
        tvEmpSalary.text = intent.getStringExtra("empsalary")
        tvempdoj.text=intent.getStringExtra("empdoj")
        tvemppno.text=intent.getStringExtra("emppno")

    }
}



