package com.arttt95.apis

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.arttt95.apis.api.EnderecoAPI
import com.arttt95.apis.api.RetrofitHelper
import com.arttt95.apis.databinding.ActivityMainBinding
import com.arttt95.apis.model.Endereco
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val retrofit by lazy {
        RetrofitHelper.retrofit
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnSegundaTela.setOnClickListener {

            startActivity(Intent(this, SegundaActivity::class.java))

        }

        binding.btnConsulta.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {

                recuperarEndereco()

            }

        }

    }

    private suspend fun recuperarEndereco() {

        var retorno : Response<Endereco>? = null

        try {

            val enderecoAPI = retrofit.create( EnderecoAPI::class.java )
            retorno = enderecoAPI.recuperarEndereco()

        } catch (e: Exception) {

            e.printStackTrace()
            Log.i("info_api", "Err GET -> MainAct.")

        }

        if( retorno != null ) {

            if( retorno.isSuccessful ) {

                val endereco = retorno.body()

                val rua = endereco?.logradouro
                val cidade = endereco?.localidade

                Log.i("info_api", "Rua: $rua | Cidade: $cidade")

            }

        }

    }



}

/*
{
    "cep": "13013-051",
    "logradouro": "Rua Regente Feijó",
    "complemento": "de 352/353 a 870/871",
    "unidade": "",
    "bairro": "Centro",
    "localidade": "Campinas",
    "uf": "SP",
    "estado": "São Paulo",
    "regiao": "Sudeste",
    "ibge": "3509502",
    "gia": "2446",
    "ddd": "19",
    "siafi": "6291"
}*/
