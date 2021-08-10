package com.mystikcoder.datastore.presentation.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mystikcoder.datastore.R
import com.mystikcoder.datastore.presentation.viewmodel.DataViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var saveDataButton: Button
    private lateinit var clearDataButton: Button
    private lateinit var getDataButton: Button
    private lateinit var inputName: EditText
    private lateinit var inputAge: EditText

    private val viewModel by viewModels<DataViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        setClickListeners()
    }

    private fun setClickListeners() {
        saveDataButton.setOnClickListener {
            if (inputName.text.toString().isEmpty() || inputAge.text.toString().isEmpty()) {
                Toast.makeText(this, "Fill all the fields", Toast.LENGTH_SHORT).show()
            } else {

                val age: Int? = try {
                    Integer.parseInt(inputAge.text.toString())
                } catch (e: NumberFormatException) {
                    e.printStackTrace()
                    null
                }

                if (age == null){
                    Toast.makeText(this, "Enter Valid Age", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                viewModel.saveName(inputName.text.toString())
                viewModel.saveAge(age)
            }
        }

        clearDataButton.setOnClickListener {
            inputAge.setText("")
            inputName.setText("")
        }

        getDataButton.setOnClickListener {
            inputName.setText(viewModel.getName())
            inputAge.setText(viewModel.getAge().toString())
        }
    }

    private fun initViews() {
        saveDataButton = findViewById(R.id.buttonSave)
        clearDataButton = findViewById(R.id.buttonClear)
        getDataButton = findViewById(R.id.buttonGetData)
        inputName = findViewById(R.id.textName)
        inputAge = findViewById(R.id.textAge)
    }
}