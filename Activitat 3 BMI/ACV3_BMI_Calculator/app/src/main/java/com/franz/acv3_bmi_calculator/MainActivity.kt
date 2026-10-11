package com.franz.acv3_bmi_calculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private var isMaleSelected = true
    private var height = 170
    private var weight = 70
    private var age = 20

    private lateinit var cardMale: CardView
    private lateinit var cardFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var seekBarHeight: SeekBar
    private lateinit var tvWeight: TextView
    private lateinit var btnWeightMinus: ImageButton
    private lateinit var btnWeightPlus: ImageButton
    private lateinit var tvAge: TextView
    private lateinit var btnAgeMinus: ImageButton
    private lateinit var btnAgePlus: ImageButton
    private lateinit var btnCalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponents()
        initListeners()
        updateGenderSelection()
    }

    private fun initComponents() {
        cardMale = findViewById(R.id.cardMale)
        cardFemale = findViewById(R.id.cardFemale)
        tvHeight = findViewById(R.id.tvHeight)
        seekBarHeight = findViewById(R.id.seekBarHeight)
        tvWeight = findViewById(R.id.tvWeight)
        btnWeightMinus = findViewById(R.id.btnWeightMinus)
        btnWeightPlus = findViewById(R.id.btnWeightPlus)
        tvAge = findViewById(R.id.tvAge)
        btnAgeMinus = findViewById(R.id.btnAgeMinus)
        btnAgePlus = findViewById(R.id.btnAgePlus)
        btnCalculate = findViewById(R.id.btnCalculate)
    }

    private fun initListeners() {
        cardMale.setOnClickListener {
            isMaleSelected = true
            updateGenderSelection()
        }

        cardFemale.setOnClickListener {
            isMaleSelected = false
            updateGenderSelection()
        }

        seekBarHeight.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                height = progress
                tvHeight.text = height.toString()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        btnWeightMinus.setOnClickListener {
            if (weight > 1) {
                weight -= 1
                tvWeight.text = weight.toString()
            }
        }

        btnWeightPlus.setOnClickListener {
            weight += 1
            tvWeight.text = weight.toString()
        }

        btnAgeMinus.setOnClickListener {
            if (age > 1) {
                age -= 1
                tvAge.text = age.toString()
            }
        }

        btnAgePlus.setOnClickListener {
            if (age < 120) {
                age += 1
                tvAge.text = age.toString()
            }
        }

        btnCalculate.setOnClickListener {
            val heightM = height.toFloat() / 100
            val bmi = weight.toFloat() / (heightM * heightM)
            navigateToResult(bmi)
        }
    }

    private fun updateGenderSelection() {
        val colorSelected = ContextCompat.getColor(this, R.color.card_selected)
        val colorNormal = ContextCompat.getColor(this, R.color.card_normal)

        if (isMaleSelected) {
            cardMale.setCardBackgroundColor(colorSelected)
            cardFemale.setCardBackgroundColor(colorNormal)
        } else {
            cardMale.setCardBackgroundColor(colorNormal)
            cardFemale.setCardBackgroundColor(colorSelected)
        }
    }

    private fun navigateToResult(bmi: Float) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("EXTRA_BMI", bmi)
        startActivity(intent)
    }
}