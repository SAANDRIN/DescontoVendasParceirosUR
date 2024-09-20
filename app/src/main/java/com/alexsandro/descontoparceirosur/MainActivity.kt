package com.alexsandro.descontoparceirosur

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout


class MainActivity : AppCompatActivity() {

    private lateinit var textInputMateriais: TextInputLayout
    private lateinit var editMateriais: EditText

    private lateinit var textInputFrete: TextInputLayout
    private lateinit var editFrete: EditText

    private lateinit var btnCalcular: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inicializarComponentesInterface()

        val intent = Intent(this, ResultadoActivity::class.java)

        btnCalcular.setOnClickListener {

            val materiais = editMateriais.text.toString()
            val frete = editFrete.text.toString()

            // -------------------------------------------------------

            val validacao = validarCampos(materiais, frete)

            if (validacao) {

                val valorTotal = calcularTotal(materiais, frete)
                intent.putExtra("total", valorTotal)

                val desconto = if (valorTotal < 800.00){
                                     0.0
                                } else if (valorTotal in 800.00 .. 1300.00){
                                     0.5
                                } else {
                                    1.0
                                }

                intent.putExtra(
                    "desconto",

                    "- Desconto aplicado: ${(desconto * 100).toInt()}%\n- ${(desconto * 100).toInt()}% do Frete: R$${(frete.toDouble()) * desconto}\n- Total com desconto: R$${valorTotal - ((frete.toDouble()) * desconto)}")

                // -------------------------------------------------------
                val materiaisDouble = materiais.toDouble()
                val freteDouble = frete.toDouble()

                intent.putExtra("materiais", "Materiais: R$$materiaisDouble")
                intent.putExtra("frete", "Frete: R$$freteDouble")
                // ---------------------------------------------------------

                startActivity(intent)

            }


        }

    }

    private fun calcularTotal(materiais: String, frete: String) : Double {

        val total = materiais.toDouble() + frete.toDouble()

        return total

    }

    private fun validarCampos(materiais: String, frete: String): Boolean {

        textInputMateriais.error = null
        textInputFrete.error = null

        if( materiais.isEmpty() ){
            textInputMateriais.error = "Digite o subtotal dos materiais"
            return false
        } else if ( frete.isEmpty() ){
            textInputFrete.error = "Digite o valor do frete"
            return false
        }

        return true

    }

    private fun inicializarComponentesInterface(){

        textInputMateriais = findViewById( R.id.text_input_materiais )
        editMateriais = findViewById( R.id.edit_materiais )

        textInputFrete = findViewById( R.id.text_input_frete )
        editFrete = findViewById( R.id.edit_frete )

        btnCalcular = findViewById( R.id.btn_calcular )

    }

}
