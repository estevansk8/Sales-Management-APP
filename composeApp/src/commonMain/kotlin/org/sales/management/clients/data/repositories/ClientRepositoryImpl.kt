package org.sales.management.clients.data.repositories

import org.sales.management.clients.data.remote.ClientsApi
import org.sales.management.clients.domain.model.Client
import org.sales.management.clients.domain.repository.ClientRepository

class ClientRepositoryImpl : ClientRepository {
    private val client = ClientsApi()

    override suspend fun getAllClients(): List<Client>? {
        return client.getClients()
    }

    override suspend fun getClientById(id: Long): Client? {
        TODO("Not yet implemented")
    }

    override suspend fun createClient(client: Client): Client {
        TODO("Not yet implemented")
    }

    override suspend fun updateClient(client: Client): Client {
        TODO("Not yet implemented")
    }

    override suspend fun deleteClient(id: Long) {
        TODO("Not yet implemented")
    }

}