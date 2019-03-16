package blue.melody.flowershop.service

import blue.melody.flowershop.model.Flower
import blue.melody.flowershop.repository.FlowerRepository
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class ComparisonServiceTest {

    @MockK
    lateinit var flowerRepository: FlowerRepository

    lateinit var comparisonService: ComparisonService

    @BeforeEach
    fun setUp() {
        comparisonService = ComparisonService(flowerRepository)
    }

    @Test
    fun `is longest flower is true for new longest`() {
        every { flowerRepository.findFirstByOrderByLengthDesc() } returns Flower(length = 1, type = "rose")
        assertTrue(comparisonService.isLongestFlower(Flower(length = 11, type = "rose")))
    }

    @Test
    fun `is longest flower is true no existing flower`() {
        every { flowerRepository.findFirstByOrderByLengthDesc() } returns null
        assertTrue(comparisonService.isLongestFlower(Flower(length = 11, type = "rose")))
    }

    @Test
    fun `is longest flower is false for not longest`() {
        every { flowerRepository.findFirstByOrderByLengthDesc() } returns Flower(length = 11, type = "rose")
        assertFalse(comparisonService.isLongestFlower(Flower(length = 1, type = "rose")))
    }
}
