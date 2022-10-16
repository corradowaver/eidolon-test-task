package com.corradowaver.eidolon.api

import com.corradowaver.eidolon.api.dto.ShortcutAddRequestDTO
import com.corradowaver.eidolon.common.models.Shortcut
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

fun singleShortcutRequestDto() =
    ShortcutAddRequestDTO(
        binding = "Ctrl + Alt + A",
        description = "test description",
        action = "git.commit"
    )

fun singleShortcut() = Mono.just(
    Shortcut(
        binding = "Ctrl + Alt + A",
        description = "test description",
        category = "git",
        action = "commit"
    )
)

fun shortcuts() = Flux.just(
    Shortcut(
        binding = "Ctrl + Alt + A",
        description = "test description",
        category = "git",
        action = "commit"
    ),
    Shortcut(
        binding = "Ctrl + Shift + A",
        description = "test description",
        category = "git",
        action = "push"
    ),
    Shortcut(
        binding = "Ctrl + Shift + Alt + A",
        description = "test description",
        category = "git",
        action = "add"
    )
)
