package com.franz.acv3_bmi_calculator

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {

    private lateinit var tvBMIResult: TextView
    private lateinit var tvCategory: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btnRecalculate: Button
    private lateinit var btnBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_result)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponents()
        initListeners()
        initUI()
    }

    private fun initComponents() {
        tvBMIResult = findViewById(R.id.tvBMIResult)
        tvCategory = findViewById(R.id.tvCategory)
        tvDescription = findViewById(R.id.tvDescription)
        btnRecalculate = findViewById(R.id.btnRecalculate)
        btnBack = findViewById(R.id.btnBack)
    }

    private fun initListeners() {
        btnRecalculate.setOnClickListener { finish() }
        btnBack.setOnClickListener { finish() }
    }

    private fun initUI() {
        val bmi = intent.getFloatExtra("EXTRA_BMI", -1.0f)

        if (bmi != -1.0f) {
            tvBMIResult.text = String.format("%.2f", bmi)

            when {
                bmi < 18.5 -> {
                    tvCategory.text = getString(R.string.underweight)
                    tvCategory.setTextColor(ContextCompat.getColor(this, R.color.bmi_yellow))
                    tvDescription.text = getString(R.string.desc_underweight)
                }
                bmi < 24.9 -> {
                    tvCategory.text = getString(R.string.normal)
                    tvCategory.setTextColor(ContextCompat.getColor(this, R.color.bmi_green))
                    tvDescription.text = getString(R.string.desc_normal)
                }
                bmi < 29.9 -> {
                    tvCategory.text = getString(R.string.overweight)
                    tvCategory.setTextColor(ContextCompat.getColor(this, R.color.bmi_orange))
                    tvDescription.text = getString(R.string.desc_overweight)
                }
                else -> {
                    tvCategory.text = getString(R.string.obese)
                    tvCategory.setTextColor(ContextCompat.getColor(this, R.color.bmi_red))
                    tvDescription.text = getString(R.string.desc_obese)
                }
            }
        }
    }
}