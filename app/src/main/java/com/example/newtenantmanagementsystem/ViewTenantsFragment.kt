package com.example.newtenantmanagementsystem

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.newtenantmanagementsystem.databinding.FragmentViewTenantsBinding

class ViewTenantsFragment : Fragment() {

    private var _binding: FragmentViewTenantsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewTenantsBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve the newly added tenant using Safe Args
        val tenant = ViewTenantsFragmentArgs
            .fromBundle(requireArguments())
            .newTenant

        if (tenant != null) {
            binding.newTenantGroup.visibility = View.VISIBLE
            binding.tenant = tenant

            binding.callTenantButton.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_DIAL,
                    Uri.parse("tel:${tenant.phone}")
                )
                startActivity(intent)
            }
        } else {
            binding.newTenantGroup.visibility = View.GONE
        }

        // Display all tenants
        val tenants = TenantRepository.tenants

        binding.allTenantsText.text = if (tenants.isEmpty()) {
            "No tenants added yet."
        } else {
            tenants.joinToString(separator = "\n\n") { savedTenant ->
                savedTenant.summary()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}