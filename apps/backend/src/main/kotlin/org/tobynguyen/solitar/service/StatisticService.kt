package org.tobynguyen.solitar.service

import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.tobynguyen.solitar.model.dto.StatisticDto
import org.tobynguyen.solitar.repository.UrlRepository

@Service
class StatisticService(private val urlRepository: UrlRepository) {

    @Cacheable(value = ["statistics"], key = "'global'")
    fun getStatistic(): StatisticDto {
        return StatisticDto(urlRepository.count(), urlRepository.sumAllClicks().toLong())
    }
}
