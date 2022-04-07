package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.example.myapplication.databinding.ButtonCellBinding

interface IButtonClick {
    fun onClick(view: View?)
}

class CustomButton : FrameLayout, View.OnTouchListener, View.OnClickListener {

    private lateinit var binding: ButtonCellBinding
    private var isHideClearIcon = false
    var delegate: IButtonClick? = null

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init(context)
        parseAttributes(context, attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet, styleAttr: Int) : super(
        context,
        attributeSet,
        styleAttr
    ) {
        init(context)
        parseAttributes(context, attributeSet)
    }

    override fun onTouch(view: View?, event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {
            Log.d("EVENT", "TOUCHED DOWN")
            binding.textView.background = ContextCompat.getDrawable(context, R.color.dark_blue)
            binding.textView.setTextColor(ContextCompat.getColor(context, R.color.gray))
        }

        if (event?.action == MotionEvent.ACTION_UP){
            Log.d("EVENT", "TOUCHED UP")
            binding.textView.background = ContextCompat.getDrawable(context, R.color.blue)
            binding.textView.setTextColor(ContextCompat.getColor(context, R.color.white))
        }
        return false
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun init(context: Context) {
        val inputView = LayoutInflater.from(context).inflate(R.layout.button_cell, this, true)
        binding = ButtonCellBinding.bind(inputView.findViewById(R.id.buttonRoot))
        binding.textView.setOnClickListener(this)
        binding.textView.setOnTouchListener(this)
    }

    private fun parseAttributes(context: Context, attributeSet: AttributeSet) {
        val attributes = context.obtainStyledAttributes(attributeSet, R.styleable.CustomButton)
        val text = attributes.getString(R.styleable.CustomButton_text)
        binding.textView.text = text
        attributes.recycle()
    }

    override fun onClick(p0: View?) {
        delegate?.onClick(p0)
    }

    @SuppressLint("ResourceAsColor")
    fun startProgress() {
        binding.textView.background = ContextCompat.getDrawable(context, R.color.dark_blue)
        binding.textView.setTextColor(ContextCompat.getColor(context, R.color.gray))
        binding.textView.isClickable = !binding.textView.isClickable
        binding.pb.visibility = View.VISIBLE
    }

    @SuppressLint("ResourceAsColor")
    fun stopProgress() {
        binding.textView.background = ContextCompat.getDrawable(context, R.color.blue)
        binding.textView.setTextColor(ContextCompat.getColor(context, R.color.white))
        binding.textView.isClickable = !binding.textView.isClickable
        binding.pb.visibility = View.GONE
    }
}