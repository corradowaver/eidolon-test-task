package com.corradowaver.eidolon.common.utils

import com.corradowaver.eidolon.api.dto.ShortcutAddRequestDTO
import java.util.Locale

private const val BINDING_KEYS_DELIMITER = "+"
private const val ACTION_DELIMITER = "."
private const val HASH_DELIMITER = ":"

fun ShortcutAddRequestDTO.bindingHash(): String =
    this.binding
        .replace(" ", "")
        .split(BINDING_KEYS_DELIMITER)
        .sorted()
        .joinToString(HASH_DELIMITER) { it.uppercase() }

fun ShortcutAddRequestDTO.normalizedBinding(): String =
    this.binding
        .replace(" ", "")
        .split(BINDING_KEYS_DELIMITER)
        .joinToString(" $BINDING_KEYS_DELIMITER ") { it.capitalizeFirstLetter() }

fun ShortcutAddRequestDTO.extractCategory(): String =
    this.action.substringBefore(ACTION_DELIMITER).lowercase()

fun ShortcutAddRequestDTO.extractAction(): String =
    this.action.substringAfter(ACTION_DELIMITER).lowercase()

private fun String.capitalizeFirstLetter() =
    this.lowercase().replaceFirstChar { it.titlecase(Locale.getDefault()) }
