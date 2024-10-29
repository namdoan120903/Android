package com.example.b3

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var studentRecyclerView: RecyclerView
    private lateinit var studentAdapter: StudentAdapter
    private lateinit var searchEditText: EditText
    private lateinit var studentList: List<Student>
    private lateinit var filteredList: List<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        studentList = listOf(
            Student(name = "Nguyễn Văn An", mssv = "MSSV1001"),
            Student(name = "Trần Thị Bình", mssv = "MSSV1002"),
            Student(name = "Lê Ngọc Châu", mssv = "MSSV1003"),
            Student(name = "Phạm Văn Dũng", mssv = "MSSV1004"),
            Student(name = "Hoàng Thị Hà", mssv = "MSSV1005"),
            Student(name = "Vũ Minh Hùng", mssv = "MSSV1006"),
            Student(name = "Đặng Khánh Linh", mssv = "MSSV1007"),
            Student(name = "Bùi Tú Mai", mssv = "MSSV1008"),
            Student(name = "Đỗ Thu Nghĩa", mssv = "MSSV1009"),
            Student(name = "Hồ Quốc Phong", mssv = "MSSV1010")
        )

        filteredList = studentList

        searchEditText = findViewById(R.id.searchEditText)
        studentRecyclerView = findViewById(R.id.studentRecyclerView)
        studentAdapter = StudentAdapter(filteredList)
        studentRecyclerView.layoutManager = LinearLayoutManager(this)
        studentRecyclerView.adapter = studentAdapter

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                filterStudents(s.toString())
            }
        })
    }
    private fun filterStudents(keyword: String) {
        filteredList = if (keyword.length > 2) {
            studentList.filter {
                it.name.contains(keyword, ignoreCase = true) ||
                        it.mssv.contains(keyword, ignoreCase = true)
            }
        } else {
            studentList
        }
        studentAdapter.updateList(filteredList)
    }
}