package org.sales.management.clients.domain.repository

import org.sales.management.clients.domain.model.Client

interface ClientRepository {
    suspend fun getAllClients(): List<Client>?
    suspend fun getClientById(id: Long): Client?
    suspend fun createClient(client: Client): Client
    suspend fun updateClient(client: Client): Client
    suspend fun deleteClient(id: Long)
}