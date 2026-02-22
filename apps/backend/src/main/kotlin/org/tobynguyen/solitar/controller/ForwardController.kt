package org.tobynguyen.solitar.controller

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.tobynguyen.solitar.model.dto.UrlForwardDto
import org.tobynguyen.solitar.model.dto.UrlForwardResponseDto
import org.tobynguyen.solitar.service.UrlService

@RestController
@RequestMapping("/forward")
class ForwardController(private val urlService: UrlService) {
    @PostMapping
    fun forwardUrl(@RequestBody @Valid body: UrlForwardDto): ResponseEntity<UrlForwardResponseDto> {
        val result = urlService.getOriginalUrl(body)

        return ResponseEntity.ok(result)
    }
}
