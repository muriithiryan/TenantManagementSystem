package com.example.newtenantmanagementsystem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newtenantmanagementsystem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val name = binding.tenantNameEditText.text.toString()
            val phone = binding.phoneEditText.text.toString()
            val rent = binding.rentEditText.text.toString()

            if (name.isBlank()) {
                binding.tenantNameEditText.error = "Tenant name is required"
                return@setOnClickListener
            }

            val tenant = Tenant(name, phone, rent)

            binding.tenant = tenant
            binding.tenantNameEditText.text.clear()
            binding.phoneEditText.text.clear()
            binding.rentEditText.text.clear()
        }
    }
}