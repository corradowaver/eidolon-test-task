package com.corradowaver.eidolon.common.mappers

import com.corradowaver.eidolon.api.dto.ShortcutDTO
import com.corradowaver.eidolon.common.models.Shortcut
import com.corradowaver.eidolon.common.utils.ACTION_DELIMITER
import com.corradowaver.eidolon.common.utils.extractAction
import com.corradowaver.eidolon.common.utils.extractCategory

fun ShortcutDTO.toShortcut() =
    Shortcut(
        binding = this.binding,
        description = this.description,
        category = this.extractCategory(),
        action = this.extractAction()
    )

fun Shortcut.toShortcutDTO() =
    ShortcutDTO(
        binding = this.binding,
        description = this.description,
        action = "${this.category}${ACTION_DELIMITER}${this.action}"
    )
