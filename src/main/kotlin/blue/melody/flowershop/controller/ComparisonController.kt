package blue.melody.flowershop.controller

import blue.melody.flowershop.model.ComparisonDTO
import blue.melody.flowershop.model.Flower
import blue.melody.flowershop.service.ComparisonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ComparisonController @Autowired constructor(private val comparisonService: ComparisonService) {
    @PostMapping("/compare")
    fun compareFlower(@RequestBody flower: Flower): ResponseEntity<ComparisonDTO> {
        val longest = comparisonService.isLongestFlower(flower)
        comparisonService.saveFlower(flower)

        return ResponseEntity.ok(ComparisonDTO(
            message = when {
                longest -> LONGEST_MESSAGE
                else -> AVERAGE_MESSAGE
            },
            flower = flower))
    }

    companion object {
        const val LONGEST_MESSAGE = "Thanks, I love it!"
        const val AVERAGE_MESSAGE = "That's a nice flower."
    }
}
