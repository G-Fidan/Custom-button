package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        binding.btn1.delegate = object: IButtonClick {
            override fun onClick(view: View?) {
                binding.btn1.startProgress()
                Handler(mainLooper).postDelayed({
                    runOnUiThread {
                        binding.btn1.stopProgress()
                    }
                }, 4000)
            }
        }
        binding.btn2.delegate = object: IButtonClick {
            override fun onClick(view: View?) {
                binding.btn2.startProgress()
                Handler(mainLooper).postDelayed({
                    runOnUiThread {
                        binding.btn2.stopProgress()
                    }
                }, 4000)
            }
        }
        binding.btn3.delegate = object: IButtonClick {
            override fun onClick(view: View?) {
                binding.btn3.startProgress()
                Handler(mainLooper).postDelayed({
                    runOnUiThread {
                        binding.btn3.stopProgress()
                    }
                }, 4000)
            }
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}