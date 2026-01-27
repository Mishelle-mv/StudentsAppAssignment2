package com.example.studentsapp.model

object Model {
    // The shared data structure
    val students: MutableList<Student> = ArrayList()

    // Stub for future mock data generation
    init {
        // TODO: Person A adds mock data here later
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