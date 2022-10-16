package com.corradowaver.eidolon.common.utils

import com.corradowaver.eidolon.api.dto.ShortcutAddRequestDTO
import java.util.Locale

const val BINDING_KEYS_DELIMITER = "+"
const val ACTION_DELIMITER = "."

fun ShortcutAddRequestDTO.normalizedBinding(): String {
    val keys = this.binding
        .replace(" ", "")
        .split(BINDING_KEYS_DELIMITER)
    val serviceKeys = keys
        .dropLast(1)
        .sorted() // ask if Alt + Shift + K is same as Shift + Alt + K?
    // if yes, then is it ok to store keys in different order than they were received
    val alphabeticKey = keys.last()
    return (serviceKeys + alphabeticKey).joinToString(separator = " $BINDING_KEYS_DELIMITER ") { it.capitalizeFirstLetter() }
}

fun ShortcutAddRequestDTO.extractCategory(): String =
    this.action.substringBefore(ACTION_DELIMITER).lowercase()

fun ShortcutAddRequestDTO.extractAction(): String =
    this.action.substringAfter(ACTION_DELIMITER).lowercase()

private fun String.capitalizeFirstLetter() =
    this.lowercase().replaceFirstChar { it.titlecase(Locale.getDefault()) }
