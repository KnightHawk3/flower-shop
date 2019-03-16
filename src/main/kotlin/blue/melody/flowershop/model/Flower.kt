package blue.melody.flowershop.model

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Flower(
    @Id val uuid: UUID,
    val length: Int,
    val type: String
)
