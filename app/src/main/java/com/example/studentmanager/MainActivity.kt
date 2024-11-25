package com.example.studentmanager

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

data class Student(val name: String, val id: Int) : Serializable

class MainActivity : AppCompatActivity() {

    private val students: MutableList<Student> = mutableListOf(
        Student("Nguyen Van A", 123),
        Student("Tran Van B", 456)
    )

    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val studentListView: ListView = findViewById(R.id.studentListView)

        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            students.map { "${it.name} - MSSV: ${it.id}" }
        )
        studentListView.adapter = adapter

        registerForContextMenu(studentListView)

        studentListView.setOnItemClickListener { _, view, _, _ ->
            view.showContextMenu()
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu?.add(0, 1, 0, "Edit")
        menu?.add(0, 2, 1, "Remove")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val position = info.position

        return when (item.itemId) {
            1 -> {
                val intent = Intent(this, AddEditStudentActivity::class.java)
                intent.putExtra("student", students[position])
                intent.putExtra("position", position)
                startActivityForResult(intent, 101)
                true
            }
            2 -> {
                students.removeAt(position)
                adapter.clear()
                adapter.addAll(students.map { "${it.name} - MSSV: ${it.id}" })
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "Student removed", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101 && resultCode == Activity.RESULT_OK) {
            val updatedStudent = data?.getSerializableExtra("updatedStudent") as Student
            val position = data.getIntExtra("position", -1)

            if (position != -1) {
                students[position] = updatedStudent
                adapter.clear()
                adapter.addAll(students.map { "${it.name} - MSSV: ${it.id}" })
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "Student updated", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

class AddEditStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_student)

        val student = intent.getSerializableExtra("student") as? Student
        val position = intent.getIntExtra("position", -1)

        val nameEditText: EditText = findViewById(R.id.nameEditText)
        val idEditText: EditText = findViewById(R.id.idEditText)
        val saveButton: Button = findViewById(R.id.saveButton)

        student?.let {
            nameEditText.setText(it.name)
            idEditText.setText(it.id.toString())
        }

        saveButton.setOnClickListener {
            val updatedStudent = Student(
                name = nameEditText.text.toString(),
                id = idEditText.text.toString().toInt()
            )
            val resultIntent = intent
            resultIntent.putExtra("updatedStudent", updatedStudent)
            resultIntent.putExtra("position", position)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
