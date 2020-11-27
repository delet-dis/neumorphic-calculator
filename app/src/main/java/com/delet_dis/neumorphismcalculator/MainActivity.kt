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
                calculatorDisplayNonMock.text =
                    calculatorDisplayNonMock.text.toString() + (it as? Button)?.text.toString()
            }
        }



        clearDisplay()


        acButton.setOnClickListener {
            clearDisplay()
        }


        commaButton.setOnClickListener {
            if (calculatorDisplayNonMock.text.toString()
                    .lastIndexOf(",") != calculatorDisplayNonMock.text.toString().length - 1
            )
                calculatorDisplayNonMock.text =
                    calculatorDisplayNonMock.text.toString() + ","
        }

        divideButton.setOnClickListener {
            if (isAvailableToOperate()) {
                onClickOperation(Operation.DIVIDE.toString())
            }
        }

        multiplyButton.setOnClickListener {
            if (isAvailableToOperate()) {
                onClickOperation(Operation.MULTIPLY.toString())
            }
        }

        minusButton.setOnClickListener {
            try {
                if (calculatorDisplayNonMock.text.toString().isNotEmpty()) {
                    onClickOperation(Operation.MINUS.toString())
                } else if (calculatorDisplayNonMock.text.toString()
                        .isEmpty() && calculatorDisplayNonMock.text.toString() != "-" &&
                    calculatorDisplayNonMock.text.toString().chars()
                        .filter { ch -> ch.toChar() == 'e' }.count() != 2.toLong()
                ) {
                    calculatorDisplayNonMock.text =
                        calculatorDisplayNonMock.text.toString() + "-"
                }
            } catch (e: java.lang.NumberFormatException) {
                clearDisplay()
            }


        }

        plusButton.setOnClickListener {
            if (isAvailableToOperate()) {
                onClickOperation(Operation.PLUS.toString())
            }
        }

        percentButton.setOnClickListener {
            if (isAvailableToOperate()) {
                onClickOperation(Operation.PERCENT.toString())
            }

        }

        plusAndMinusButton.setOnClickListener {
            if (isAvailableToOperate()) {
                val1 =
                    +calculatorDisplayNonMock.text.toString().replace(',', '.').toDouble() * -1
                calculatorDisplayNonMock.text =
                    if ((floor(val1) == ceil(val1)))
                        val1
                            .toString().replace(".0", "")
                    else
                        val1.toString()
                            .replace('.', ',')
            }
        }

        equalsButton.setOnClickListener {
            try {
                val2 = calculatorDisplayNonMock.text.toString().replace(',', '.').toDouble()
                calculatorDisplayNonMock.text =
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
        calculatorDisplayNonMock.text = ""
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
        return calculatorDisplayNonMock.text.toString()
            .isNotEmpty() && calculatorDisplayNonMock.text.toString() != "-"
    }

    private fun onClickOperation(processingOperation: String) {

        if (operation == "") {
            if (calculatorDisplayNonMock.text.toString().isNotEmpty()) {
                val1 = calculatorDisplayNonMock.text.toString().replace(',', '.').toDouble()
                calculatorDisplayNonMock.text = ""
                operation = processingOperation
            }
        } else {
            val2 = calculatorDisplayNonMock.text.toString().replace(',', '.').toDouble()
            val1 =
                if ((floor(calculateExpression()) == ceil(calculateExpression())))
                    calculateExpression()
                        .toString().replace(".0", "").toDouble()
                else
                    calculateExpression().toString()
                        .replace(',', '.').toDouble()
            calculatorDisplayNonMock.text = ""
        }


    }

}
