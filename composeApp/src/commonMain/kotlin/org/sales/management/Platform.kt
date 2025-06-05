package org.sales.management

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

