package com.corradowaver.eidolon.common.validators

import com.corradowaver.eidolon.api.dto.ShortcutAddRequestDTO
import com.corradowaver.eidolon.common.validators.ShortcutValidationException.DuplicateServiceKeysInBindingException
import com.corradowaver.eidolon.common.validators.ShortcutValidationException.InvalidActionPatternException
import com.corradowaver.eidolon.common.validators.ShortcutValidationException.InvalidBindingPatternException
import com.corradowaver.eidolon.common.validators.ShortcutValidationException.WrongServiceKeyFoundInBindingException
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class ShortcutValidatorTest {

    @ParameterizedTest
    @MethodSource("validShortcutRequests")
    fun `if shortcut request is valid no exceptions must be thrown`(shortcut: ShortcutAddRequestDTO) {
        shortcut.validate()
    }

    @ParameterizedTest
    @MethodSource("invalidActionFormatShortcutRequests")
    fun `if shortcut request action is invalid format then InvalidActionPatternException must be thrown`(shortcut: ShortcutAddRequestDTO) {
        assertThrows<InvalidActionPatternException> {
            shortcut.validate()
        }
    }

    @ParameterizedTest
    @MethodSource("invalidBindingFormatShortcutRequests")
    fun `if shortcut request binding is invalid format then InvalidBindingPatternException must be thrown`(shortcut: ShortcutAddRequestDTO) {
        assertThrows<InvalidBindingPatternException> {
            shortcut.validate()
        }
    }

    @ParameterizedTest
    @MethodSource("wrongServiceKeyShortcutRequests")
    fun `if shortcut request binding service key is wrong format then WrongServiceKeyFoundInBindingException must be thrown`(
        shortcut: ShortcutAddRequestDTO
    ) {
        assertThrows<WrongServiceKeyFoundInBindingException> {
            shortcut.validate()
        }
    }

    @ParameterizedTest
    @MethodSource("duplicateServiceKeysShortcutRequests")
    fun `if shortcut request binding has duplicate service keys then DuplicateServiceKeysInBindingException must be thrown`(
        shortcut: ShortcutAddRequestDTO
    ) {
        assertThrows<DuplicateServiceKeysInBindingException> {
            shortcut.validate()
        }
    }

    companion object {
        @JvmStatic
        fun validShortcutRequests() = listOf(
            ShortcutAddRequestDTO(
                "Ctrl + A",
                "test description",
                "git.commit"
            ),
            ShortcutAddRequestDTO(
                "Ctrl + Shift + B",
                "test description",
                "git.push"
            ),
            ShortcutAddRequestDTO(
                "Ctrl + Shift + Alt + C",
                "test description",
                "git.add"
            ),
            ShortcutAddRequestDTO(
                "Ctrl+ Shift +Z",
                "test description",
                "git.log"
            )
        )

        @JvmStatic
        fun duplicateServiceKeysShortcutRequests() = listOf(
            ShortcutAddRequestDTO(
                "Ctrl + Shift + Ctrl + A",
                "test description",
                "git.commit"
            ),
            ShortcutAddRequestDTO(
                "Ctrl + Ctrl + B",
                "test description",
                "git.push"
            )
        )

        @JvmStatic
        fun invalidBindingFormatShortcutRequests() = listOf(
            ShortcutAddRequestDTO(
                "Ctrl + ",
                "test description",
                "git.commit"
            ),
            ShortcutAddRequestDTO(
                "Ctrl + Shift + Alt + Alt + B",
                "test description",
                "git.push"
            ),
            ShortcutAddRequestDTO(
                "Ctrl + Shift +     C",
                "test description",
                "git.add"
            ),
            ShortcutAddRequestDTO(
                "Ctrl    +     Shift + --.--",
                "test description",
                "git.log"
            )
        )

        @JvmStatic
        fun wrongServiceKeyShortcutRequests() = listOf(
            ShortcutAddRequestDTO(
                "Tab + A",
                "test description",
                "git.commit"
            ),
            ShortcutAddRequestDTO(
                "Ctrl + Tab + B",
                "test description",
                "git.push"
            )
        )

        @JvmStatic
        fun invalidActionFormatShortcutRequests() = listOf(
            ShortcutAddRequestDTO(
                "Ctrl + A",
                "test description",
                ".commit"
            ),
            ShortcutAddRequestDTO(
                "Ctrl + Shift + B",
                "test description",
                "git."
            ),
            ShortcutAddRequestDTO(
                "Ctrl + Shift + Alt + C",
                "test description",
                "-.-"
            ),
            ShortcutAddRequestDTO(
                "Ctrl+ Shift +Z",
                "test description",
                "git .push"
            )
        )
    }
}
