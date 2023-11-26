package es.uex.gps_asee_ga015.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.runtime.getValue

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import es.uex.gps_asee_ga015.navigation.AppScreens.*
import es.uex.gps_asee_ga015.api.RetrofitService
import es.uex.gps_asee_ga015.auth


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarCustom(navController: NavController) {
    var search by remember { mutableStateOf("") }



    TextField(
        value = search,
        onValueChange = { search = it },
        placeholder = { Text(text = "Buscar", modifier = Modifier.alpha(0.5F)) },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                modifier = Modifier
                //Poner opcion de clicar que utilice el appNavigation
            )
        },
        modifier = Modifier
            .fillMaxWidth()
    )

}

@Composable
fun getType(onUpdate: (HashMap<String,List<String>>) -> Unit){
    val composed = remember { false }
    LaunchedEffect(true) {
        if (!composed) {
            val service = RetrofitService.RetrofitServiceFactory.makeRetrofitService()
            var query = GlobalScope.async(Dispatchers.IO) { service.getSearchTypes(auth) }
            var result = query.await()!!
            var tiposanimal: List<String> = listOf()
            for (i in result.types) {
                tiposanimal = tiposanimal.plus(i.name)
                println(i.name)
            }
        }
    }
}

@Composable
@Preview
fun Preview (){
    //SearchBarCustom(null)
}