package com.example.studentsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.R
import com.example.studentsapp.model.Student

class StudentsRecyclerAdapter(private var students: List<Student>?) :
    RecyclerView.Adapter<StudentsRecyclerAdapter.StudentViewHolder>() {

    // Listener interface for clicks
    var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onStudentCheck(position: Int) // Optional: If specific logic needed for check
    }

    // Set the data and notify the adapter
    fun setStudents(students: List<Student>?) {
        this.students = students
        notifyDataSetChanged()
    }

    // Create the view holder (inflate layout)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.student_list_row,
            parent,
            false
        )
        return StudentViewHolder(itemView, listener)
    }

    // Bind data to view holder
    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students?.get(position)
        holder.bind(student)
    }

    override fun getItemCount(): Int = students?.size ?: 0

    // Inner ViewHolder class
    class StudentViewHolder(
        itemView: View,
        listener: OnItemClickListener?
    ) : RecyclerView.ViewHolder(itemView) {

        private val nameTextView: TextView = itemView.findViewById(R.id.student_row_name)
        private val idTextView: TextView = itemView.findViewById(R.id.student_row_id)
        private val checkBox: CheckBox = itemView.findViewById(R.id.student_row_check)

        init {
            // Handle Row Click
            itemView.setOnClickListener {
                listener?.onItemClick(adapterPosition)
            }

            // Handle Checkbox Click
            checkBox.setOnClickListener {
                // We use setOnClickListener instead of onCheckedChange to avoid recycling bugs
                listener?.onStudentCheck(adapterPosition)
            }
        }

        fun bind(student: Student?) {
            student?.let {
                nameTextView.text = it.name
                idTextView.text = "ID: ${it.id}"
                checkBox.isChecked = it.isChecked
            }
        }
    }
}