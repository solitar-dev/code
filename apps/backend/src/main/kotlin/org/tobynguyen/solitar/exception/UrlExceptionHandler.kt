package org.tobynguyen.solitar.exception

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.tobynguyen.solitar.model.dto.ErrorResponse

@RestControllerAdvice
class UrlExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun onValidationFailed(e: MethodArgumentNotValidException): ResponseEntity<Map<String, Any>> {
        val map = buildMap {
            e.bindingResult.fieldErrors.forEach {
                put(it.field, it.defaultMessage ?: "Validation failed")
            }
        }

        return ResponseEntity.badRequest().body(map)
    }

    @ExceptionHandler(UrlNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun onUrlNotFound(e: UrlNotFoundException, request: HttpServletRequest) =
        ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.reasonPhrase,
            message = e.message,
            path = request.requestURI,
        )

    @ExceptionHandler(UrlExpiredException::class)
    @ResponseStatus(HttpStatus.GONE)
    fun onUrlExpired(e: UrlExpiredException, request: HttpServletRequest) =
        ErrorResponse(
            status = HttpStatus.GONE.value(),
            error = HttpStatus.GONE.reasonPhrase,
            message = e.message,
            path = request.requestURI,
        )

    @ExceptionHandler(UrlDisabledException::class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    fun onUrlDisabled(e: UrlDisabledException, request: HttpServletRequest) =
        ErrorResponse(
            status = HttpStatus.FORBIDDEN.value(),
            error = HttpStatus.FORBIDDEN.reasonPhrase,
            message = e.message,
            path = request.requestURI,
        )

    @ExceptionHandler(UrlShortCodeConflictedException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun onUrlShortCodeConflicted(e: UrlShortCodeConflictedException, request: HttpServletRequest) =
        ErrorResponse(
            status = HttpStatus.CONFLICT.value(),
            error = HttpStatus.CONFLICT.reasonPhrase,
            message = e.message,
            path = request.requestURI,
        )
}
