package com.corradowaver.eidolon.api

import com.corradowaver.eidolon.api.dto.ShortcutAddRequestDTO
import com.corradowaver.eidolon.common.mappers.toShortcut
import com.corradowaver.eidolon.common.models.Shortcut
import com.corradowaver.eidolon.common.validators.validate
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ShortcutService(
    val shortcutRepository: ShortcutRepository
) {

    fun addShortcut(shortcutDTO: ShortcutAddRequestDTO): Mono<Boolean> =
        shortcutRepository
            .save(shortcutDTO.validate().toShortcut())
            .thenReturn(true)
            .onErrorReturn(DataIntegrityViolationException::class.java, false)

    fun fetchShortcutByCategory(category: String): Flux<Shortcut> =
        shortcutRepository
            .findAllByCategory(category)
}
