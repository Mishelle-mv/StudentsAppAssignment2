package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.studentsapp.model.Model

class StudentDetailsActivity : AppCompatActivity() {

    private var currentStudentId: String? = null

    private lateinit var imageView: ImageView
    private lateinit var nameTv: TextView
    private lateinit var idTv: TextView
    private lateinit var phoneTv: TextView
    private lateinit var addressTv: TextView
    private lateinit var checkBox: CheckBox
    private lateinit var btnEdit: Button

    private val editLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode != RESULT_OK) return@registerForActivityResult

            val data = result.data
            val deleted = data?.getBooleanExtra("deleted", false) ?: false

            if (deleted) {
                finish()
                return@registerForActivityResult
            }

            val newId = data?.getStringExtra("updated_id")
            if (newId != null) {
                currentStudentId = newId
            }
            refresh()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)

        currentStudentId = intent.getStringExtra("student_id")
        if (currentStudentId == null) {
            Toast.makeText(this, "Student ID missing", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        imageView = findViewById(R.id.student_details_image)
        nameTv = findViewById(R.id.student_details_name)
        idTv = findViewById(R.id.student_details_id)
        phoneTv = findViewById(R.id.student_details_phone)
        addressTv = findViewById(R.id.student_details_address)
        checkBox = findViewById(R.id.student_details_checkbox)
        btnEdit = findViewById(R.id.btn_details_edit)

        checkBox.isEnabled = false

        btnEdit.setOnClickListener {
            val id = currentStudentId ?: return@setOnClickListener
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("student_id", id)
            editLauncher.launch(intent)
        }

        refresh()
    }

    private fun refresh() {
        val id = currentStudentId ?: return

        val student = Model.getStudentById(id)
        if (student == null) {
            Toast.makeText(this, "Student not found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        imageView.setImageResource(R.drawable.student_pic) // או R.drawable.img אם צריך
        nameTv.text = "Name: ${student.name}"
        idTv.text = "ID: ${student.id}"
        phoneTv.text = "Phone: ${student.phone}"
        addressTv.text = "Address: ${student.address}"
        checkBox.isChecked = student.isChecked
    }
}
