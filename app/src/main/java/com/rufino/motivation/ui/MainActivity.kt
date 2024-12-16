package com.rufino.motivation.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.rufino.motivation.infra.AuthService
import com.rufino.motivation.infra.MotivationConstants
import com.rufino.motivation.R
import com.rufino.motivation.data.Mock
import com.rufino.motivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var categoryId = MotivationConstants.FILTER.ALL
    private val mock: Mock = Mock();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        //enableEdgeToEdge()

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

       handleUserName()

        binding.buttonNewPhrase.setOnClickListener(this)
        binding.icAllMessages.setOnClickListener(this)
        binding.icSmile.setOnClickListener(this)
        binding.icSunny.setOnClickListener(this)

        supportActionBar?.hide()
        onClick(binding.icAllMessages)
    }

    override fun onClick(view: View?) {


        if(view?.id == R.id.button_new_phrase){
            Toast.makeText(this, "Botão clicado", Toast.LENGTH_SHORT).show()
        } else if(view?.id in arrayOf(R.id.ic_all_messages, R.id.ic_smile, R.id.ic_sunny)){
            handleFilter(view?.id as Int)
        }
    }

    private fun handleUserName (){
        val name = AuthService(this).getOnStorage(MotivationConstants.KEY.USER_NAME)
        binding.textUserWelcome.text = "Olá, " + name
    }

    private fun handleFilter(id: Int){
        binding.icAllMessages.setColorFilter(getColor(R.color.dark_purple))
        binding.icSmile.setColorFilter(getColor(R.color.dark_purple))
        binding.icSunny.setColorFilter(getColor(R.color.dark_purple))

        when(id){
            R.id.ic_all_messages -> {
                binding.icAllMessages.setColorFilter(getColor(R.color.white))
                categoryId = MotivationConstants.FILTER.ALL
                handleGetPhrase()
            }
            R.id.ic_smile -> {
                binding.icSmile.setColorFilter(getColor(R.color.white))
                categoryId = MotivationConstants.FILTER.SMILE
                handleGetPhrase()
            }
            R.id.ic_sunny -> {
                binding.icSunny.setColorFilter(getColor(R.color.white))
                categoryId = MotivationConstants.FILTER.SUNNY
                handleGetPhrase()
            }
        }
    }

    private fun handleGetPhrase(){
        binding.textPhrase.text = mock.getPhrase(categoryId)
    }
}