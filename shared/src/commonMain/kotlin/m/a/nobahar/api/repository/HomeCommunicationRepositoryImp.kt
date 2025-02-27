package m.a.nobahar.api.repository

import m.a.nobahar.domain.model.HomeCommunication
import m.a.nobahar.domain.repository.HomeCommunicationRepository

class HomeCommunicationRepositoryImp() : HomeCommunicationRepository {

    private var communication: HomeCommunication? = null

    override fun getCommunication(): HomeCommunication? = communication

    override fun setCommunication(communication: HomeCommunication) {
        this.communication = communication
    }
}