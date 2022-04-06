package com.trempel.core_ui.exceptions

import java.io.IOException

sealed class TrempelException : IOException() {

    object Network : TrempelException()

    object Service : TrempelException()
}
