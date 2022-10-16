package com.corradowaver.eidolon.common.mappers

import com.corradowaver.eidolon.api.dto.ShortcutAddRequestDTO
import com.corradowaver.eidolon.api.dto.ShortcutResponseDTO
import com.corradowaver.eidolon.common.models.Shortcut
import com.corradowaver.eidolon.common.utils.extractAction
import com.corradowaver.eidolon.common.utils.extractCategory
import com.corradowaver.eidolon.common.utils.normalizedBinding

fun ShortcutAddRequestDTO.toShortcut() =
    Shortcut(
        binding = this.normalizedBinding(),
        description = this.description,
        category = this.extractCategory(),
        action = this.extractAction()
    )

fun Shortcut.toShortcutResponseDTO() =
    ShortcutResponseDTO(
        actionName = this.action,
        binding = this.binding
    )
