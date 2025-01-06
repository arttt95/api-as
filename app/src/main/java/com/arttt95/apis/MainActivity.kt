package com.arttt95.apis

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.arttt95.apis.api.EnderecoAPI
import com.arttt95.apis.api.PostagemAPI
import com.arttt95.apis.api.RetrofitHelper
import com.arttt95.apis.databinding.ActivityMainBinding
import com.arttt95.apis.model.Comentario
import com.arttt95.apis.model.Endereco
import com.arttt95.apis.model.Postagem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val apiViaVep by lazy {
        RetrofitHelper.apiViaCep
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

//                recuperarEndereco()
//                recuperarPostagens()
//                recuperarPostagemUnica()
//                recuperarComentariosParaPostagem()
//                recuperarComentariosParaPostagemQuery()
//                salvarPostagem()
//                salvarPostagemFormulario()
//                atualizarPostagemPut()
                atualizarPostagemPatch()
            }

        }

    }

    private suspend fun atualizarPostagemPatch() {

        var retorno : Response<Postagem>? = null
        val postagem = Postagem(
            "Corpo (body: String -> Postagem.kt) da Postagem",
            -1,
            null,
            1090
        )

        try {

            val postagemAPI = retrofit.create( PostagemAPI::class.java )
            retorno = postagemAPI.atualizarPostagemPatch(1, postagem)
            /*retorno = postagemAPI.salvarPostagemFormulario(
                1090,
                0,
                "Título Postagem",
                "Descrição da Postagem"
            )*/

        } catch (e: Exception) {

            e.printStackTrace()
            Log.i("info_api", "Err GET -> MainAct.")

        }

        if( retorno != null ) {

            if( retorno.isSuccessful ) {

                val postagem = retorno.body()

                val id = postagem?.id
                val titulo = postagem?.title
                val userId = postagem?.userId
                val corpo = postagem?.body

                val resultado = "Code: {${retorno.code()}} ID postagem: $id | Título: $titulo | userId: $userId | Corpo: $corpo"

                withContext(Dispatchers.Main) {
                    binding.textResultado.text = resultado
                }


                Log.i("info_api", resultado)

            } else {

                withContext(Dispatchers.Main) {
                    binding.textResultado.text = "Err:  ${retorno.code()}"
                }

            }

        }

    }

    private suspend fun atualizarPostagemPut() {

        var retorno : Response<Postagem>? = null
        val postagem = Postagem(
            "Corpo (body: String -> Postagem.kt) da Postagem",
            -1,
            "Título da Postagem",
            1090
        )

        try {

            val postagemAPI = retrofit.create( PostagemAPI::class.java )
            retorno = postagemAPI.atualizarPostagemPut(1, postagem)
            /*retorno = postagemAPI.salvarPostagemFormulario(
                1090,
                0,
                "Título Postagem",
                "Descrição da Postagem"
            )*/

        } catch (e: Exception) {

            e.printStackTrace()
            Log.i("info_api", "Err GET -> MainAct.")

        }

        if( retorno != null ) {

            if( retorno.isSuccessful ) {

                val postagem = retorno.body()

                val id = postagem?.id
                val titulo = postagem?.title
                val userId = postagem?.userId
                val corpo = postagem?.body

                val resultado = "{${retorno.code()}} ID postagem: $id | Título: $titulo | userId: $userId | Corpo: $corpo"

                withContext(Dispatchers.Main) {
                    binding.textResultado.text = resultado
                }


                Log.i("info_api", resultado)

            } else {

                withContext(Dispatchers.Main) {
                    binding.textResultado.text = "Err:  ${retorno.code()}"
                }

            }

        }

    }

    private suspend fun salvarPostagemFormulario() {

        var retorno : Response<Postagem>? = null
        /*val postagem = Postagem(
            "Corpo da postagem",
            0,
            "Título da Postagem",
            1090

        )*/ // No FormURLEncoded não será usado essa val e será passado item por item, porque tem que ser "guiado" com nomenclatura no PostagemAPI.kt

        try {

            val postagemAPI = retrofit.create( PostagemAPI::class.java )
//            retorno = postagemAPI.salvarPostagemFormulario(postagem)
            retorno = postagemAPI.salvarPostagemFormulario(
                1090,
                0,
                "Título Postagem",
                "Descrição da Postagem"
            )

        } catch (e: Exception) {

            e.printStackTrace()
            Log.i("info_api", "Err GET -> MainAct.")

        }

        if( retorno != null ) {

            if( retorno.isSuccessful ) {

                val postagem = retorno.body()

                val id = postagem?.id
                val titulo = postagem?.title
                val userId = postagem?.userId

                val resultado = "{${retorno.code()}} ID postagem: $id | Título: $titulo | userId: $userId"

                withContext(Dispatchers.Main) {
                    binding.textResultado.text = resultado
                }


                Log.i("info_api", resultado)

            } else {

                withContext(Dispatchers.Main) {
                    binding.textResultado.text = "Err:  ${retorno.code()}"
                }

            }

        }

    }

    private suspend fun salvarPostagem() {

        var retorno : Response<Postagem>? = null
        val postagem = Postagem(
            "Corpo da postagem",
            0,
            "Título da Postagem",
            1090

        )

        try {

            val postagemAPI = retrofit.create( PostagemAPI::class.java )
            retorno = postagemAPI.salvarPostagem(postagem)

        } catch (e: Exception) {

            e.printStackTrace()
            Log.i("info_api", "Err GET -> MainAct.")

        }

        if( retorno != null ) {

            if( retorno.isSuccessful ) {

                val postagem = retorno.body()

                val id = postagem?.id
                val titulo = postagem?.title
                val userId = postagem?.userId

                val resultado = "{${retorno.code()}} ID postagem: $id | Título: $titulo | userId: $userId"

                withContext(Dispatchers.Main) {
                    binding.textResultado.text = resultado
                }


                Log.i("info_api", resultado)

            } else {

                withContext(Dispatchers.Main) {
                    binding.textResultado.text = "Err:  ${retorno.code()}"
                }

            }

        }

    }

    private suspend fun recuperarComentariosParaPostagemQuery() {

        var retorno : Response<List<Comentario>>? = null

        try {

            val postagemAPI = retrofit.create( PostagemAPI::class.java )
            retorno = postagemAPI.recuperarComentariosParaPostagemQuery(1, "")

        } catch (e: Exception) {

            e.printStackTrace()
            Log.i("info_place", "Err GET -> MainAct.")

        }

        if( retorno != null ) {

            if( retorno.isSuccessful ) {

                val listaComentarios = retorno.body()

                var resultado = ""

                listaComentarios?.forEach { comentario ->

                    val idComentario = comentario.id
                    val email = comentario.email

                    val comentarioResultado = "ID: $idComentario Quem fez: $email\n"

                    resultado += comentarioResultado

                }

                withContext(Dispatchers.Main) {
                    binding.textResultado.text = resultado
                }

            }

        }

    }

    private suspend fun recuperarComentariosParaPostagem() {

        var retorno : Response<List<Comentario>>? = null

        try {

            val postagemAPI = retrofit.create( PostagemAPI::class.java )
            retorno = postagemAPI.recuperarComentariosParaPostagem(1)

        } catch (e: Exception) {

            e.printStackTrace()
            Log.i("info_place", "Err GET -> MainAct.")

        }

        if( retorno != null ) {

            if( retorno.isSuccessful ) {

                val listaComentarios = retorno.body()

                var resultado = ""

                listaComentarios?.forEach { comentario ->

                    val idComentario = comentario.id
                    val email = comentario.email

                    val comentarioResultado = "ID: $idComentario Quem fez: $email\n"

                    resultado += comentarioResultado

                }

                withContext(Dispatchers.Main) {
                    binding.textResultado.text = resultado
                }

            }

        }

    }

    private suspend fun recuperarPostagemUnica() {

        var retorno : Response<Postagem>? = null

        try {

            val postagemAPI = retrofit.create( PostagemAPI::class.java )
            retorno = postagemAPI.recuperarPostagemUnica(1)

        } catch (e: Exception) {

            e.printStackTrace()
            Log.i("info_place", "Err GET -> MainAct.")

        }

        if( retorno != null ) {

            if( retorno.isSuccessful ) {

                val postagem = retorno.body()

                val resultado = "ID: ${postagem?.id} | Title: ${postagem?.title}"

                withContext(Dispatchers.Main) {

                    binding.textResultado.text = resultado

                }

                Log.i("info_place", resultado)
            }

        }

    }

    private suspend fun recuperarPostagens() {

        var retorno : Response<List<Postagem>>? = null

        try {

            val postagemAPI = retrofit.create( PostagemAPI::class.java )
            retorno = postagemAPI.recuperarPostagens()

        } catch (e: Exception) {

            e.printStackTrace()
            Log.i("info_place", "Err GET -> MainAct.")

        }

        if( retorno != null ) {

            if( retorno.isSuccessful ) {

                val listaPostagens = retorno.body()

                listaPostagens?.forEach { postagem ->

                    val id = postagem.id
                    val title = postagem.title

                    Log.i("info_place", "Id: $id | Título: $title")
                }

            }

        }

    }

    private suspend fun recuperarEndereco() {

        val cepInseridoUsuario = "13013051"

        var retorno : Response<Endereco>? = null

        try {

            val enderecoAPI = retrofit.create( EnderecoAPI::class.java )
            retorno = enderecoAPI.recuperarEndereco(cepInseridoUsuario, "json")

        } catch (e: Exception) {

            e.printStackTrace()
            Log.i("info_api", "Err GET -> MainAct.")

        }

        if( retorno != null ) {

            if( retorno.isSuccessful ) {

                val endereco = retorno.body()

                val rua = endereco?.logradouro
                val cidade = endereco?.localidade
                val cep = endereco?.ajustado // Esta sendo adaptado no modelo de obj. Endereco

                Log.i("info_api", "Rua: $rua | Cidade: $cidade | CEP: $cep")

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
