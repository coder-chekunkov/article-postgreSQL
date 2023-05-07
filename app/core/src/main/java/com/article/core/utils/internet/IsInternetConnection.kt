package com.article.core.utils.internet

/**
 * Implement this interface in the activity if you need to check the status of internet status.
 */
interface IsInternetConnection {
    /**
     * @return status of internet connection (TRUE -> available; FALSE -> unavailable).
     */
    fun checkInternetConnection(): Boolean
}