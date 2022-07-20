package com.example.calculadoradeimc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.calculadoradeimc.databinding.ActivityMainBinding
import com.example.calculadoradeimc.databinding.Alertdialog2Binding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var b: ActivityMainBinding
    private var altura = 150
    private var peso = 75
    private var dobleAltura = 2.25
    private var  IMC = 33.33


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)
        showTable()
        b.imageView.setOnClickListener{showTable()}

        b.sbAltura.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {

                b.tvSBAltura.text = progress.toString().plus("/200")
                altura = progress



            }


            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                calcularIMC()

            }
        })
        b.sbPeso.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {

                b.tvSBPeso.text = progress.toString().plus("/150")
                peso = progress




            }


            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                calcularIMC()
                          }
        })
}


    private fun snackBar(){
        var obs = when(IMC){
            in 0.0..15.99 -> Snackbar.make(b.root, "Delgadez Severa", Snackbar.LENGTH_SHORT).setBackgroundTint(ContextCompat.getColor(this, R.color.AzulOscuro))
                .setAction("Ver Tabla"){showTable()}.show()
            in 16.00..16.99 -> Snackbar.make(b.root, "Delgadez Moderada", Snackbar.LENGTH_SHORT).setBackgroundTint(ContextCompat.getColor(this, R.color.Azul))
                .setAction("Ver Tabla"){showTable()}.show()
            in 17.00..18.49 -> Snackbar.make(b.root, "Delgadez Leve", Snackbar.LENGTH_SHORT).setBackgroundTint(ContextCompat.getColor(this, R.color.AzulCeleste))
                .setAction("Ver Tabla"){showTable()}.show()
            in 18.50..24.99 -> Snackbar.make(b.root, "Peso Normal", Snackbar.LENGTH_SHORT).setBackgroundTint(ContextCompat.getColor(this, R.color.Verde))
                .setAction("Ver Tabla"){showTable()}.show()
            in 25.00..29.99 -> Snackbar.make(b.root, "Preobesidad", Snackbar.LENGTH_SHORT).setBackgroundTint(ContextCompat.getColor(this, R.color.VerdeCesped))
                .setAction("Ver Tabla"){showTable()}.show()
            in 30.00..34.99 -> Snackbar.make(b.root, "Obesidad Leve", Snackbar.LENGTH_SHORT).setBackgroundTint(ContextCompat.getColor(this, R.color.Amarillo))
                .setAction("Ver Tabla"){showTable()}.show()
            in 35.00..40.00 -> Snackbar.make(b.root, "Obesidad Media", Snackbar.LENGTH_SHORT).setBackgroundTint(ContextCompat.getColor(this, R.color.Naranja))
                .setAction("Ver Tabla"){showTable()}.show()
            else -> Snackbar.make(b.root, "Obesidad Morvida",Snackbar.LENGTH_SHORT).setBackgroundTint(ContextCompat.getColor(this, R.color.Rojo))
                .setAction("Ver Tabla"){showTable()}.show()
        }



    }

  fun calcularIMC() {
      dobleAltura = altura.times(altura) / 10000.0
      IMC = Math.round(peso / dobleAltura)
          .times(100)
          .div(100.0)
      b.tvIMC.text = IMC.toString()
      snackBar()

  }
    fun showTable(){
        val dialog = TableDialog()
        dialog.show(supportFragmentManager, "TablaPeso")
    }
//    fun customDialog() {
//        val inflater = this.layoutInflater
//        val custom_layout = inflater.inflate(R.layout.alertdialog2, null)
//        AlertDialog.Builder(this)
//            .setView(custom_layout)
//            .setPositiveButton("Aceptar", null)
//            .show()
//    }

}
