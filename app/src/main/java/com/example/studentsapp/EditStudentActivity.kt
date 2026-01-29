package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.model.Model
import com.example.studentsapp.model.Student

class EditStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_student)

        val oldId = intent.getStringExtra("student_id")
        if (oldId == null) {
            Toast.makeText(this, "Student ID missing", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val student = Model.getStudentById(oldId)
        if (student == null) {
            Toast.makeText(this, "Student not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val imageView: ImageView = findViewById(R.id.edit_student_image)
        val etName: EditText = findViewById(R.id.et_edit_name)
        val etId: EditText = findViewById(R.id.et_edit_id)
        val etPhone: EditText = findViewById(R.id.et_edit_phone)
        val etAddress: EditText = findViewById(R.id.et_edit_address)
        val cbChecked: CheckBox = findViewById(R.id.cb_edit_checked)

        val btnCancel: Button = findViewById(R.id.btn_edit_cancel)
        val btnDelete: Button = findViewById(R.id.btn_edit_delete)
        val btnSave: Button = findViewById(R.id.btn_edit_save)

        imageView.setImageResource(R.drawable.student_pic) // או R.drawable.img אם צריך
        etName.setText(student.name)
        etId.setText(student.id)
        etPhone.setText(student.phone)
        etAddress.setText(student.address)
        cbChecked.isChecked = student.isChecked

        btnCancel.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

        btnDelete.setOnClickListener {
            Model.deleteStudentById(oldId)

            val data = Intent().putExtra("deleted", true)
            setResult(RESULT_OK, data)
            finish()
        }

        btnSave.setOnClickListener {
            val newName = etName.text.toString().trim()
            val newId = etId.text.toString().trim()
            val newPhone = etPhone.text.toString().trim()
            val newAddress = etAddress.text.toString().trim()
            val newChecked = cbChecked.isChecked

            if (newName.isEmpty() || newId.isEmpty()) {
                Toast.makeText(this, "Name and ID are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val updated = Student(
                name = newName,
                id = newId,
                phone = newPhone,
                address = newAddress,
                isChecked = newChecked
            )

            val ok = Model.updateStudent(oldId, updated)
            if (!ok) {
                Toast.makeText(this, "Update failed", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val data = Intent()
                .putExtra("deleted", false)
                .putExtra("updated_id", updated.id)

            setResult(RESULT_OK, data)
            finish()
        }
    }
}
