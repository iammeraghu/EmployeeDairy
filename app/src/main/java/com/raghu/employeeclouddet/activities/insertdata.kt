package com.raghu.employeeclouddet.activities

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*

import android.widget.Button
import com.raghu.employeeclouddet.R
import com.raghu.employeeclouddet.employeemodel

class insertdata : AppCompatActivity() {
    private var formatdate=SimpleDateFormat("dd/MM/YYYY", Locale.US)
    private lateinit var empname:EditText
    private lateinit var empage:EditText
    private lateinit var empsalary:EditText
    private lateinit var savebutt:Button
//    private lateinit var check2:EditText
    private lateinit var empphoneno:EditText
    private lateinit var empdoj:TextView
private lateinit var dbref:DatabaseReference
   /* lateinit var imageView: ImageView
    lateinit var button: Button
    private val pickImage = 100
    private var imageUri: Uri? = null */


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertdata)

        empname= findViewById(R.id.empName)
        empage= findViewById(R.id.empage)
        empsalary=findViewById(R.id.empSalary)
        empphoneno=findViewById(R.id.empphoneno)


       empdoj=findViewById(R.id.empdatejoining)
        savebutt=findViewById(R.id.btnSave)
        //dbref= Firebase.database.reference
        dbref=FirebaseDatabase.getInstance()
            .getReference("Employees")





        savebutt.setOnClickListener{
                savedatafun()
        }

    }



   @SuppressLint("SuspiciousIndentation")
   private fun savedatafun()
    {
        Toast.makeText(this,"Working button ",Toast.LENGTH_LONG).show()
    val namepass=empname.text.toString()
    val agepass=empage.text.toString()
     val salarypass=empsalary.text.toString()
        val phonenopass=empphoneno.text.toString()
   val dojpass= "${empdoj.text}"




        if(namepass.isEmpty())
        {
            empname.error="Name Cannot be Blank"
        }
        if(agepass.isEmpty())
        {
           empage.error="Age Cannot be Blank"
        }
        if(salarypass.isEmpty())
        {
            empsalary.error="Salary Cannot be Blank"
        }
       if(dojpass.isEmpty())
        {
            empdoj.error="Select Date to proceed"
        }
        if(phonenopass.isEmpty())
        {
            empphoneno.error="Enter Valid Phone No"
        }

        val cloudid=dbref.push().key!!


        val employees= employeemodel(cloudid,namepass,agepass,salarypass,dojpass,phonenopass)
        dbref.child(cloudid).setValue(employees).addOnCompleteListener {
            Toast.makeText(this,"Data of $namepass is succesfully added to cloud ",Toast.LENGTH_LONG).show()
        }.addOnFailureListener{err ->         Toast.makeText(this,"Error ${err.message} ",Toast.LENGTH_LONG).show()
        }
    }


   public fun dateselectfun(view: View)
    {
        val getdate:Calendar= Calendar.getInstance()
        val datepicker= DatePickerDialog(this,android.R.style.ThemeOverlay_DeviceDefault_Accent_DayNight,DatePickerDialog.OnDateSetListener { view: DatePicker?, i1, i2, i3 ->
            val selectdate:Calendar= Calendar.getInstance()
            selectdate.set(Calendar.YEAR,i1)
            selectdate.set(Calendar.MONTH,i2)
            selectdate.set(Calendar.DAY_OF_MONTH,i3)
            val date=formatdate.format(selectdate.time)
            Toast.makeText(this,"Date $date",Toast.LENGTH_LONG).show()
            empdoj.text=date




        } ,getdate.get(Calendar.YEAR),getdate.get(Calendar.MONTH),getdate.get(Calendar.DAY_OF_MONTH))
        datepicker.show()
    }




}