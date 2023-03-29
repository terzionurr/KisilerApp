package com.example.personApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kiileruygulamasi.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var kisilerListe : ArrayList<Kisiler>
    private lateinit var adapter:KisilerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title = "Kişiler Uygulaması"
        setSupportActionBar(toolbar)

        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this)

        kisilerListe = ArrayList()

        val k1 = Kisiler(1,"ahmet","9999")
        val k2 = Kisiler(2,"zeynep","6666")

        kisilerListe.add(k1)
        kisilerListe.add(k2)

        adapter = KisilerAdapter(this,kisilerListe)

        rv.adapter = adapter

        fab.setOnClickListener {

            alertGoster()

        }
    }

        fun alertGoster(){
            val tasarim = LayoutInflater.from(this).inflate(R.layout.alert_tasarim,null)

            val editTextAd = tasarim.findViewById(R.id.editTextAd) as EditText  // tasarım üzerindeki edittextlere erişmeyi sağlar
            val editTextTel = tasarim.findViewById(R.id.editTextTel) as EditText

            val ad = AlertDialog.Builder(this)

            ad.setTitle("Kişi Ekle")
            ad.setView(tasarim) // edittextleri alert üzerinde göstermemizi sağladı
            ad.setPositiveButton("Ekle"){ dialogInterface, i ->

                val kisiAd = editTextAd.text.toString().trim()
                val kisiTel = editTextTel.text.toString().trim()

                Toast.makeText(applicationContext,"$kisiAd - $kisiTel",Toast.LENGTH_SHORT).show()
            }
            ad.setNegativeButton("İptal"){ dialogInterface, i ->

                Toast.makeText(applicationContext,"İptal Tıklandı",Toast.LENGTH_SHORT).show()
            }
            ad.create().show()
        }
}