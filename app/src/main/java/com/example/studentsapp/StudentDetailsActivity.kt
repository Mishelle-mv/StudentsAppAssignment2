package com.example.studentsapp

import android.os.Bundle
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.model.Model

class StudentDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        // 1. לקרוא את ה-ID מה-Intent
        val studentId = intent.getStringExtra("student_id")

        if (studentId == null) {
            Toast.makeText(this, "Student ID missing", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // 2. להביא את הסטודנט מה-Model
        val student = Model.getStudentById(studentId)

        if (student == null) {
            Toast.makeText(this, "Student not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // 3. חיבור ל-Views
        val imageView: ImageView = findViewById(R.id.student_details_image)
        val nameText: TextView = findViewById(R.id.student_details_name)
        val idText: TextView = findViewById(R.id.student_details_id)
        val phoneText: TextView = findViewById(R.id.student_details_phone)
        val addressText: TextView = findViewById(R.id.student_details_address)
        val checkBox: CheckBox = findViewById(R.id.student_details_checkbox)

        // 4. הצגת נתונים
        imageView.setImageResource(R.drawable.student_pic)
        nameText.text = "Name: ${student.name}"
        idText.text = "ID: ${student.id}"
        phoneText.text = "Phone: ${student.phone}"
        addressText.text = "Address: ${student.address}"
        checkBox.isChecked = student.isChecked
        checkBox.isEnabled = false
    }
}
