package org.tobynguyen.solitar.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.tobynguyen.solitar.model.dto.UrlForwardResponseDto
import org.tobynguyen.solitar.service.UrlService

@RestController
@RequestMapping("/forward")
class ForwardController(private val urlService: UrlService) {
    @GetMapping("/{shortCode}")
    fun forwardUrl(@PathVariable shortCode: String): ResponseEntity<UrlForwardResponseDto> {
        val originalUrl = urlService.getOriginalUrl(shortCode)

        return ResponseEntity.ok(UrlForwardResponseDto(originalUrl))
    }
}
