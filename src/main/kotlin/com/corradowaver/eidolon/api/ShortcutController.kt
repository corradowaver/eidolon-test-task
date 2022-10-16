package com.corradowaver.eidolon.api

import com.corradowaver.eidolon.api.dto.ShortcutAddRequestDTO
import com.corradowaver.eidolon.api.dto.ShortcutSuccessStatusDTO
import com.corradowaver.eidolon.common.mappers.toShortcutResponseDTO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ShortcutController(
    private val shortcutService: ShortcutService
) {

    @PostMapping("/add")
    fun add(@RequestBody shortcut: ShortcutAddRequestDTO) =
        shortcutService
            .addShortcut(shortcut)
            .map { ShortcutSuccessStatusDTO(success = it) }

    @GetMapping("/category/{category_name}")
    fun fetchAllByCategory(@PathVariable("category_name") category: String) =
        shortcutService
            .fetchShortcutByCategory(category)
            .map { it.toShortcutResponseDTO() }
}
