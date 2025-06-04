package org.sales.management.core.data.remote.ktor

import io.ktor.client.engine.HttpClientEngine

expect fun getHttpEngine() : HttpClientEngine