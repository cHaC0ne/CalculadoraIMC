package com.example.calculadoradeimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.calculadoradeimc.databinding.ActivityMainBinding

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
        customDialog()
        b.imageView.setOnClickListener{customDialog()}

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
                Toast.makeText(this@MainActivity, "$peso",Toast.LENGTH_SHORT).show()           }
        })
}

  fun calcularIMC() {
      dobleAltura = altura.times(altura) / 10000.0
      IMC = Math.round(peso / dobleAltura)
          .times(100)
          .div(100.0)
      b.tvIMC.text = IMC.toString()
  }
    fun customDialog() {
        val inflater = this.layoutInflater
        val custom_layout = inflater.inflate(R.layout.alertdialog, null)
        AlertDialog.Builder(this)
            .setView(custom_layout)
            .setPositiveButton("Aceptar", null)
            .show()
    }
}
