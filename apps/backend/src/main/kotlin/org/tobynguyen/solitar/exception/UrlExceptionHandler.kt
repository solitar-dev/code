package org.tobynguyen.solitar.exception

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class UrlExceptionHandler : ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest,
    ): ResponseEntity<Any> {
        val invalidParams =
            ex.bindingResult.fieldErrors.map {
                mapOf("name" to it.field, "reason" to (it.defaultMessage ?: "Validation failed"))
            }

        val problemDetail =
            ProblemDetail.forStatusAndDetail(
                    HttpStatus.BAD_REQUEST,
                    "The request contained invalid data. Please check the 'invalid_params' array.",
                )
                .apply { setProperty("invalid_params", invalidParams) }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail)
    }

    @ExceptionHandler(UrlProtectedException::class)
    fun onUrlProtected(e: UrlProtectedException) =
        ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, e.message)

    @ExceptionHandler(UrlNotFoundException::class)
    fun onUrlNotFound(e: UrlNotFoundException) =
        ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.message)

    @ExceptionHandler(UrlExpiredException::class)
    fun onUrlExpired(e: UrlExpiredException) =
        ProblemDetail.forStatusAndDetail(HttpStatus.GONE, e.message)

    @ExceptionHandler(UrlDisabledException::class)
    fun onUrlDisabled(e: UrlDisabledException) =
        ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, e.message)

    @ExceptionHandler(UrlShortCodeConflictedException::class)
    fun onUrlShortCodeConflicted(e: UrlShortCodeConflictedException) =
        ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.message)
}
