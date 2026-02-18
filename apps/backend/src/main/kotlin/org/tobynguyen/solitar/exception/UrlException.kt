package org.tobynguyen.solitar.exception

class UrlNotFoundException(override val message: String) : RuntimeException(message)

class UrlExpiredException(override val message: String) : RuntimeException(message)

class UrlDisabledException(override val message: String) : RuntimeException(message)

class UrlShortCodeConflictedException(override val message: String) : RuntimeException(message)
