package blue.melody.flowershop.service

import blue.melody.flowershop.model.Flower
import blue.melody.flowershop.repository.FlowerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ComparisonService @Autowired constructor(private val flowerRepository: FlowerRepository) {
    fun isLongestFlower(flower: Flower): Boolean =
        flower.length > flowerRepository.findFirstByOrderByLengthDesc()?.length ?: 0

    fun saveFlower(flower: Flower) = flowerRepository.save(flower)
}
