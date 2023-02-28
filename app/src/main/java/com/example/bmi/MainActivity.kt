package com.example.bmi

import android.app.Activity
import android.icu.util.Output
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.bmi.databinding.ActivityMainBinding
import java.sql.ResultSet

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var isClear: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnCalculate.setOnClickListener(this)
        if (isClear){
            isClear = false
            binding.btnCalculate.setText("CALCULATE")

        }
    }


    override fun onClick(view: View) {
        when (view?.id) {
            R.id.btn_calculate -> {
                if (isClear){
                    isClear = false
                    binding.btnCalculate.text = "Calculate"
                    binding.BMI.setText("")
                    binding.Output.setText("")
                    binding.weight.text.clear()
                    binding.height.text.clear()
                    Toast.makeText(this, "Clear karo", Toast.LENGTH_SHORT).show()
                }else{

                    //Toast.makeText(this@MainActivity, "hello", Toast.LENGTH_LONG).show()

                    // Check if the height EditText and Weight EditText are not empty
                    if (binding.height.text.toString().isNotEmpty() && binding.weight.text.toString().isNotEmpty()) {
                        if (!isClear){
                            // initialize the variable
                            isClear = true
                            binding.btnCalculate.setText("Clear")
                            val height = (binding.height.text.toString()).toDouble()
                            val weight = (binding.weight.text.toString()).toDouble()


                            val Height_in_metre = height.toFloat() / 100
                            val BMI = weight.toFloat() / (Height_in_metre * Height_in_metre)



                            binding.BMI.text = "your bmi  is:-"
                            binding.BMI.text = "${BMI}"
                            // update the status text as per the bmi conditions
                            if (BMI < 18.5) {
                                // Toast.makeText(this@MainActivity, R.string.under_weight, Toast.LENGTH_LONG).show()
                                binding.Output.text = resources.getString(R.string.under_weight)

                            } else if (BMI >= 18.5 && BMI < 24.9) {

                                // Toast.makeText(this@MainActivity, R.string.Healthy, Toast.LENGTH_LONG).show()
                                binding.Output.text = resources.getString(R.string.Healthy)
                            } else if (BMI >= 24.9 && BMI < 30) {
                                // Toast.makeText(this@MainActivity, R.string.over_weight, Toast.LENGTH_LONG).show()
                                binding.Output.text = resources.getString(R.string.over_weight)
                            } else {
                                //Toast.makeText(this@MainActivity, R.string.Suffering_from_Obesity, Toast.LENGTH_LONG).show()
                                binding.Output.text = resources.getString(R.string.Suffering_from_Obesity)

                            }
                        }

                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "please enter height & weight ",
                            Toast.LENGTH_LONG
                        ).show()
                    }


                }


            }

        }
    }

    override fun onResume() {
        super.onResume()
    }


}





