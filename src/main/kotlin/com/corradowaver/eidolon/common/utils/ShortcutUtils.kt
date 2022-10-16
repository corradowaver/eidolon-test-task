package com.corradowaver.eidolon.common.utils

import com.corradowaver.eidolon.api.dto.ShortcutAddRequestDTO

const val ACTION_DELIMITER = "."

fun ShortcutAddRequestDTO.extractCategory(): String =
    this.action.substringBefore(ACTION_DELIMITER)

fun ShortcutAddRequestDTO.extractAction(): String =
    this.action.substringAfter(ACTION_DELIMITER)
