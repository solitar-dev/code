package org.tobynguyen.solitar.controller

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.tobynguyen.solitar.mapper.toResponseDto
import org.tobynguyen.solitar.model.dto.UrlCreateDto
import org.tobynguyen.solitar.model.dto.UrlResponseDto
import org.tobynguyen.solitar.service.UrlService

@RestController
@RequestMapping("/create")
class CreateController(val urlService: UrlService) {
    @PostMapping
    fun shortenUrl(@RequestBody @Valid body: UrlCreateDto): ResponseEntity<UrlResponseDto> {
        val result = urlService.createUrl(body)

        return ResponseEntity.ok(result.toResponseDto())
    }
}
