package com.example.studentsapp

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student

class NewStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_student)

        val nameEditText = findViewById<EditText>(R.id.new_student_name)
        val idEditText = findViewById<EditText>(R.id.new_student_id)
        val phoneEditText = findViewById<EditText>(R.id.new_student_phone)
        val addressEditText = findViewById<EditText>(R.id.new_student_address)
        val checkedCheckBox = findViewById<CheckBox>(R.id.new_student_checked)
        val saveButton = findViewById<Button>(R.id.new_student_save_btn)
        val cancelButton = findViewById<Button>(R.id.new_student_cancel_btn)

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val id = idEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val address = addressEditText.text.toString()
            val isChecked = checkedCheckBox.isChecked

            if (name.isNotEmpty() && id.isNotEmpty()) {
                val newStudent = Student(name, id, phone, address, isChecked)
                Model.addStudent(newStudent)
                finish()
            }
        }

        cancelButton.setOnClickListener {
            finish()
        }
    }
}
