package com.alexsandro.descontoparceirosur

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ResultadoActivity : AppCompatActivity() {

    private lateinit var textMateriais: TextView
    private lateinit var textFrete: TextView
    private lateinit var textTotal: TextView
    private lateinit var textResultado: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        inicializarComponentesInterface()

        val bundle = intent.extras

        val materiais = bundle?.getString("materiais")
        textMateriais.text = materiais

        val frete = bundle?.getString("frete")
        textFrete.text = frete

        val total = bundle?.getDouble("total")
        textTotal.text = "Valor Total: R$$total"




    }

    private fun inicializarComponentesInterface(){

        textMateriais = findViewById( R.id.text_materiais )
        textFrete = findViewById( R.id.text_frete )
        textTotal = findViewById( R.id.text_total )
        textResultado = findViewById( R.id.text_resultado )

    }


}