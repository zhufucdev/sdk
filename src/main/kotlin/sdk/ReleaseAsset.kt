package com.zhufucdev.sdk

import kotlinx.serialization.Serializable

@Serializable
data class ReleaseAsset(val versionName: String, val productName: String, val url: String)
