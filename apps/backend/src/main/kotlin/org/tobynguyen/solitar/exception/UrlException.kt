package org.tobynguyen.solitar.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class UrlNotFoundException(message: String = "URL not found") : RuntimeException(message)

@ResponseStatus(HttpStatus.GONE)
class UrlExpiredException(message: String = "This URL is no longer available") :
    RuntimeException(message)

@ResponseStatus(HttpStatus.NOT_FOUND)
class UrlDisabledException(
    message: String = "This URL has been disabled due to violation of terms"
) : RuntimeException(message)

@ResponseStatus(HttpStatus.CONFLICT)
class ShortCodeConflictException(message: String = "This alias already exists.") :
    RuntimeException(message)

@ResponseStatus(HttpStatus.UNAUTHORIZED)
class PasswordProtectedException(
    message: String = "Please provide a valid password to unlock this URL"
) : RuntimeException(message)

@ResponseStatus(HttpStatus.UNAUTHORIZED)
class IncorrectPasswordException(message: String = "The provided password is incorrect.") :
    RuntimeException(message)
