package com.example.studentsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studentsapp.R
import com.example.studentsapp.model.Student

class StudentsRecyclerAdapter(private var students: MutableList<Student>?) :
    RecyclerView.Adapter<StudentsRecyclerAdapter.StudentViewHolder>() {

    var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onStudentCheck(position: Int)
    }

    fun setStudents(students: MutableList<Student>?) {
        this.students = students
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = students?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.student_list_row,
            parent,
            false
        )
        return StudentViewHolder(itemView, listener)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students?.get(position)
        holder.bind(student, position)
    }

    inner class StudentViewHolder(
        itemView: View,
        listener: OnItemClickListener?
    ) : RecyclerView.ViewHolder(itemView) {

        private val nameTextView: TextView = itemView.findViewById(R.id.student_row_name)
        private val idTextView: TextView = itemView.findViewById(R.id.student_row_id)
        private val checkBox: CheckBox = itemView.findViewById(R.id.student_row_check)

        init {
            itemView.setOnClickListener {
                listener?.onItemClick(adapterPosition)
            }
            
            checkBox.setOnClickListener { 
                listener?.onStudentCheck(adapterPosition)
            }
        }

        fun bind(student: Student?, position: Int) {
            student?.let {
                nameTextView.text = it.name
                idTextView.text = "ID: ${it.id}"
                checkBox.isChecked = it.isChecked
            }
        }
    }
}