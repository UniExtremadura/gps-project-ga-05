package com.example.gps_asee_ga015.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.LockOpen
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.gps_asee_ga015.Model.Protectora
import com.example.gps_asee_ga015.R
import com.example.gps_asee_ga015.Repository.AplicacionDB
import com.example.gps_asee_ga015.Repository.protectoraRepository
import com.example.gps_asee_ga015.navigation.AppScreens
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Composable
fun newOrgComponents(
    navController: NavController,
    protectoraRepository: protectoraRepository
) {
    var nombre: String by remember { mutableStateOf("") }
    var numTlf: String by remember { mutableStateOf("") }
    var email: String by remember { mutableStateOf("") }
    var ciudad: String by remember { mutableStateOf("") }
    var pais: String by remember { mutableStateOf("") }
    var logo: String by remember { mutableStateOf("") }

    Box(
        Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.background_register),
                contentScale = ContentScale.FillBounds
            ), contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(R.drawable.background_register),
            contentDescription = "add_org_background",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight(0.80F)
                .fillMaxWidth(0.85F)
                .padding(top = 30.dp)
                .offset(y = 30.dp)
                .background(Color.White, RoundedCornerShape(8.dp))

        ) {
            item {
                Spacer(Modifier.height(20.dp))
                Text(
                    text = "Añadir Organizacion",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(0.71F)
                )
                Spacer(Modifier.height(40.dp))
                TextField(
                    shape = RoundedCornerShape(8.dp),
                    value = nombre,
                    onValueChange = { nombre = it },
                    placeholder = { Text(text = "Nombre", modifier = Modifier.alpha(0.5F)) },
                    trailingIcon = {
                        Icon(Icons.Outlined.Person, contentDescription = "name_icon")
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.LightGray,
                        unfocusedContainerColor = Color.LightGray,
                        disabledContainerColor = Color.LightGray,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.fillMaxWidth(0.95F)
                )
                Spacer(Modifier.height(15.dp))
                TextField(
                    value = numTlf,
                    shape = RoundedCornerShape(8.dp),
                    onValueChange = { numTlf = it },
                    placeholder = {
                        Text(
                            text = "Número Teléfono", modifier = Modifier.alpha(0.5F)
                        )
                    },
                    trailingIcon = {
                        Icon(Icons.Outlined.LockOpen, contentDescription = "pass_icon")
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.LightGray,
                        unfocusedContainerColor = Color.LightGray,
                        disabledContainerColor = Color.LightGray,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.fillMaxWidth(0.95F)
                )
                Spacer(Modifier.height(15.dp))
                TextField(
                    value = email,
                    shape = RoundedCornerShape(8.dp),
                    onValueChange = { email = it },
                    placeholder = { Text(text = "Email", modifier = Modifier.alpha(0.5F)) },
                    trailingIcon = {
                        Icon(Icons.Outlined.Email, contentDescription = "email_icon")
                    },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.LightGray,
                        unfocusedContainerColor = Color.LightGray,
                        disabledContainerColor = Color.LightGray,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.fillMaxWidth(0.95F)
                )
                Spacer(Modifier.height(15.dp))
                TextField(
                    value = ciudad,
                    shape = RoundedCornerShape(8.dp),
                    onValueChange = { ciudad = it },
                    placeholder = { Text(text = "Ciudad", modifier = Modifier.alpha(0.5F)) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.LightGray,
                        unfocusedContainerColor = Color.LightGray,
                        disabledContainerColor = Color.LightGray,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.fillMaxWidth(0.95F)
                )
                Spacer(Modifier.height(15.dp))
                TextField(
                    value = pais,
                    shape = RoundedCornerShape(8.dp),
                    onValueChange = { pais = it },
                    placeholder = { Text(text = "Pais", modifier = Modifier.alpha(0.5F)) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.LightGray,
                        unfocusedContainerColor = Color.LightGray,
                        disabledContainerColor = Color.LightGray,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.fillMaxWidth(0.95F)
                )
                Spacer(Modifier.height(15.dp))
                TextField(
                    value = logo,
                    shape = RoundedCornerShape(8.dp),
                    onValueChange = { logo = it },
                    placeholder = { Text(text = "Logo (dirección imagen)", modifier = Modifier.alpha(0.5F)) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.LightGray,
                        unfocusedContainerColor = Color.LightGray,
                        disabledContainerColor = Color.LightGray,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.fillMaxWidth(0.95F)
                )
                Spacer(Modifier.height(35.dp))
                Button(onClick = {
                    //Comprobar que todos los campos estan rellenos
                    if (nombre.isNotEmpty() && numTlf.isNotEmpty() && email.isNotEmpty() && ciudad.isNotEmpty() && pais.isNotEmpty()){
                        //Introducir los datos en la base de datos y navegar al login
                        GlobalScope.launch {
                            var newOrg = Protectora(nombre, numTlf, email, ciudad, pais, logo)
                            //La peticion a la base de datos de forma asincrona
                            protectoraRepository.insertOrganizacion(newOrg)

                            Log.i("Crear organizacion", "organizacion creada")
                        }

                        navController.popBackStack()
                    }
                }) {
                    Text(text = "Añadir Organizacion")
                }
                Spacer(Modifier.height(40.dp))
            }
        }
    }
}