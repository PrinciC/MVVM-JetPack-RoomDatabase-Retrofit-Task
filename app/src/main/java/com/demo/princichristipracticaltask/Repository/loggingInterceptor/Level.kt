package com.demo.princichristipracticaltask.Repository.loggingInterceptor

enum class Level {
    /**
     * No logs.
     */
    NONE,

    /**
     *
     * Example:
     * <pre>`- URL
     * - Method
     * - Headers
     * - Body
    `</pre> *
     */
    BASIC,

    /**
     *
     * Example:
     * <pre>`- URL
     * - Method
     * - Headers
    `</pre> *
     */
    HEADERS,

    /**
     *
     * Example:
     * <pre>`- URL
     * - Method
     * - Body
    `</pre> *
     */
    BODY
}