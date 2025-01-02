package com.arttt95.apis.model

data class Endereco(

    val cep : String,
    val logradouro : String,
    val complemento : String,
    val unidade : String,
    val bairro : String,
    val localidade : String,
    val uf : String,
    val estado : String,
    val regiao : String,
    val ibge : Int,
    val gia : Int,
    val ddd : Int,
    val siafi : Int,

)

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
