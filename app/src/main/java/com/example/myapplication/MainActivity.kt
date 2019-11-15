package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.calculateBtn.setOnClickListener{
            clickHandlerFunction(it)
        }
    }

    private fun clickHandlerFunction(view: View){
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val carPrice: Double = binding.editPrice.text.toString().toDouble()
        val downPayment: Double = binding.paymentEdit.text.toString().toDouble()
        val loanPeriod: Double = binding.editLoan.text.toString().toDouble()
        val rate: Double = binding.editInterest.text.toString().toDouble()
        val carLoan:Double = carPrice - downPayment
        val interest:Double = carLoan*(rate/100.0)*loanPeriod
        val monthlyPay:Double = (carLoan+interest)/loanPeriod/12.toDouble()

        binding.carLoanEdit.text = "RM %.2f".format(carLoan)
        binding.interestEdit.text = "RM %.2f".format(interest)
        binding.monthlyEdit.text = "RM %.2f".format(monthlyPay)

        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
