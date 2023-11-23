package com.example.proyecto_en_la_sombra.apiimport com.example.proyecto_en_la_sombra.api.TypeModel.TypeRemoteModelimport com.example.proyecto_en_la_sombra.api.authModel.authModelimport com.example.proyecto_en_la_sombra.api.model.RemoteModelPageimport com.example.proyecto_en_la_sombra.api.model.RemoteResultimport com.example.proyecto_en_la_sombra.api.organizationsModel.OrgRemoteModelimport com.example.proyecto_en_la_sombra.api.organizationsModel.OrganizationsRemoteModelimport retrofit2.Retrofitimport retrofit2.converter.gson.GsonConverterFactoryimport retrofit2.http.Fieldimport retrofit2.http.FormUrlEncodedimport retrofit2.http.GETimport retrofit2.http.Headerimport retrofit2.http.Headersimport retrofit2.http.POSTimport retrofit2.http.Pathimport retrofit2.http.Queryinterface RetrofitService {    @FormUrlEncoded    @POST("oauth2/token")    suspend fun login(        @Field("grant_type") auth: String,        @Field("client_id") id: String,        @Field("client_secret") secret: String,    ) : authModel    @GET("animals/{id}")    suspend fun getAnimals(        @Header("Authorization") auth: String,        @Path("id") id: String,    ) : RemoteResult    @GET("animals/")    suspend fun getAnimalsRandom(        @Header("Authorization") auth: String,        @Query("sort") sort: String,    ) : RemoteModelPage    @GET("organizations/")    suspend fun getOrganizations(        @Header("Authorization") auth: String,    ) : OrganizationsRemoteModel    @GET("organizations/{id}")    suspend fun getUniqueOrganization(        @Header("Authorization") auth: String,        @Path("id") id: String,    ) : OrgRemoteModel    @GET("types/")    suspend fun getSearchTypes(        @Header("Authorization") auth: String,    ) : TypeRemoteModel    object RetrofitServiceFactory {        fun makeRetrofitService(): RetrofitService {            return Retrofit.Builder()                .baseUrl("https://api.petfinder.com/v2/")                .addConverterFactory(GsonConverterFactory.create())                .build().create(RetrofitService::class.java)        }    }}