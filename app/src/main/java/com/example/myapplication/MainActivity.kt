package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val students = mutableListOf(
            StudentModel("Nguyễn Văn An", "SV001"),
            StudentModel("Trần Thị Bảo", "SV002"),
            StudentModel("Lê Hoàng Cường", "SV003")

        )

        val studentAdapter = StudentAdapter(students)

        findViewById<RecyclerView>(R.id.recycler_view_students).run {
            adapter = studentAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        fun showStudentDialog(
            student: StudentModel? = null,
            onSave: (StudentModel) -> Unit
        ) {
            val dialogView = layoutInflater.inflate(R.layout.dialog_student, null)
            val edtName = dialogView.findViewById<EditText>(R.id.edt_name)
            val edtId = dialogView.findViewById<EditText>(R.id.edt_id)

            student?.let {
                edtName.setText(it.studentName)
                edtId.setText(it.studentId)
            }

            AlertDialog.Builder(this)
                .setTitle(if (student == null) "Add Student" else "Edit Student")
                .setView(dialogView)
                .setPositiveButton("Save") { _, _ ->
                    val name = edtName.text.toString()
                    val id = edtId.text.toString()
                    onSave(StudentModel(name, id))
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
        findViewById<Button>(R.id.btn_add_new).setOnClickListener {
            showStudentDialog { newStudent ->
                students.add(newStudent)
                studentAdapter.notifyItemInserted(students.size - 1)
            }
        }

        studentAdapter.onEditClick = { position, student ->
            showStudentDialog(student) { updatedStudent ->
                students[position] = updatedStudent
                studentAdapter.notifyItemChanged(position)
            }
        }

        studentAdapter.onRemoveClick = { position ->
            val removedStudent = students[position]
            students.removeAt(position)
            studentAdapter.notifyItemRemoved(position)

            Snackbar.make(findViewById(R.id.main), "Student removed", Snackbar.LENGTH_LONG)
                .setAction("Undo") {
                    students.add(position, removedStudent)
                    studentAdapter.notifyItemInserted(position)
                }.show()
        }

    }
}