package com.github.hsyed.orders

enum class OrderType {
    BUY,
    SELL
}

data class Order(
    val userId: String,
    val quantity: Double,
    val price: Double,
    val type: OrderType
)


data class OrderSummaryPageLine(val quantity: Double, val price: Double)

/**
 * A page containing buy and sell orders.
 */
data class OrderSummaryPage(val buy: Collection<OrderSummaryPageLine>, val sell: Collection<OrderSummaryPageLine>)