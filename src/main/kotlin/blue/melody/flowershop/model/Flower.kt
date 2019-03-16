package blue.melody.flowershop.model

import javax.persistence.Entity

@Entity
data class Flower(val length: Int, val type: String)
