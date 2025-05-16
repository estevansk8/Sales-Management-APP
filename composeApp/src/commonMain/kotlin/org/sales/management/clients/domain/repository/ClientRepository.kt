package org.sales.management.clients.domain.repository

import org.sales.management.clients.domain.model.Client
import org.sales.management.clients.domain.model.ClientDTO
import org.sales.management.clients.domain.model.ClientRequest

interface ClientRepository {
    suspend fun getAllClients(): List<Client>?
    suspend fun getClientById(id: Long): Client?
    suspend fun createClient(request: ClientRequest): ClientDTO
    suspend fun updateClient(client: Client): Client
    suspend fun deleteClient(id: Long)
}