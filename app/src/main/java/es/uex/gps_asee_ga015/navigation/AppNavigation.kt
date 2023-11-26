package es.uex.gps_asee_ga015

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import es.uex.gps_asee_ga015.navigation.MyNavigationBar


import es.uex.gps_asee_ga015.screens.SearchBarCustom
import es.uex.gps_asee_ga015.navigation.AppScreens
import es.uex.gps_asee_ga015.screens.FirstScreen


/*Elemento composable que se va a encargar de orquestar la navegacion, va a conocer
las pantallas de nuestra app y se va a encargar de gestionar el paso entre ellas*/
@Composable
fun AppNavigation(context : Context){
    val navController = rememberNavController()

    //El elemento NavHost va a conocer las pantallas y como navegar entre ellas
    NavHost(navController = navController, startDestination = AppScreens.SearchScreen.route){
        //El navHost estara formado por diferente composables que seran cada una de nuestras pantallas
        composable(route = AppScreens.SearchScreen.route){
            FirstScreen(navController)
        }



        composable(route = AppScreens.SearchScreen.route){
            SearchBarCustom(navController)
            MyNavigationBar(navController)
        }

    }
}