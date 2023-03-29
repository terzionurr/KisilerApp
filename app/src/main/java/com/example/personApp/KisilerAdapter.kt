package com.example.personApp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.kiileruygulamasi.R
import com.google.android.material.snackbar.Snackbar

class KisilerAdapter(private val mContext:Context,private val kisilerListe:List<Kisiler>)  //adapter oluştururken bir context birde veri kümesi oluşturmamız gerekir
    : RecyclerView.Adapter<KisilerAdapter.CardTasarimTutucu>(){ // adapter ile tasarımımızı birleştirdik

    inner class CardTasarimTutucu(tasarim:View) : RecyclerView.ViewHolder(tasarim){
        var textViewKisiBilgi:TextView
        var imageViewNokta:ImageView

    init {
        textViewKisiBilgi = tasarim.findViewById(R.id.textViewKisiBilgi)
        imageViewNokta = tasarim.findViewById(R.id.imageViewNokta)
     }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.kisi_card_tasarim,parent,false)//yazılımsal ve görsel tasarımı birleştirir

       return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
            val kisi = kisilerListe.get(position) // position bize sırayla index numarasını verir ve erişmemizi sağlar
            holder.textViewKisiBilgi.text = "${kisi.kisi_ad} - ${kisi.kisi_tel} "

            holder.imageViewNokta.setOnClickListener {

                val popupMenu = PopupMenu(mContext,holder.imageViewNokta)
                popupMenu.menuInflater.inflate(R.menu.popup_menu,popupMenu.menu)

                popupMenu.setOnMenuItemClickListener { menuItem ->

                    when(menuItem.itemId){
                        R.id.action_sil ->{
                            Snackbar.make(holder.imageViewNokta,"${kisi.kisi_ad} silinsin mi ?",Snackbar.LENGTH_SHORT)
                                .setAction("EVET"){
                                }.show()
                            true
                        }
                        R.id.action_guncelle ->{
                            true
                        }
                        else -> false
                    }

                }

                popupMenu.show()




            }


    }

    override fun getItemCount(): Int { //veri kümemizin boyutunu sorar
        return kisilerListe.size
    }

}