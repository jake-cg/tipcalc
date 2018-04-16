package edu.washington.jcg25.tipcalc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var amount: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button0: Button = findViewById<Button>(R.id.button0)
        val button1: Button = findViewById<Button>(R.id.button1)
        val button2: Button = findViewById<Button>(R.id.button2)
        val button3: Button = findViewById<Button>(R.id.button3)
        val button4: Button = findViewById<Button>(R.id.button4)
        val button5: Button = findViewById<Button>(R.id.button5)
        val button6: Button = findViewById<Button>(R.id.button6)
        val button7: Button = findViewById<Button>(R.id.button7)
        val button8: Button = findViewById<Button>(R.id.button8)
        val button9: Button = findViewById<Button>(R.id.button9)
        val button00: Button = findViewById<Button>(R.id.button00)
        val buttonDelete: Button = findViewById<Button>(R.id.buttonDelete)
        val buttonTip: Button = findViewById<Button>(R.id.buttonCalc)
        val radioRate: RadioGroup = findViewById<RadioGroup>(R.id.radioGroupRate)

        buttonTip.isEnabled = false;
        buttonDelete.isEnabled = false;

        button0.setOnClickListener { view -> numberAdded(0) }

        button1.setOnClickListener { view -> numberAdded(1) }

        button2.setOnClickListener { view -> numberAdded(2) }

        button3.setOnClickListener { view -> numberAdded(3) }

        button4.setOnClickListener { view -> numberAdded(4) }

        button5.setOnClickListener { view -> numberAdded(5) }

        button6.setOnClickListener { view -> numberAdded(6) }

        button7.setOnClickListener { view -> numberAdded(7) }

        button8.setOnClickListener { view -> numberAdded(8) }

        button9.setOnClickListener { view -> numberAdded(9) }

        button00.setOnClickListener { view ->
            numberAdded(0)
            numberAdded(0)
        }

        buttonTip.setOnClickListener { view ->

            val rateRaw = findViewById<RadioButton>(radioRate.checkedRadioButtonId).text
            val rate = rateRaw.substring(0, 2)
            val tip : String = "%.2f".format(amount.toDouble() * rate.toDouble() / 10000.0)
            val text = "A $rateRaw tip is $$tip"

            val toast = Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT)
            toast.show();
        }

        buttonDelete.setOnClickListener {view ->
            numberAdded(-1)
        }
    }

    private fun numberAdded(num: Int) {
        if(num < 0){
            amount = amount.substring(0, amount.length - 1)
        }
        if(amount.length < 7){
            val amountText: TextView = findViewById<TextView>(R.id.textViewAmount)
            val buttonTip: Button = findViewById<Button>(R.id.buttonCalc)
            val buttonDelete: Button = findViewById<Button>(R.id.buttonDelete)

            if(num >= 0){
                amount += num.toString()
            }

            amount += "."
            amount = amount.trim('0')
            amount = amount.substring(0, amount.length - 1)
            if(amount.isNotEmpty()) {

                buttonDelete.isEnabled = true;
                buttonTip.isEnabled = true;
                 if(amount.length == 1){
                    amountText.text = "$0.0" + amount
                } else if(amount.length == 2){
                    amountText.text = "$0." + amount
                } else if(amount.length > 2 && amount.length < 6){
                    amountText.text = "$" + amount.substring(0, amount.length - 2) + "." + amount.substring(amount.length - 2)
                } else if(amount.length == 6){
                    amountText.text = "$" + amount.substring(0, 1) + "," +  amount.substring(1, amount.length - 2) + "." + amount.substring(amount.length - 2)
                } else if (amount.length == 7){
                    amountText.text = "$" + amount.substring(0, 2) + "," +  amount.substring(2, amount.length - 2) + "." + amount.substring(amount.length - 2)
                }
            } else {
                amountText.text = "$0.00"
                buttonTip.isEnabled = false;
                buttonDelete.isEnabled = false;
            }

        }
    }


}
