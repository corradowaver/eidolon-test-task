package com.corradowaver.eidolon.api.dto

data class ShortcutAddRequestDTO(
    val binding: String,
    val description: String,
    val action: String
)
