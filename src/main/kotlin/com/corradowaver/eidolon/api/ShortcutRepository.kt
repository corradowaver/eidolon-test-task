package com.corradowaver.eidolon.api

import com.corradowaver.eidolon.common.models.Shortcut
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface ShortcutRepository : ReactiveCrudRepository<Shortcut, Long> {
    fun findAllByCategory(category: String): Flux<Shortcut>
}
