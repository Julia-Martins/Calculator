package com.example.calculatorapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.trimmedLength

@SuppressLint("WrongViewCast")
class MainActivity : AppCompatActivity() {
    private var txtNumResult: TextView? = null
    private var LastNumeric : Boolean = false
    private var LastDot : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtNumResult = findViewById(R.id.txtNum)
    }

// region Function onClickButton(Just in number's Buttons)

    // Comments: This function it'll show the text that you put on your button,
    //      and show in the TextView.
    fun onClickButton(view: View){
        txtNumResult?.append((view as Button).text)
        LastNumeric = true
        LastDot = false
    }

// endregion

// region Function onClear(Just in clear button)

    // Comments: This function it'll clear the scream, when u clicked this button
    fun onClear(view: View){
        txtNumResult?.text = ""
    }

// endregion

// region Function onDecimalPoint(Just in dot button)

    // Comments: This function it'll put a dot on you screen, but this is treating
    //      some cases that this isn't completely unnecessarily.
    fun onDecimalPoint(view: View){
        if(LastNumeric && !LastDot){
            txtNumResult?.append(".")
            LastNumeric = false
            LastDot = true
        }
    }
// endregion

// region Function onOperator (Just in the operator's buttons)

    // Comments: This function it'll put the operators just a once and never after a dot
    fun onOperator(view: View){
        txtNumResult?.text?.let {
            if(LastNumeric && !isOperatorAdded(it.toString())){
                txtNumResult?.append((view as Button).text)
                LastNumeric = false
                LastDot = false
            }
        }
    }
// endregion

// region onEqual() (Just for the equal's button)
    fun onEqual(view: View){
        if(LastNumeric){
            var strValue = txtNumResult?.text.toString()
            var strPrefix = ""

            try{
                if(strValue.startsWith("-")){
                    strPrefix = "-"
                    strValue = strValue.substring(1)
                }

                if(strValue.contains("-")){
                    val splitValue = strValue.split("-")
                    var firstNum = splitValue[0]
                    var secondNum = splitValue[1]

                    if(strPrefix.isNotEmpty()){
                        firstNum = strPrefix + firstNum
                    }

                    txtNumResult?.text = removeZeroAfterDot((firstNum.toDouble() - secondNum.toDouble()).toString())

                }else if(strValue.contains("+")){
                    val splitValue = strValue.split("+")
                    var firstNum = splitValue[0]
                    var secondNum = splitValue[1]

                    if(strPrefix.isNotEmpty()){
                        firstNum = strPrefix + firstNum
                    }

                    txtNumResult?.text = removeZeroAfterDot((firstNum.toDouble() + secondNum.toDouble()).toString())

                }else if(strValue.contains("/")){
                    val splitValue = strValue.split("/")
                    var firstNum = splitValue[0]
                    var secondNum = splitValue[1]

                    if(strPrefix.isNotEmpty()){
                        firstNum = strPrefix + firstNum
                    }

                    txtNumResult?.text = removeZeroAfterDot((firstNum.toDouble() / secondNum.toDouble()).toString())

                }else if(strValue.contains("*")){
                    val splitValue = strValue.split("*")
                    var firstNum = splitValue[0]
                    var secondNum = splitValue[1]

                    if(strPrefix.isNotEmpty()){
                        firstNum = strPrefix + firstNum
                    }

                    txtNumResult?.text = removeZeroAfterDot((firstNum.toDouble() * secondNum.toDouble()).toString())
                }

            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }
// endregion

// region Function removeZeroAfterDot
    private fun removeZeroAfterDot(result: String) : String{
        var value = result

        if(result.contains(".0")){
            value = result.substring(0, result.length - 2)
        }
        return value
    }

// endregion

// region Function isOperatorAdded(Used for complete the function upstairs)

    // Comments: This function will make you put once time the operator, but need to add somethings, and threat some exceptions
    private fun isOperatorAdded(value: String): Boolean {

        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/") ||
            value.contains("+") ||
            value.contains("*") ||
            value.contains("-")
        }
    }
// endregion
}
