package blue.melody.flowershop.controller

import blue.melody.flowershop.model.Flower
import blue.melody.flowershop.service.ComparisonService
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.UUID

@ExtendWith(MockKExtension::class)
internal class ComparisonControllerTest {

    @MockK(relaxed = true)
    lateinit var comparisonService: ComparisonService
    lateinit var comparisonController: ComparisonController

    private val flower = Flower(length = 1, type = "Rose", uuid = UUID.randomUUID())

    @BeforeEach
    fun setUp() {
        comparisonController = ComparisonController(comparisonService)
    }

    @Test
    fun `compare Flower checks repository`() {
        every { comparisonService.isLongestFlower(any()) } returns true
        comparisonController.compareFlower(flower)
        verify { comparisonService.isLongestFlower(flower) }
    }

    @Test
    fun `compare Flower returns accurate DTO for longest flower`() {
        every { comparisonService.isLongestFlower(any()) } returns true
        val response = comparisonController.compareFlower(flower)
        verify { comparisonService.isLongestFlower(flower) }
        assertEquals(ComparisonController.LONGEST_MESSAGE, response.body?.message)
        assertEquals(flower, response.body?.flower)
    }

    @Test
    fun `compare Flower returns accurate DTO for not longest flower`() {
        every { comparisonService.isLongestFlower(any()) } returns false
        val response = comparisonController.compareFlower(flower)
        verify { comparisonService.isLongestFlower(flower) }
        assertEquals(ComparisonController.AVERAGE_MESSAGE, response.body?.message)
        assertEquals(flower, response.body?.flower)
    }
}
