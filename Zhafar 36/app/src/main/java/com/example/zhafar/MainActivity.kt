package com.example.zhafar

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etNama: EditText
    private lateinit var etAlamat: EditText
    private lateinit var etTanggalLahir: EditText
    private lateinit var rgJenisKelamin: RadioGroup
    private lateinit var spinnerAgama: Spinner
    private lateinit var btnSimpan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Menginisialisasi komponen UI
        etNama = findViewById(R.id.et_nama)
        etAlamat = findViewById(R.id.et_alamat)
        etTanggalLahir = findViewById(R.id.et_tanggal_lahir)
        rgJenisKelamin = findViewById(R.id.rg_jenis_kelamin)
        spinnerAgama = findViewById(R.id.spinner_agama)
        btnSimpan = findViewById(R.id.btn_simpan)

        // Menambahkan listener pada tombol
        btnSimpan.setOnClickListener {
            if (validateInput()) {
                tampilkanBiodata()
            }
        }
    }

    // Fungsi untuk validasi input
    private fun validateInput(): Boolean {
        if (etNama.text.isNullOrEmpty() || etAlamat.text.isNullOrEmpty() || etTanggalLahir.text.isNullOrEmpty()) {
            Toast.makeText(this, "Semua field harus diisi!", Toast.LENGTH_SHORT).show()
            return false
        }

        if (rgJenisKelamin.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Jenis kelamin harus dipilih!", Toast.LENGTH_SHORT).show()
            return false
        }

        if (spinnerAgama.selectedItem.toString() == "Pilih Agama") {
            Toast.makeText(this, "Agama harus dipilih!", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    // Fungsi untuk menampilkan hasil biodata dalam AlertDialog
    private fun tampilkanBiodata() {
        val nama = etNama.text.toString()
        val alamat = etAlamat.text.toString()
        val tanggalLahir = etTanggalLahir.text.toString()

        val jenisKelaminId = rgJenisKelamin.checkedRadioButtonId
        val jenisKelamin = if (jenisKelaminId == R.id.rb_laki_laki) "Laki-laki" else "Perempuan"

        val agama = spinnerAgama.selectedItem.toString()

        val biodataText = "Nama: $nama\n" +
                "Alamat: $alamat\n" +
                "Tanggal Lahir: $tanggalLahir\n" +
                "Jenis Kelamin: $jenisKelamin\n" +
                "Agama: $agama"

        AlertDialog.Builder(this)
            .setTitle("Hasil Biodata")
            .setMessage(biodataText)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}