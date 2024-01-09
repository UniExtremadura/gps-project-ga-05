package com.example.gps_asee_ga015.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gps_asee_ga015.screens.listOfElements
import com.example.gps_asee_ga015.screens.AnimalComponents
import com.example.gps_asee_ga015.screens.ProfileComponents
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import com.example.gps_asee_ga015.Repository.animalRepository
import com.example.gps_asee_ga015.Repository.clientRepository
import com.example.gps_asee_ga015.Repository.donacionRepository
import com.example.gps_asee_ga015.Repository.favoritosRepository
import com.example.gps_asee_ga015.Repository.protectoraRepository
import com.example.gps_asee_ga015.Repository.solicitudAdopciónRepository
import com.example.gps_asee_ga015.Repository.valoracionRepository
import com.example.gps_asee_ga015.screens.LoginActivity
import com.example.gps_asee_ga015.screens.OrganizationList
import com.example.gps_asee_ga015.screens.SearchBarCustom
import com.example.gps_asee_ga015.screens.listadoResultados
import com.example.gps_asee_ga015.screens.profileOrganizationAPI
import com.example.gps_asee_ga015.screens.profileOrganizationBD
import com.example.gps_asee_ga015.screens.RegisterActivity
import com.example.gps_asee_ga015.screens.newOrgComponents
import com.example.gps_asee_ga015.screens.newAnimalComponents

/*Elemento composable que se va a encargar de orquestar la navegacion, va a conocer
las pantallas de nuestra app y se va a encargar de gestionar el paso entre ellas*/
@Composable
fun AppNavigation(
    context: Context,
    users: clientRepository,
    animals: animalRepository,
    donacionRepository: donacionRepository,
    favoritosRepository: favoritosRepository,
    protectoraRepository: protectoraRepository,
    solicitudAdoptRepository: solicitudAdopciónRepository,
    valoracionRepository: valoracionRepository
) {

    val navController = rememberNavController()

    //El elemento NavHost va a conocer las pantallas y como navegar entre ellas
    NavHost(navController = navController, startDestination = AppScreens.RegisterActivity.route) {
        //El navHost estara formado por diferente composables que seran cada una de nuestras pantallas
        composable(route = AppScreens.RegisterActivity.route) {
            RegisterActivity(navController, users)
        }

        composable(route = AppScreens.LoginActivity.route) {
            LoginActivity(navController, users)
        }

        composable(route = AppScreens.AnimalListScreen.route) {
            listOfElements(navController, users, favoritosRepository)
            MyNavigationBar(navController)
        }

        composable(route = AppScreens.AnimalDetailScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) {
            it.arguments?.getString("id")
                ?.let { it1 ->
                    AnimalComponents(
                        navController,
                        it1,
                        context,
                        solicitudAdoptRepository,
                        favoritosRepository,
                        users
                    )
                }
        }

        composable(route = AppScreens.ProfileUserScreen.route) {
            ProfileComponents(
                navController,
                users,
                solicitudAdoptRepository,
                favoritosRepository,
                animals,
            )
            MyNavigationBar(navController)
        }

        composable(route = AppScreens.SearchScreen.route) {
            SearchBarCustom(navController)
            MyNavigationBar(navController)
        }

        composable(route = AppScreens.OrgListScreen.route) {
            OrganizationList(navController, protectoraRepository)
            MyNavigationBar(navController)
        }

        composable(route = AppScreens.ProfileOrganizationScreenAPI.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) {
            it.arguments?.getString("id")
                ?.let { it1 ->
                    profileOrganizationAPI(
                        navController,
                        it1,
                        context,
                        protectoraRepository,
                        valoracionRepository,
                        donacionRepository,
                        users,
                        animals
                    )
                }

        }

        composable(route = AppScreens.ProfileOrganizationScreenBD.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                }
            )
        ) {
            it.arguments?.getLong("id")
                ?.let { it1 ->
                    profileOrganizationBD(
                        navController,
                        it1,
                        context,
                        users,
                        protectoraRepository,
                        valoracionRepository,
                        donacionRepository,
                        animals
                    )
                }

        }

        composable(route = AppScreens.SearchResultsScreen.route + "?name={search}&type={type}&size={size}&gender={gender}&age={age}",
            arguments = listOf(
                navArgument("search") {
                    type = NavType.StringType
                }, navArgument("gender") {
                    type = androidx.navigation.NavType.StringType
                },
                navArgument("type") {
                    type = androidx.navigation.NavType.StringType
                },
                navArgument("size") {
                    type = androidx.navigation.NavType.StringType
                },
                navArgument("age") {
                    type = androidx.navigation.NavType.StringType
                }
            )
        ) {
            //it.arguments?.getString("search",)?.let { it1 -> listadoResultados(navController, it1, it2, it3, it4, it4) }
            listadoResultados(
                navController = navController,
                search = it.arguments?.getString("search")!!,
                type = it.arguments?.getString("type")!!,
                size = it.arguments?.getString("size")!!,
                gender = it.arguments?.getString("gender")!!,
                age = it.arguments?.getString("age")!!,
                animals
            )
        }

        composable(route = AppScreens.NewOrgScreen.route) {
            newOrgComponents(navController, protectoraRepository)
        }

        composable(route = AppScreens.NewAnimalScreen.route+ "/{idOrg}",
            arguments = listOf(
                navArgument("idOrg") {
                    type = NavType.StringType
                }
            )
        ) {it.arguments?.getString("idOrg")
            ?.let { it1 ->
                newAnimalComponents(navController, animals, it1)
            }
        }
    }
}