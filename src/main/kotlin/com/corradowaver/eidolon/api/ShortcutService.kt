package com.corradowaver.eidolon.api

import com.corradowaver.eidolon.api.dto.ShortcutDTO
import com.corradowaver.eidolon.common.mappers.toShortcut
import org.springframework.stereotype.Service

@Service
class ShortcutService(
    val shortcutRepository: ShortcutRepository
) {

    fun add(shortcutDTO: ShortcutDTO) =
        shortcutRepository.save(shortcutDTO.toShortcut())

    fun fetchShortcutByCategory(category: String) =
        shortcutRepository.findAllByCategory(category)
}
