package com.delet_dis.neumorphismcalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.ceil
import kotlin.math.floor

var operation = "";
var val1 = 0.0;
var val2 = 0.0;

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clearDisplay()



        ac_button.setOnClickListener {
            clearDisplay()
        }

        zero_button.setOnClickListener {
            calculator_display_non_mock.text =
                calculator_display_non_mock.text.toString() + "0"
        }

        one_button.setOnClickListener {
            calculator_display_non_mock.text =
                calculator_display_non_mock.text.toString() + "1"
        }
        two_button.setOnClickListener {
            calculator_display_non_mock.text =
                calculator_display_non_mock.text.toString() + "2"
        }
        three_button.setOnClickListener {
            calculator_display_non_mock.text =
                calculator_display_non_mock.text.toString() + "3"
        }
        four_button.setOnClickListener {
            calculator_display_non_mock.text =
                calculator_display_non_mock.text.toString() + "4"
        }
        five_button.setOnClickListener {
            calculator_display_non_mock.text =
                calculator_display_non_mock.text.toString() + "5"
        }
        six_button.setOnClickListener {
            calculator_display_non_mock.text =
                calculator_display_non_mock.text.toString() + "6"
        }
        seven_button.setOnClickListener {
            calculator_display_non_mock.text =
                calculator_display_non_mock.text.toString() + "7"
        }
        eight_button.setOnClickListener {
            calculator_display_non_mock.text =
                calculator_display_non_mock.text.toString() + "8"
        }
        nine_button.setOnClickListener {
            calculator_display_non_mock.text =
                calculator_display_non_mock.text.toString() + "9"
        }

        comma_button.setOnClickListener {
            if (calculator_display_non_mock.text.toString()
                    .lastIndexOf(",") != calculator_display_non_mock.text.toString().length - 1
            )
                calculator_display_non_mock.text =
                    calculator_display_non_mock.text.toString() + ","
        }

        divide_button.setOnClickListener {
            onClickOperation("DIVIDE")

        }

        multiply_button.setOnClickListener {
            onClickOperation("MULTIPLY")
        }

        minus_button.setOnClickListener {
            if (calculator_display_non_mock.text.toString().isNotEmpty()) {
                onClickOperation("MINUS")
            } else if (calculator_display_non_mock.text.toString()
                    .isEmpty() && calculator_display_non_mock.text.toString() != "-" &&
                calculator_display_non_mock.text.toString().chars().filter { ch -> ch.toChar() == 'e' }.count()!=2.toLong()
            ) {
                calculator_display_non_mock.text =
                    calculator_display_non_mock.text.toString() + "-"
            }

        }

        plus_button.setOnClickListener {
            onClickOperation("PLUS")
        }

        percent_button.setOnClickListener {
            onClickOperation("PERCENT")

        }

        plus_and_minus_button.setOnClickListener {
            if (calculator_display_non_mock.text.toString().isNotEmpty()) {
                val1 =
                    +calculator_display_non_mock.text.toString().replace(',', '.').toDouble() * -1
                calculator_display_non_mock.text =
                    if ((floor(val1) == ceil(val1)))
                        val1
                            .toString().replace(".0", "")
                    else
                        val1.toString()
                            .replace('.', ',')
            }

        }

        equals_button.setOnClickListener {
            try {
                val2 = calculator_display_non_mock.text.toString().replace(',', '.').toDouble()
                calculator_display_non_mock.text =
                    if ((floor(calculateExpression()) == ceil(calculateExpression())))
                        calculateExpression()
                            .toString().replace(".0", "")
                    else
                        calculateExpression().toString()
                            .replace('.', ',')
            } catch (e: NumberFormatException) {
                clearDisplay()
            }
        }

    }

    private fun clearDisplay() {
        calculator_display_non_mock.text = ""
        operation = ""
        val1 = 0.0
        val2 = 0.0
    }


    private fun calculateExpression(): Double {
        return when (operation) {
            "DIVIDE" -> val1 / val2
            "MULTIPLY" -> val1 * val2
            "MINUS" -> val1 - val2
            "PLUS" -> val1 + val2
            "PERCENT" -> val1 / 100 * val2
            else -> val1
        }
    }

    private fun onClickOperation(processingOperation: String) {

        if (operation == "") {
            if (calculator_display_non_mock.text.toString().isNotEmpty()) {
                val1 = calculator_display_non_mock.text.toString().replace(',', '.').toDouble()
                calculator_display_non_mock.text = ""
                operation = processingOperation
            }
        } else {
            val2 = calculator_display_non_mock.text.toString().replace(',', '.').toDouble()
            val1 =
                if ((floor(calculateExpression()) == ceil(calculateExpression())))
                    calculateExpression()
                        .toString().replace(".0", "").toDouble()
                else
                    calculateExpression().toString()
                        .replace('.', ',').toDouble()
            calculator_display_non_mock.text = ""
        }


    }

}