package com.delet_dis.neumorphismcalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.roundToLong


class MainActivity : AppCompatActivity() {

    private var operation: Operation = Operation.EMPTY
    private var firstProcessingNumber = 0.0
    private var secondProcessingNumber = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initListeners()

    }

    private fun equalsButtonOnclick() {
        try {
            secondProcessingNumber =
                calculatorDisplayNonMock.text.toString().replace(',', '.').toDouble()
            calculatorDisplayNonMock.text =
                if ((floor(calculateExpression()) == ceil(calculateExpression())))
                    calculateExpression()
                        .toString().replace(".0", "")
                else
                    calculateExpression().toString()
                        .replace('.', ',')
            operation = Operation.EMPTY
        } catch (e: NumberFormatException) {
            clearDisplay()
        }
    }

    private fun clearDisplay() {
        calculatorDisplayNonMock.text = ""
        operation = Operation.EMPTY
        firstProcessingNumber = 0.0
        secondProcessingNumber = 0.0
    }


    private fun calculateExpression(): Double {
        return when (operation) {
            Operation.DIVIDE -> (firstProcessingNumber / secondProcessingNumber * 100000000).roundToLong()
                .toDouble() / 100000000
            Operation.MULTIPLY -> (firstProcessingNumber * secondProcessingNumber * 100000000).roundToLong()
                .toDouble() / 100000000
            Operation.MINUS -> ((firstProcessingNumber - secondProcessingNumber) * 100000000).roundToLong()
                .toDouble() / 100000000
            Operation.PLUS -> ((firstProcessingNumber + secondProcessingNumber) * 100000000).roundToLong()
                .toDouble() / 100000000
            Operation.PERCENT -> (firstProcessingNumber / 100 * secondProcessingNumber * 100000000).roundToLong()
                .toDouble() / 100000000
            else -> firstProcessingNumber
        }
    }

    private fun isAvailableToOperate(operation: Operation) {
        if (calculatorDisplayNonMock.text.toString()
                .isNotEmpty() && calculatorDisplayNonMock.text.toString() != "-"
        ) {
            onClickOperation(operation)
        }
    }

    private fun onClickOperation(processingOperation: Operation) {

        if (operation == Operation.EMPTY) {
            if (calculatorDisplayNonMock.text.toString().isNotEmpty()) {
                firstProcessingNumber =
                    calculatorDisplayNonMock.text.toString().replace(',', '.').toDouble()
                calculatorDisplayNonMock.text = ""
                operation = processingOperation
            }
        }
    }

    private fun initListeners() {
        val group = groupOfNumbers
        val refIds = group.referencedIds
        for (id in refIds) {
            findViewById<View>(id).setOnClickListener {
                calculatorDisplayNonMock.text =
                    "${calculatorDisplayNonMock.text.toString()}${(it as? Button)?.text.toString()}"
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
                    "${calculatorDisplayNonMock.text.toString()},"
        }

        divideButton.setOnClickListener {
            isAvailableToOperate(Operation.DIVIDE)
        }

        multiplyButton.setOnClickListener {
            isAvailableToOperate(Operation.MULTIPLY)

        }

        minusButton.setOnClickListener {
            val displayAsString = calculatorDisplayNonMock.text.toString()
            try {
                if (displayAsString.isNotEmpty()) {
                    onClickOperation(Operation.MINUS)
                } else if (displayAsString.isEmpty() && displayAsString != "-"
                ) {
                    calculatorDisplayNonMock.text =
                        "${calculatorDisplayNonMock.text.toString()}-"
                }
            } catch (e: java.lang.NumberFormatException) {
                clearDisplay()
            }


        }

        plusButton.setOnClickListener {
            isAvailableToOperate(Operation.PLUS)

        }

        percentButton.setOnClickListener {
            isAvailableToOperate(Operation.PERCENT)

        }

        plusAndMinusButton.setOnClickListener {
            if (calculatorDisplayNonMock.text.toString()
                    .isNotEmpty() && calculatorDisplayNonMock.text.toString() != "-"
            ) {
                firstProcessingNumber =
                    +calculatorDisplayNonMock.text.toString().replace(',', '.').toDouble() * -1
                calculatorDisplayNonMock.text =
                    if ((floor(firstProcessingNumber) == ceil(firstProcessingNumber)))
                        firstProcessingNumber
                            .toString().replace(".0", "")
                    else
                        firstProcessingNumber.toString()
                            .replace('.', ',')
            }
        }

        equalsButton.setOnClickListener {
            equalsButtonOnclick()
        }
    }
}
