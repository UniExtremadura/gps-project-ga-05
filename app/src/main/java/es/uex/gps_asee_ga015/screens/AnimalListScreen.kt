package com.example.gps_asee_ga015.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.gps_asee_ga015.Model.Favoritos
import com.example.gps_asee_ga015.R
import com.example.gps_asee_ga015.Repository.AplicacionDB
import com.example.gps_asee_ga015.api.RetrofitService
import com.example.gps_asee_ga015.api.model.Animal
import com.example.gps_asee_ga015.api.model.RemoteModelPage
import com.example.gps_asee_ga015.api.model.RemoteResult
import com.example.gps_asee_ga015.auth
import com.example.gps_asee_ga015.navigation.AppScreens
import com.example.gps_asee_ga015.ui.theme.PurpleGrey40
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.material3.Button
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import com.example.gps_asee_ga015.Repository.clientRepository
import com.example.gps_asee_ga015.Repository.favoritosRepository
import com.example.gps_asee_ga015.emailActual
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/*Funcion que pinta cada elemento de la lista de forma optima*/
@Composable
fun listOfElements(navController: NavController, users: clientRepository, favoritosRepository: favoritosRepository) {
    var result by remember { mutableStateOf<RemoteModelPage?>(null) }
    LaunchedEffect(true) {
        val service = RetrofitService.RetrofitServiceFactory.makeRetrofitService()
        val query = GlobalScope.async(Dispatchers.IO) { service.getAnimalsRandom(auth, "random") }
        result = query.await()
    }

    if (result != null) {
        /*LazyColumn nos permite ser mas eficientes ya que
     * traera a memoria unicamente los elementos que se pueden pintar (los que
     * caben dentro de los margenes de la pantalla)*/
        LazyColumn {
            /*Items es un iterador inteligente que se va a ejecutar
            * una vez por cada elemento de la lista*/
            result?.animals?.let {
                items(it) { animal ->
                    listElement(animal, navController, users, favoritosRepository)
                }
            }


        }
    }

}

/*Funcion que pinta los textos y las imagenes en conjunto*/
@Composable
fun listElement(animal: Animal, navController: NavController, users: clientRepository, favoritosRepository: favoritosRepository) {
    Box(modifier = Modifier
        .fillMaxSize()
        .clickable {
            navController.navigate(route = AppScreens.AnimalDetailScreen.route + "/" + animal.id)
        }
    ) {
        Spacer(modifier = Modifier.height(5.dp))
        imageElements(
            if (animal.photos.isNotEmpty())
                animal.photos[0].full
            else
                "https://play-lh.googleusercontent.com/QuYkQAkLt5OpBAIabNdIGmd8HKwK58tfqmKNvw2UF69pb4jkojQG9za9l3nLfhv2N5U"
        )
        iconElements(animal, users, favoritosRepository)
        textElements(animal)
    }
}

/*Funcion que pinta los elementos de tipo imagen*/
@Composable
fun imageElements(image: String) {
    AsyncImage(
        model = image,
        placeholder = painterResource(R.drawable.ic_launcher_foreground),
        contentDescription = "Animal photo",
        modifier = Modifier
            .background(color = PurpleGrey40)
            .size(width = 600.dp, height = 800.dp) //Tendria que ocupar toda la pantalla
    )
}

/*Funcion que pinta los elementos de tipo texto*/
@Composable
fun iconElements(animal: Animal, users: clientRepository, favoritosRepository: favoritosRepository) {
    Column {
        var islikeClicked by rememberSaveable { mutableStateOf(false) }
        /* Inicializacion de los likes, si al animal ya se le ha dado like previamente, inicializa
        la variable islikeClicked para que pinte el corazon en consecuencia*/
        var result by remember { mutableStateOf<Favoritos?>(null) }
        LaunchedEffect(true) {
            GlobalScope.async(Dispatchers.IO) {
                //La peticion a la base de datos de forma asincrona
                //Inserta en la base de datos si sabe que no existe el id de dicho animal en la bd
                val query = GlobalScope.async(Dispatchers.IO) {
                    favoritosRepository.getFavByIdAnimal(animal.id.toLong(), users.getClienteByEmail(emailActual).idCliente)
                }
                result = query.await()
            }
        }
        if (result != null) {
            islikeClicked = true
        } else {
            islikeClicked = false
        }

        IconButton(
            onClick = {
                islikeClicked = !islikeClicked
                if (!islikeClicked) {
                    //ha pulsado sobre no me gusta, luego lo elimina de la
                    GlobalScope.launch {
                        //La peticion a la base de datos de forma asincrona
                        //Elimina de la base de la tabla favoritos, dicho animal
                        favoritosRepository.deleteFav(Favoritos(users.getClienteByEmail(emailActual).idCliente, animal.id.toLong()))
                        var favoritos: List<Favoritos> = favoritosRepository.getFavsByIdClient(users.getClienteByEmail(emailActual).idCliente)

                        Log.i("Numero de favs ", favoritos.size.toString())
                    }
                } else {
                    GlobalScope.launch {
                        //La peticion a la base de datos de forma asincrona
                        //Elimina de la base de la tabla favoritos, dicho animal
                        favoritosRepository.setFav(Favoritos(users.getClienteByEmail(emailActual).idCliente, animal.id.toLong()))
                        var favoritos: List<Favoritos> = favoritosRepository.getFavsByIdClient(users.getClienteByEmail(emailActual).idCliente)

                        Log.i("Numero de favs ", favoritos.size.toString())
                    }
                }
            },
            modifier = Modifier
                .padding(start = 350.dp, top = 630.dp)
                .size(30.dp)
        ) {

            if (!islikeClicked) {
                Icon(
                    Icons.Default.FavoriteBorder,
                    contentDescription = "like button no"
                )

            } else {
                Icon(
                    Icons.Default.Favorite,
                    contentDescription = "like button si"
                )
            }
        }

        Share(
            text = "Échale un vistazo a este bicho, ha sido verlo y acordarme de tí y de lo solo que estás: " + animal.url,
            context = LocalContext.current
        )
    }
}

@Composable
fun textElements(animal: Animal) {
    Column {
        Text(
            "Localización Organización",
            modifier = Modifier.padding(top = 10.dp, start = 20.dp)
        )
        Text(
            animal.organization_id.toString(),
            modifier = Modifier.padding(start = 20.dp)
        )
        Text(
            animal.name,
            modifier = Modifier.padding(top = 660.dp, start = 20.dp)
        )
        if (animal.description != null) Text(
            animal.description,
            modifier = Modifier.padding(start = 20.dp)
        )
        else Text(
            "No hay descripcion",
            modifier = Modifier.padding(start = 20.dp)
        )
    }
}

@Composable
fun Share(text: String, context: Context) {
    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)

    IconButton(
        onClick = {
            startActivity(context, shareIntent, null)
        },
        modifier = Modifier
            .padding(start = 350.dp, top = 10.dp)
            .size(30.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Share,
            contentDescription = null
            )
    }
}