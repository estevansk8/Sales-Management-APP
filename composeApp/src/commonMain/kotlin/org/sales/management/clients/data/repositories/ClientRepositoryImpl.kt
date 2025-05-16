package org.sales.management.clients.data.repositories

import org.sales.management.clients.data.remote.ClientService
import org.sales.management.clients.domain.model.Client
import org.sales.management.clients.domain.model.ClientDTO
import org.sales.management.clients.domain.model.ClientRequest
import org.sales.management.clients.domain.repository.ClientRepository

class ClientRepositoryImpl(
    private val service: ClientService
) : ClientRepository {

    override suspend fun getAllClients(): List<Client>? {
        return service.getClients()
    }

    override suspend fun getClientById(id: Long): Client? {
        TODO("Not yet implemented")
    }

    override suspend fun createClient(client: ClientRequest): ClientDTO {
        return service.createClient(client)
    }

    override suspend fun updateClient(client: Client): Client {
        TODO("Not yet implemented")
    }

    override suspend fun deleteClient(id: Long) {
        TODO("Not yet implemented")
    }

    fun ClientDTO.toDomain() = Client(id, name, phone, address)
    fun Client.toDto() = ClientDTO(id, name, phone, address)
}