package com.corradowaver.eidolon.common.utils

import com.corradowaver.eidolon.api.dto.ShortcutDTO

const val ACTION_DELIMITER = "."

fun ShortcutDTO.extractCategory(): String =
    this.action.substringBefore(ACTION_DELIMITER)

fun ShortcutDTO.extractAction(): String =
    this.action.substringAfter(ACTION_DELIMITER)
