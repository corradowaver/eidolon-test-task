package com.corradowaver.eidolon.api

import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.springframework.dao.DataIntegrityViolationException
import reactor.core.publisher.Mono
import reactor.test.StepVerifier

internal class ShortcutServiceTest {

    private val shortcutRepository: ShortcutRepository = mock()

    private val shortcutService = ShortcutService(shortcutRepository)

    @Test
    fun `fetch all shortcuts by category must return valid shortcuts`() {
        whenever(shortcutRepository.findAllByCategory(any())).doReturn(shortcuts())
        val fetchedShortcuts = shortcutService.fetchShortcutByCategory("git")
        StepVerifier
            .create(fetchedShortcuts)
            .expectNextCount(3)
            .verifyComplete()
    }

    @Test
    fun `add new shortcut must return true success status`() {
        whenever(shortcutRepository.save(any())).doReturn(singleShortcut())
        val result = shortcutService.addShortcut(singleShortcutRequestDto())
        StepVerifier
            .create(result)
            .expectNext(true)
            .verifyComplete()
    }

    @Test
    fun `add existing shortcut must return false success status`() {
        whenever(shortcutRepository.save(any())).doReturn(Mono.error(DataIntegrityViolationException("")))
        val result = shortcutService.addShortcut(singleShortcutRequestDto())
        StepVerifier
            .create(result)
            .expectNext(false)
            .verifyComplete()
    }

    @Test
    fun `unexpected behaviour must return error`() {
        whenever(shortcutRepository.save(any())).doReturn(Mono.error(RuntimeException("")))
        val result = shortcutService.addShortcut(singleShortcutRequestDto())
        StepVerifier
            .create(result)
            .verifyError()
    }
}
