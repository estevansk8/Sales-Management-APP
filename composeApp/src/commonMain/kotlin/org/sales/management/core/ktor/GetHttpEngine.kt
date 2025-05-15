package org.sales.management.core.ktor

import io.ktor.client.engine.HttpClientEngine

expect fun getHttpEngine() : HttpClientEngine