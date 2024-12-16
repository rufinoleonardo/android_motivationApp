package com.rufino.motivation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.rufino.motivation.infra.AuthService
import com.rufino.motivation.infra.MotivationConstants
import com.rufino.motivation.R
import com.rufino.motivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()

        binding = ActivityUserBinding.inflate(layoutInflater)

        setContentView(binding.root)

        checkUser()

        binding.buttonSave.setOnClickListener(this)

        supportActionBar?.hide()
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.button_save) {
            handleSave()
        }
    }

    private fun handleSave(){
        val name = binding.editName.text.toString()
        if (name != "") {
            AuthService(this).setOnStorage(MotivationConstants.KEY.USER_NAME, name)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, R.string.validation_mandatory_name, Toast.LENGTH_LONG).show()

        }
    }

    private fun checkUser(){
        val name = AuthService(this).getOnStorage(MotivationConstants.KEY.USER_NAME)
        if(name != ""){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}