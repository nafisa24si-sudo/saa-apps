package com.example.saa_apps.pertemuan13

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.saa_apps.databinding.ActivityThirteenthBinding
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import java.text.SimpleDateFormat
import java.util.Locale

class ThirteenthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirteenthBinding
    private var imageUri: Uri? = null

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions[Manifest.permission.CAMERA] == true) {
            openCamera()
        } else {
            Toast.makeText(this, "Izin kamera ditolak", Toast.LENGTH_SHORT).show()
        }
    }

    private val takePictureLauncher = registerForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            binding.ivCameraResult.setImageURI(imageUri)
            Toast.makeText(this, "Foto berhasil disimpan", Toast.LENGTH_SHORT).show()
        }
    }

    private val scanLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val scanResult = result.data?.getStringExtra("SCAN_RESULT")
            binding.tvScanResult.text = "Hasil Scan: $scanResult"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirteenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Bina Desa"

        binding.btnTakePhoto.setOnClickListener {
            checkCameraPermission()
        }

        binding.btnGenerateQr.setOnClickListener {
            val text = binding.etQrInput.text.toString()
            if (text.isNotEmpty()) {
                generateQrCode(text)
            } else {
                Toast.makeText(this, "Masukkan teks terlebih dahulu", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnStartScanner.setOnClickListener {
            val intent = Intent(this, ScannerActivity::class.java)
            scanLauncher.launch(intent)
        }
    }

    private fun checkCameraPermission() {
        val permissions = mutableListOf(Manifest.permission.CAMERA)
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }

        if (permissions.all { ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED }) {
            openCamera()
        } else {
            requestPermissionLauncher.launch(permissions.toTypedArray())
        }
    }

    private fun openCamera() {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis())
        val name = "IMG_$timeStamp.jpg"

        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, name)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/BinaDesa")
            }
        }

        imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        imageUri?.let { takePictureLauncher.launch(it) }
    }

    private fun generateQrCode(text: String) {
        val size = 512
        val writer = MultiFormatWriter()
        try {
            val bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, size, size)
            val bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565)
            for (x in 0 until size) {
                for (y in 0 until size) {
                    bitmap.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }
            binding.ivQrResult.visibility = View.VISIBLE
            binding.ivQrResult.setImageBitmap(bitmap)
        } catch (e: WriterException) {
            e.printStackTrace()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}