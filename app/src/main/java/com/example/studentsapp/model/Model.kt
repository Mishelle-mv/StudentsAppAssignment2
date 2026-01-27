package com.example.studentsapp.model

object Model {
    // The shared data structure
    val students: MutableList<Student> = ArrayList()

    init {
        // Generating 10 mock students
        for (i in 0..20) {
            val student = Student(
                name = "Student $i",
                id = "$i",
                phone = "050-00000$i",
                address = "Street $i, City",
                isChecked = false
            )
            students.add(student)
        }
    }
    // --- Interface Methods ---

    fun getAllStudents(): MutableList<Student> {
        return students
    }

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun getStudentById(id: String): Student? {
        return students.find { it.id == id }
    }

    fun deleteStudent(student: Student) {
        students.remove(student)
    }
    
    fun deleteStudent(pos: Int) {
        if (pos in students.indices) {
            students.removeAt(pos)
        }
    }
}