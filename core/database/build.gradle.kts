plugins {
    alias(libs.plugins.custom.android.library)
    alias(libs.plugins.custom.android.hilt)
    alias(libs.plugins.custom.android.room)
}

android {
    namespace = "com.stathis.database"
}
