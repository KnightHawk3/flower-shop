package blue.melody.flowershop.controller

import blue.melody.flowershop.model.ComparisonDTO
import blue.melody.flowershop.model.Flower
import blue.melody.flowershop.service.ComparisonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ComparisonController @Autowired constructor(private val comparisonService: ComparisonService) {
    @PostMapping("/compare")
    fun compareFlower(@RequestParam("flower") flower: Flower): ComparisonDTO {
        val longest = comparisonService.isLongestFlower(flower)

        if (longest) {
            comparisonService.saveFlower(flower)
        }

        return ComparisonDTO(
            message = if (longest) LONGEST_MESSAGE else AVERAGE_MESSAGE,
            flower = flower
        )
    }

    companion object {
        const val LONGEST_MESSAGE = "That's the longest flower I have ever seen!"
        const val AVERAGE_MESSAGE = "That's a nice flower."
    }
}
