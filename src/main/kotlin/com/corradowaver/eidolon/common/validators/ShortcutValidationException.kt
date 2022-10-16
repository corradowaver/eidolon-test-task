package com.corradowaver.eidolon.common.validators

import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(BAD_REQUEST)
open class ShortcutValidationException(message: String) : RuntimeException(message) {

    class InvalidBindingPatternException : ShortcutValidationException("Invalid binding pattern.")
    class WrongServiceKeyFoundInBindingException : ShortcutValidationException("Wrong service key found in binding.")
    class InvalidActionPatternException : ShortcutValidationException("Invalid action pattern.")
}
