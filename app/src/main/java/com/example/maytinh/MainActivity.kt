package com.example.maytinh

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private var currentNumber = "" // Chuỗi số hiện tại mà người dùng nhập
    private var firstNumber = 0.0 // Số đầu tiên trong phép toán
    private var secondNumber = 0.0 // Số thứ hai trong phép toán
    private var operator = "" // Phép toán đã chọn

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)

        // Lắng nghe sự kiện của các nút số
        findViewById<Button>(R.id.btn0).setOnClickListener { appendNumber("0") }
        findViewById<Button>(R.id.btn1).setOnClickListener { appendNumber("1") }
        findViewById<Button>(R.id.btn2).setOnClickListener { appendNumber("2") }
        findViewById<Button>(R.id.btn3).setOnClickListener { appendNumber("3") }
        findViewById<Button>(R.id.btn4).setOnClickListener { appendNumber("4") }
        findViewById<Button>(R.id.btn5).setOnClickListener { appendNumber("5") }
        findViewById<Button>(R.id.btn6).setOnClickListener { appendNumber("6") }
        findViewById<Button>(R.id.btn7).setOnClickListener { appendNumber("7") }
        findViewById<Button>(R.id.btn8).setOnClickListener { appendNumber("8") }
        findViewById<Button>(R.id.btn9).setOnClickListener { appendNumber("9") }
        findViewById<Button>(R.id.btnDot).setOnClickListener { appendNumber(".") }

        // Lắng nghe sự kiện của các phép toán
        findViewById<Button>(R.id.btnPlus).setOnClickListener { setOperator("+") }
        findViewById<Button>(R.id.btnMinus).setOnClickListener { setOperator("-") }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { setOperator("*") }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { setOperator("/") }

        // Lắng nghe sự kiện của nút bằng (=)
        findViewById<Button>(R.id.btnEqual).setOnClickListener { calculateResult() }

        // Lắng nghe sự kiện của nút Clear (C)
        findViewById<Button>(R.id.btnC).setOnClickListener { clearAll() }

        // Lắng nghe sự kiện của nút Clear Entry (CE)
        findViewById<Button>(R.id.btnCE).setOnClickListener { clearEntry() }

        // Lắng nghe sự kiện của nút Backspace (BS)
        findViewById<Button>(R.id.btnBS).setOnClickListener { backspace() }
    }

    // Hàm thêm số vào chuỗi hiện tại
    private fun appendNumber(number: String) {
        currentNumber += number
        tvResult.text = currentNumber // Hiển thị số hiện tại
    }

    // Hàm thiết lập phép toán
    private fun setOperator(op: String) {
        if (currentNumber.isNotEmpty()) {
            firstNumber = currentNumber.toDouble() // Lưu số đầu tiên
            operator = op // Ghi nhớ phép toán
            currentNumber = "" // Reset chuỗi số để nhập số thứ hai
        }
    }

    // Hàm tính toán kết quả
    private fun calculateResult() {
        if (currentNumber.isNotEmpty()) {
            secondNumber = currentNumber.toDouble() // Lấy số thứ hai
            val result = when (operator) {
                "+" -> firstNumber + secondNumber
                "-" -> firstNumber - secondNumber
                "*" -> firstNumber * secondNumber
                "/" -> if (secondNumber != 0.0) firstNumber / secondNumber else "Error"
                else -> 0.0
            }
            tvResult.text = result.toString() // Hiển thị kết quả
            currentNumber = "" // Reset chuỗi số
            operator = "" // Reset phép toán
        }
    }

    // Hàm để xóa tất cả
    private fun clearAll() {
        currentNumber = ""
        tvResult.text = "0"
        firstNumber = 0.0
        secondNumber = 0.0
        operator = ""
    }

    // Hàm để xóa phần nhập gần nhất
    private fun clearEntry() {
        currentNumber = ""
        tvResult.text = "0"
    }

    // Hàm để xóa ký tự cuối
    private fun backspace() {
        if (currentNumber.isNotEmpty()) {
            currentNumber = currentNumber.substring(0, currentNumber.length - 1)
            tvResult.text = if (currentNumber.isEmpty()) "0" else currentNumber
        }
    }
}
