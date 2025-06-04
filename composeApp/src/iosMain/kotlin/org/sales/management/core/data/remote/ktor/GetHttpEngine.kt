package org.sales.management.core.data.remote.ktor

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin

actual fun getHttpEngine() : HttpClientEngine = Darwin.create()