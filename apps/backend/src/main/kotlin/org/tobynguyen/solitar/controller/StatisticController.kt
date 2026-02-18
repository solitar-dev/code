package org.tobynguyen.solitar.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.tobynguyen.solitar.model.dto.StatisticDto
import org.tobynguyen.solitar.service.StatisticService

@RestController
@RequestMapping("/statistics")
class StatisticController(private val statisticService: StatisticService) {
    @GetMapping fun getStatistic(): StatisticDto = statisticService.getStatistic()
}
