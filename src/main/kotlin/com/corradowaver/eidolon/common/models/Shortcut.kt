package com.corradowaver.eidolon.common.models

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column

data class Shortcut(
    @Id val id: Long? = null,
    @Column("binding_hash") val bindingHash: String,
    val binding: String,
    val description: String,
    val category: String,
    val action: String
)
