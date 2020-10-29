package utils

sealed class ScreenState {
    object Loading : ScreenState()
    class Success(val data: String) : ScreenState()
    class Error(val error: String) : ScreenState()
}
