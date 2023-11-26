package es.uex.gps_asee_ga015.navigation

/*Esta clase se utiliza para centralizar las pantallas*/
sealed class AppScreens(val route: String){
    //Aqui se definiran las pantallas que forman nuestra aplicacion
    //Con esto se consigue tipar y limitar las pantallas de nuestra aplicacion, para que en el momento en el que
    // se quieran realizar navegaciones solo se podran realizar a las pantallas que tenemos aqui definidas


    //Pantalla al iniciar la App
    object FirstScreen: AppScreens("first_screen")


    //Pantalla de busqueda y filtrado
    object SearchScreen: AppScreens("search_screen")


}
