package com.corradowaver.eidolon.common.validators

import com.corradowaver.eidolon.api.dto.ShortcutAddRequestDTO
import com.corradowaver.eidolon.common.validators.ShortcutValidationException.DuplicateServiceKeysInBindingException
import com.corradowaver.eidolon.common.validators.ShortcutValidationException.InvalidActionPatternException
import com.corradowaver.eidolon.common.validators.ShortcutValidationException.InvalidBindingPatternException
import com.corradowaver.eidolon.common.validators.ShortcutValidationException.WrongServiceKeyFoundInBindingException
import org.slf4j.LoggerFactory

private const val BINDING_DELIMITER = "+"
private val SERVICE_KEYS = listOf("Ctrl", "Alt", "Shift").map { it.uppercase() }

/*
    Binding pattern.
    Must contain from 1 to 3 words ([a-zA-Z]+) delimited by "+" sign.
    Must ends with uppercase alphabetic character([A-Z]).
    May contain from 0 to 1 space character (\\s?) before or after "+" sign.
 */
private val BINDING_REGEX = Regex("([a-zA-Z]+\\s?\\+\\s?){1,3}([A-Z])")

/*
    Action pattern.
    Must contain two word in lowercase ([a-z]+) delimited by "." sign.
 */
private val ACTION_REGEX = Regex("([a-z]+\\.[a-z]+)")

private val logger = LoggerFactory.getLogger("ShortcutValidator")

fun ShortcutAddRequestDTO.validate() =
    this.apply {
        validateBinding()
        validateAction()
    }

private fun ShortcutAddRequestDTO.validateBinding() {
    if (!this.binding.matches(BINDING_REGEX)) {
        logger.error("Validation failed for $this, invalid binding pattern")
        throw InvalidBindingPatternException()
    }
    val serviceKeys = this.binding
        .replace(" ", "")
        .split(BINDING_DELIMITER)
        .dropLast(1)
        .map { it.uppercase() }
    if (serviceKeys.size != serviceKeys.distinct().size) {
        logger.error("Validation failed for $this, duplicate service keys")
        throw DuplicateServiceKeysInBindingException()
    }
    if (serviceKeys.any { it !in SERVICE_KEYS }) {
        logger.error("Validation failed for $this, wrong service key found in binding")
        throw WrongServiceKeyFoundInBindingException()
    }
}

private fun ShortcutAddRequestDTO.validateAction() {
    if (!this.action.matches(ACTION_REGEX)) {
        logger.error("Validation failed for $this, invalid action pattern")
        throw InvalidActionPatternException()
    }
}
