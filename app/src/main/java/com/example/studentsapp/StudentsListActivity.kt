package com.example.studentsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.adapter.StudentsRecyclerAdapter
import com.example.studentsapp.model.Model
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StudentsListActivity : AppCompatActivity() {

    private var adapter: StudentsRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students_list)

        val recyclerView: RecyclerView = findViewById(R.id.students_recycler_view)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = StudentsRecyclerAdapter(Model.getAllStudents())
        recyclerView.adapter = adapter

        adapter?.listener = object : StudentsRecyclerAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                Log.d("TAG", "Row clicked: $position")
                val student = Model.getAllStudents()[position]
                
                val intent = Intent(this@StudentsListActivity, StudentDetailsActivity::class.java)
                intent.putExtra("student_id", student.id)
                startActivity(intent)
            }

            override fun onStudentCheck(position: Int) {
                val student = Model.getAllStudents()[position]
                student.isChecked = !student.isChecked
            }
        }

        val fab: FloatingActionButton = findViewById(R.id.students_fab_add)
        fab.setOnClickListener {
            val intent = Intent(this, NewStudentActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        adapter?.setStudents(Model.getAllStudents())
    }
}