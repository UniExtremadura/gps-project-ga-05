package com.example.gps_asee_ga015.apiimport com.example.gps_asee_ga015.api.TypeModel.Typeimport com.example.gps_asee_ga015.api.TypeModel.TypeRemoteModelimport com.example.gps_asee_ga015.api.TypeModel.TypeUniqueModelimport com.example.gps_asee_ga015.api.authModel.authModelimport com.example.gps_asee_ga015.api.model.RemoteModelPageimport com.example.gps_asee_ga015.api.model.RemoteResultimport com.example.gps_asee_ga015.api.organizationsModel.OrgRemoteModelimport com.example.gps_asee_ga015.api.organizationsModel.OrganizationsRemoteModelimport retrofit2.Retrofitimport retrofit2.converter.gson.GsonConverterFactoryimport retrofit2.http.Fieldimport retrofit2.http.FormUrlEncodedimport retrofit2.http.GETimport retrofit2.http.Headerimport retrofit2.http.POSTimport retrofit2.http.Pathimport retrofit2.http.Queryimport java.io.IOException// This is the RetrofitService interface, which is used to make the API calls.interface RetrofitService {// Permite hacer login en la API    @FormUrlEncoded    @POST("oauth2/token")    suspend fun login(        @Field("grant_type") auth: String,        @Field("client_id") id: String,        @Field("client_secret") secret: String,    ) : authModel// Permite obtener los datos de un animal en concreto    @GET("animals/{id}")    suspend fun getAnimals(        @Header("Authorization") auth: String,        @Path("id") id: String,    ) : RemoteResult// Permite obtener los datos de un animal aleatorio    @GET("animals/")    suspend fun getAnimalsRandom(        @Header("Authorization") auth: String,        @Query("sort") sort: String    ) : RemoteModelPage    // Permite obtener los datos de un animal aleatorio    @GET("animals/")    suspend fun getAnimalsName(        @Header("Authorization") auth: String,        @Query("sort") sort: String,        @Query("name") name: String,    ) : RemoteModelPage    // Permite obtener los datos de un animal aleatorio    @GET("animals/")    suspend fun getAnimalsLocation(        @Header("Authorization") auth: String,        @Query("sort") sort: String,        @Query("location") location: String,    ) : RemoteModelPage    // Permite obtener los aimales pertenecientes a una organizacion    @GET("animals/")    suspend fun getAnimalsByOrganization(        @Header("Authorization") auth: String,        @Query("organization") organization: String    ) : RemoteModelPage    @GET("animals/")    suspend fun getAnimalsFilters(        @Header("Authorization") auth: String,        @Query("sort") sort: String,        @Query("type") type: String,        @Query("size") size: String,        @Query("gender") gender: String,        @Query("age") age: String,    ) : RemoteModelPage// Permite obtener los datos de las organizaciones    @GET("organizations/")    suspend fun getOrganizations(        @Header("Authorization") auth: String,    ) : OrganizationsRemoteModel// Permite obtener los datos de una organización en concreto    @GET("organizations/{id}")    suspend fun getUniqueOrganization(        @Header("Authorization") auth: String,        @Path("id") id: String,    ) : OrgRemoteModel// Permite obtener los datos de los tipos de animales    @GET("types")    suspend fun getSearchTypes(        @Header("Authorization") auth: String,    ) : TypeRemoteModel    @GET("types/{animal}")    suspend fun getSearchTypebyAnimal(        @Header("Authorization") auth: String,        @Path("animal") animal: String,    ) : TypeUniqueModel// Factory para crear el servicio de Retrofit    object RetrofitServiceFactory {        fun makeRetrofitService(): RetrofitService {            return Retrofit.Builder()                .baseUrl("https://api.petfinder.com/v2/")                .addConverterFactory(GsonConverterFactory.create())                .build().create(RetrofitService::class.java)        }    }}