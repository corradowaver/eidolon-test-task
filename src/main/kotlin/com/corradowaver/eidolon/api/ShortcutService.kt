package com.corradowaver.eidolon.api

import com.corradowaver.eidolon.api.dto.ShortcutAddRequestDTO
import com.corradowaver.eidolon.common.mappers.toShortcut
import com.corradowaver.eidolon.common.models.Shortcut
import com.corradowaver.eidolon.common.validators.validate
import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ShortcutService(
    val shortcutRepository: ShortcutRepository
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun addShortcut(shortcutDTO: ShortcutAddRequestDTO): Mono<Boolean> =
        shortcutRepository
            .save(shortcutDTO.validate().toShortcut())
            .thenReturn(true)
            .onErrorReturn(DataIntegrityViolationException::class.java, false)
            .doOnSuccess { logger.info("Adding $shortcutDTO finished with status: $it") }
            .doOnError { logger.error("Failed to add $shortcutDTO" + it.message) }

    fun fetchShortcutByCategory(category: String): Flux<Shortcut> =
        shortcutRepository
            .findAllByCategory(category)
            .doOnComplete { logger.info("Fetching shortcuts by category=$category complete") }
            .doOnError { logger.error("Failed fetch shortcuts by category=$category" + it.message) }
}
