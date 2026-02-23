package org.tobynguyen.solitar.model.entity

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import jakarta.validation.constraints.Size
import java.time.Instant
import org.hibernate.annotations.CreationTimestamp

@Entity
@Table(name = "urls")
class UrlEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "url_gen")
    @SequenceGenerator(name = "url_gen", sequenceName = "url_seq", allocationSize = 1)
    var id: Long = 0,
    @Column(nullable = false, unique = true) @field:Size(min = 7) var shortCode: String = "",
    @Column(nullable = false) var hasAlias: Boolean = false,
    @Column(nullable = false, columnDefinition = "TEXT") var originalUrl: String = "",
    @Column(nullable = true, columnDefinition = "TEXT") var password: String? = null,
    @Column(nullable = false, name = "click_count") var clickCount: Long = 0,
    @Column(nullable = false, name = "is_disabled")
    @field:JsonProperty("isDisabled")
    var isDisabled: Boolean = false,
    @Column(nullable = true, name = "expires_at") var expiresAt: Instant? = null,
    @Column(nullable = false, name = "created_at")
    @CreationTimestamp
    var createdAt: Instant = Instant.now(),
) {}
