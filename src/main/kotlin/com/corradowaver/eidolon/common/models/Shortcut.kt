package com.corradowaver.eidolon.common.models

import org.springframework.data.annotation.Id

data class Shortcut(
    @Id val id: Long? = null,
    val binding: String,
    val description: String,
    val category: String,
    val action: String
)
