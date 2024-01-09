package com.example.gps_asee_ga015


import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.example.gps_asee_ga015.api.RetrofitService
import com.example.gps_asee_ga015.navigation.AppNavigation
import kotlinx.coroutines.launch
import com.example.gps_asee_ga015.Repository.AplicacionDB

import com.example.gps_asee_ga015.Repository.animalRepository
import com.example.gps_asee_ga015.Repository.clientRepository
import com.example.gps_asee_ga015.Repository.donacionRepository
import com.example.gps_asee_ga015.Repository.favoritosRepository
import com.example.gps_asee_ga015.Repository.protectoraRepository
import com.example.gps_asee_ga015.Repository.solicitudAdopciónRepository
import com.example.gps_asee_ga015.Repository.valoracionRepository
import java.util.concurrent.TimeUnit

private const val grant_type = "client_credentials"
private const val client_id = "jt78yfZFePKyGM8tmLpHZduPe4wiobusxA4gvGrxv5p9xlMREy"
private const val client_secret = "ADto7Ake8ThsPUe57IprbsLHORd29qhZynDdw1ej"
var auth = ""
var emailActual = ""

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val service = RetrofitService.RetrofitServiceFactory.makeRetrofitService()
        val room: AplicacionDB = AplicacionDB.getInstance(context = this)
        val users = clientRepository(room)
        val animals = animalRepository(room, service)
        val donaciones = donacionRepository(room)
        val favoritos = favoritosRepository(room)
        val organizations = protectoraRepository(room, service)
        val solicitudesAdopt = solicitudAdopciónRepository(room)
        val valoraciones = valoracionRepository(room)

        val sharedPreferences = getSharedPreferences("myPreferences", Context.MODE_PRIVATE)
        val timeSeconds = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        lifecycleScope.launch {
            auth = sharedPreferences.getString("token", "")!!
            val time = sharedPreferences.getLong("time", 0)
            //Check if an hour has passed since the last token was generated, if so, generate a new one.
            if (timeSeconds > (time + 3600)) {
                val authResponse = service.login(grant_type, client_id, client_secret)
                auth = "Bearer ${authResponse.access_token}"
                Log.i("Generated token: ", auth)
                sharedPreferences.edit().putString("token", auth).apply()
                sharedPreferences.edit().putLong("time", timeSeconds).apply()
            }
        }
        setContent {
            AppNavigation(this, users, animals, donaciones, favoritos, organizations, solicitudesAdopt, valoraciones)
        }
    }
}