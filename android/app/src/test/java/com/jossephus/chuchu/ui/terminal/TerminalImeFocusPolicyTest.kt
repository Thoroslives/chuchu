package com.jossephus.chuchu.ui.terminal

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Pure tests for the IME-rebind decision TerminalInputView applies on
 * window-focus changes. Non-Android logic, so it runs as plain JUnit.
 */
class TerminalImeFocusPolicyTest {

    @Test
    fun `rebinds when window focus is regained while the view holds focus`() {
        assertTrue(
            shouldRebindImeOnWindowFocus(
                previouslyHadWindowFocus = false,
                hasWindowFocus = true,
                viewHasFocus = true,
            ),
        )
    }

    @Test
    fun `does not rebind when the view does not hold focus`() {
        assertFalse(
            shouldRebindImeOnWindowFocus(
                previouslyHadWindowFocus = false,
                hasWindowFocus = true,
                viewHasFocus = false,
            ),
        )
    }

    @Test
    fun `does not rebind on window-focus loss`() {
        assertFalse(
            shouldRebindImeOnWindowFocus(
                previouslyHadWindowFocus = true,
                hasWindowFocus = false,
                viewHasFocus = true,
            ),
        )
    }

    @Test
    fun `does not rebind when window focus was already held`() {
        // e.g. an in-app popup that never dropped window focus: avoid
        // resetting an active IME composition for nothing.
        assertFalse(
            shouldRebindImeOnWindowFocus(
                previouslyHadWindowFocus = true,
                hasWindowFocus = true,
                viewHasFocus = true,
            ),
        )
    }
}
