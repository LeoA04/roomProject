package com.paba.roomproject

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.paba.roomproject.database.daftarBelanja
import com.paba.roomproject.database.daftarBelanjaDB
import com.paba.roomproject.helper.DateHelper.getCurrentDate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class TambahDaftar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_daftar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var etItem = findViewById<EditText>(R.id.etItem)
        var etJumlah = findViewById<EditText>(R.id.etJumlah)
        var btnTambah = findViewById<Button>(R.id.btnTambah)
        var btnUpdate = findViewById<Button>(R.id.btnUpdate)

        var DB = daftarBelanjaDB.getDatabase(this)

        var tanggal = getCurrentDate()

        btnTambah.setOnClickListener {
            CoroutineScope(Dispatchers.IO).async {
                DB.fundaftarBelanjaDAO().insert(
                    daftarBelanja(
                        tanggal = tanggal,
                        item = etItem.text.toString(),
                        jumlah = etJumlah.text.toString()
                    )
                )
            }
        }
    }
}