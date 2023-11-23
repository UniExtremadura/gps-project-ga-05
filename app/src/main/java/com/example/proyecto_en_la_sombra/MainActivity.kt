package com.example.proyecto_en_la_sombra


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.proyecto_en_la_sombra.Model.Cliente
import com.example.proyecto_en_la_sombra.api.RetrofitService
import com.example.proyecto_en_la_sombra.api.organizationsModel.Organization
import com.example.proyecto_en_la_sombra.navigation.AppNavigation
import kotlinx.coroutines.launch
import com.example.proyecto_en_la_sombra.Repository.AplicacionDB

import com.example.proyecto_en_la_sombra.Model.*
import java.time.Duration
import java.util.concurrent.TimeUnit

private const val grant_type = "client_credentials"
private const val client_id = "jt78yfZFePKyGM8tmLpHZduPe4wiobusxA4gvGrxv5p9xlMREy"
private const val client_secret = "ADto7Ake8ThsPUe57IprbsLHORd29qhZynDdw1ej"


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       val service = RetrofitService.RetrofitServiceFactory.makeRetrofitService()
       val room : AplicacionDB = Room
           .databaseBuilder(this, AplicacionDB::class.java, "database.db")
           .build()
        lifecycleScope.launch {
            //room.clienteDAO().insertCliente(Cliente(1,"Manuel",null))
             val clientes: List<Cliente> = room.clienteDAO().getClientes()

            Log.i("Numero de clientes almacenados en la base de datos: ",clientes.size.toString())
        }

        val sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE)
        val timeSeconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        lifecycleScope.launch {
            var auth = sharedPreferences.getString("token", "")!!
            val time = sharedPreferences.getLong("time", 0)
            //Check if an hour has passed since the last token was generated, if so, generate a new one.
            if(timeSeconds > time + 3600) {
                val authResponse = service.login(grant_type, client_id, client_secret)
                auth = "Bearer ${authResponse.access_token}"
                Log.i("Generated token: ", auth)
                sharedPreferences.edit().putString("token", auth).apply()
                sharedPreferences.edit().putLong("time", timeSeconds).apply()
            }

            val animal = service.getAnimals(auth,"69771579")
            println(animal)

            val listanimals = service.getAnimalsRandom(auth,"random")
            println(listanimals)

            val listOrganizations = service.getOrganizations(auth)
            println(listOrganizations)

            val Organization = service.getUniqueOrganization(auth,"WI535")
            println(Organization)
        }
            setContent {
                AppNavigation()
            }
    }
}



@Preview(showSystemUi = true)
@Composable
fun preview(){
    AppNavigation()
}