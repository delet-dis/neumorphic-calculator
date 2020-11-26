package com.delet_dis.neumorphismcalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.roundToLong

enum class Operation {
    DIVIDE,
    MULTIPLY,
    PERCENT,
    MINUS,
    PLUS
}


class MainActivity : AppCompatActivity() {

    private var operation = ""
    private var val1 = 0.0
    private var val2 = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val group = groupOfNumbers
        val refIds = group.referencedIds
        for (id in refIds) {
            findViewById<View>(id).setOnClickListener {
                calculator_display_non_mock.text =
                    calculator_display_non_mock.text.toString() + (it as? Button)?.text.toString()
            }
        }



        clearDisplay()


        ac_button.setOnClickListener {
            clearDisplay()
        }


        comma_button.setOnClickListener {
            if (calculator_display_non_mock.text.toString()
                    .lastIndexOf(",") != calculator_display_non_mock.text.toString().length - 1
            )
                calculator_display_non_mock.text =
                    calculator_display_non_mock.text.toString() + ","
        }

        divide_button.setOnClickListener {
            if (isAvailableToOperate()) {
                onClickOperation(Operation.DIVIDE.toString())
            }
        }

        multiply_button.setOnClickListener {
            if (isAvailableToOperate()) {
                onClickOperation(Operation.MULTIPLY.toString())
            }
        }

        minus_button.setOnClickListener {
            try {
                if (calculator_display_non_mock.text.toString().isNotEmpty()) {
                    onClickOperation(Operation.MINUS.toString())
                } else if (calculator_display_non_mock.text.toString()
                        .isEmpty() && calculator_display_non_mock.text.toString() != "-" &&
                    calculator_display_non_mock.text.toString().chars()
                        .filter { ch -> ch.toChar() == 'e' }.count() != 2.toLong()
                ) {
                    calculator_display_non_mock.text =
                        calculator_display_non_mock.text.toString() + "-"
                }
            } catch (e: java.lang.NumberFormatException) {
                clearDisplay()
            }


        }

        plus_button.setOnClickListener {
            if (isAvailableToOperate()) {
                onClickOperation(Operation.PLUS.toString())
            }
        }

        percent_button.setOnClickListener {
            if (isAvailableToOperate()) {
                onClickOperation(Operation.PERCENT.toString())
            }

        }

        plus_and_minus_button.setOnClickListener {
            if (isAvailableToOperate()) {
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
            "DIVIDE" -> (val1 / val2 * 100000000).roundToLong().toDouble() / 100000000
            "MULTIPLY" -> (val1 * val2 * 100000000).roundToLong().toDouble() / 100000000
            "MINUS" -> ((val1 - val2) * 100000000).roundToLong().toDouble() / 100000000
            "PLUS" -> ((val1 + val2) * 100000000).roundToLong().toDouble() / 100000000
            "PERCENT" -> (val1 / 100 * val2 * 100000000).roundToLong().toDouble() / 100000000
            else -> val1
        }
    }

    private fun isAvailableToOperate(): Boolean {
        return calculator_display_non_mock.text.toString()
            .isNotEmpty() && calculator_display_non_mock.text.toString() != "-"
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
                        .replace(',', '.').toDouble()
            calculator_display_non_mock.text = ""
        }


    }

}
