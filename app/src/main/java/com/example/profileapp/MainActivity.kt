package com.example.profileapp

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var profileImageView: ImageView
    private lateinit var userNameTextView: TextView
    private lateinit var editProfileButton: Button
    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            profileImageView.setImageURI(uri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        profileImageView = findViewById(R.id.profileImage)
        userNameTextView = findViewById(R.id.userName)
        editProfileButton = findViewById(R.id.editProfileButton)

        // Mengubah Foto Profil
        profileImageView.setOnClickListener {
            pickImage.launch("image/*") // Memilih gambar dari galeri
        }

        // Mengedit Nama Pengguna
        editProfileButton.setOnClickListener {
            showEditNameDialog()
        }
    }

    private fun showEditNameDialog() {
        val currentName = userNameTextView.text.toString()
        val editText = EditText(this)
        editText.setText(currentName)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Edit Nama")
            .setView(editText)
            .setPositiveButton("Simpan") { _, _ ->
                val newName = editText.text.toString()
                userNameTextView.text = newName
            }
            .setNegativeButton("Batal", null)
            .create()

        dialog.show()
    }
}
