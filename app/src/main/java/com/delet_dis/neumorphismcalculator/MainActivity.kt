package com.delet_dis.neumorphismcalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clearDisplay()

        calculator_display_non_mock.getText().toString() + "4"

        ac_button.setOnClickListener {
            clearDisplay()
        }

        one_button.setOnClickListener {
            calculator_display_non_mock.text =
                calculator_display_non_mock.getText().toString() + "1"
        }
        two_button.setOnClickListener {
            calculator_display_non_mock.text =
                calculator_display_non_mock.getText().toString() + "2"
        }
        three_button.setOnClickListener {
            calculator_display_non_mock.text =
                calculator_display_non_mock.getText().toString() + "3"
        }
        four_button.setOnClickListener {
            calculator_display_non_mock.text =
                calculator_display_non_mock.getText().toString() + "4"
        }
        five_button.setOnClickListener {
            calculator_display_non_mock.text =
                calculator_display_non_mock.getText().toString() + "5"
        }
        six_button.setOnClickListener {
            calculator_display_non_mock.text =
                calculator_display_non_mock.getText().toString() + "6"
        }
        seven_button.setOnClickListener {
            calculator_display_non_mock.text =
                calculator_display_non_mock.getText().toString() + "7"
        }
        eight_button.setOnClickListener {
            calculator_display_non_mock.text =
                calculator_display_non_mock.getText().toString() + "8"
        }
        nine_button.setOnClickListener {
            calculator_display_non_mock.text =
                calculator_display_non_mock.getText().toString() + "9"
        }


    }
  private fun clearDisplay() {
    calculator_display_non_mock.text = ""
  }
}