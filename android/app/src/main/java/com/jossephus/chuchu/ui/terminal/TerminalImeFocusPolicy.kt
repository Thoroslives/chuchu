package com.jossephus.chuchu.ui.terminal

/**
 * Whether [TerminalInputView] must force the IME to rebind after a window-focus
 * change.
 *
 * The terminal input view is an always-focused, invisible EditText. When the app
 * is backgrounded and returns to the foreground the view keeps its focus, so the
 * focus-change listener never fires and the IME is left bound to a stale
 * InputConnection. Typing then lags until the terminal screen is torn down and
 * rebuilt. Forcing an InputMethodManager.restartInput on the background to
 * foreground edge rebinds the IME in place, which is what recreating the screen
 * does anyway.
 *
 * Only the regain edge counts: fire when window focus returns (was lost, now
 * held) and the view still owns focus. Firing on focus loss, or on every
 * window-focus event, would reset an active composition for nothing.
 */
internal fun shouldRebindImeOnWindowFocus(
    previouslyHadWindowFocus: Boolean,
    hasWindowFocus: Boolean,
    viewHasFocus: Boolean,
): Boolean = hasWindowFocus && !previouslyHadWindowFocus && viewHasFocus
