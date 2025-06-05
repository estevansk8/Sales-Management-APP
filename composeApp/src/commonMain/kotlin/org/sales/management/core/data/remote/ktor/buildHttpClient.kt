package org.sales.management.core.data.remote.ktor

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel.ALL
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json


fun buildHttpClient(
    dataStore: DataStore<Preferences>,
    engine: HttpClientEngine
) : HttpClient {
    return HttpClient(engine) {

        install(ContentNegotiation) {
            json(
                json = Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                }
            )
        }

        install(Logging){
            logger = object : Logger {
                override fun log(message: String) {
                    println(message)
                }
            }
            level = ALL
        }

        defaultRequest {
            contentType(ContentType.Application.Json)
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }

        install(Auth) {
            bearer {
                loadTokens {
                    val tokenKey = stringPreferencesKey("token")

                    // .map para transformar o Flow<Preferences> em Flow<String?>
                    // e .first() para obter o valor mais recente.
                    val tokenValue = dataStore.data
                        .map { preferences ->
                            preferences[tokenKey]
                        }
                        .first()

                    println("TokenValue: $tokenValue")

                    if (!tokenValue.isNullOrBlank()) {
                        BearerTokens(tokenValue, "")
                    } else {
                        null
                    }
                }

                // TODO: Lógica para atualizar o token se a API retornar 401 (Unauthorized)
                refreshTokens {
                    // Chamar API de refresh token e atualizar com o novo token.

                    // val newTokens = apiClient.refreshToken(oldTokens.refreshToken)
                    // dataStore.edit { prefs -> prefs[tokenKey] = newTokens.accessToken }
                    // BearerTokens(newTokens.accessToken, newTokens.refreshToken)
                    println("Refresh token solicitado, mas não implementado neste exemplo.")
                    null
                }

                // Opcional: adicionar condições para quando o token deve ser enviado.
                // sendWithoutRequest { request ->
                //    request.url.host == "minha-api.com" && !request.url.pathSegments.contains("auth")
                // }
            }
        }
    }
}