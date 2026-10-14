package com.example.newtenantmanagementsystem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newtenantmanagementsystem.databinding.FragmentAddTenantBinding

class AddTenantFragment : Fragment() {

    private var _binding: FragmentAddTenantBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTenantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveTenantButton.setOnClickListener {
            val name = binding.tenantNameEditText.text.toString().trim()
            val phone = binding.phoneEditText.text.toString().trim()
            val rent = binding.rentEditText.text.toString().trim()

            var isValid = true

            if (name.isEmpty()) {
                binding.tenantNameEditText.error = "Enter tenant name"
                isValid = false
            }

            if (phone.isEmpty()) {
                binding.phoneEditText.error = "Enter phone number"
                isValid = false
            }

            if (rent.isEmpty()) {
                binding.rentEditText.error = "Enter rent amount"
                isValid = false
            } else if (rent.toDoubleOrNull() == null || rent.toDouble() < 0) {
                binding.rentEditText.error = "Enter a valid rent amount"
                isValid = false
            }

            if (!isValid) return@setOnClickListener

            val tenant = Tenant(name, phone, rent)

            TenantRepository.addTenant(tenant)

            Toast.makeText(
                requireContext(),
                "Tenant saved successfully",
                Toast.LENGTH_SHORT
            ).show()

            val action =
                AddTenantFragmentDirections
                    .actionAddTenantFragmentToViewTenantsFragment(tenant)

            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}