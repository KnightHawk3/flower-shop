package blue.melody.flowershop.repository

import blue.melody.flowershop.model.Flower
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface FlowerRepository : JpaRepository<Flower, UUID> {
    fun findFirstByOrderByLengthDesc(): Flower?
}
