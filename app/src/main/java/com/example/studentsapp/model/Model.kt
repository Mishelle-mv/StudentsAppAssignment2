package com.example.studentsapp.model

object Model {

    private val students: MutableList<Student> = mutableListOf()

    init {
        // Dummy data – so the list is not empty
        for (i in 1..20) {
            students.add(
                Student(
                    name = "Student $i",
                    id = i.toString(),
                    phone = "050-00000$i",
                    address = "Street $i, City",
                    isChecked = false
                )
            )
        }
    }

    /* =======================
       CRUD OPERATIONS
       ======================= */

    fun getAllStudents(): MutableList<Student> {
        return students
    }

    fun addStudent(student: Student) {
        students.add(student)
    }

    fun getStudentById(id: String): Student? {
        return students.find { it.id == id }
    }

    fun deleteStudentById(id: String): Boolean {
        val student = getStudentById(id) ?: return false
        students.remove(student)
        return true
    }

    /**
     * Update student by OLD id.
     * Needed so we can also change the ID itself.
     */
    fun updateStudent(oldId: String, updatedStudent: Student): Boolean {
        val index = students.indexOfFirst { it.id == oldId }
        if (index == -1) return false
        students[index] = updatedStudent
        return true
    }
}
