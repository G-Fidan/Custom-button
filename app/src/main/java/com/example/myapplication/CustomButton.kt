package com.example.myapplication

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myapplication.databinding.ButtonCellBinding

interface IButtonClick {
    fun onClick(text: String?)
}

class CustomButton: ConstraintLayout, View.OnFocusChangeListener, View.OnClickListener {

    private lateinit var binding: ButtonCellBinding
    private var isHideClearIcon = false
    var delegate: IButtonClick? = null

    constructor(context: Context) : super(context) {
        if (!isInEditMode) {
            init(context)
        }
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        if (!isInEditMode) {
            init(context)
            parseAttributes(context, attributeSet)
        }
    }

    constructor(context: Context, attributeSet: AttributeSet, styleAttr: Int) : super(
        context,
        attributeSet,
        styleAttr
    ) {
        if (!isInEditMode) {
            init(context)
            parseAttributes(context, attributeSet)
        }
    }

    override fun onFocusChange(view: View?, focused: Boolean) {
        changeBorderAndBackground(focused)
    }

    private fun init(context: Context) {
        val inputView = LayoutInflater.from(context).inflate(R.layout.button_cell, this, true)
        binding = ButtonCellBinding.bind(inputView.findViewById(R.id.buttonRoot))
        binding.btn.onFocusChangeListener = this
        binding.btn.setOnClickListener(this)
    }

    private fun parseAttributes(context: Context, attributeSet: AttributeSet) {
        val attributes = context.obtainStyledAttributes(attributeSet, R.styleable.CustomButton)
        val hint = attributes.getString(R.styleable.CustomButton_hint)
        isHideClearIcon = attributes.getBoolean(R.styleable.CustomButton_hideClearIcon, false)
        binding.btn.hint = hint
        if (isHideClearIcon) {
            binding.btn.visibility = View.GONE
        }
        attributes.recycle()
    }

    private fun changeBorderAndBackground(isFocused: Boolean) {
        if (isFocused) {
            // show shadow
        } else {
            // hide shadow
        }
    }

    override fun onClick(p0: View?) {

    }

    private fun startAnimation() {

    }

    private fun stopAnimation() {

    }
}