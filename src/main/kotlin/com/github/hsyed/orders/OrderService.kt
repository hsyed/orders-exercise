package com.github.hsyed.orders

import java.util.*

/**
 * OrderService adapts OrderRepo with service methods.
 */
// Note: idiomatic kotlin would simply have the methods as extension functions on OrderRepo.
class OrderService(private val repo: OrderRepo) : OrderRepo by repo {
    /**
     * Return a summarized page of orders. BUY orders are sorted in descending order and SELL orders in ascending order.
     */
    fun summarizeAll(): OrderSummaryPage {
        return summarize(repo.listAll())
    }

    private fun summarize(data: List<Order>): OrderSummaryPage {
        val buy = TreeMap<Double, OrderSummaryPageLine>()
        val sell = TreeMap<Double, OrderSummaryPageLine>()

        data.forEach {
            val tally =
                if (it.type == OrderType.SELL) {
                    sell
                } else {
                    buy
                }

            val curr = tally[it.price]
            if(curr == null) {
                tally[it.price] = OrderSummaryPageLine(it.quantity, it.price)
            } else {
                tally[it.price] = curr.copy(quantity = curr.quantity + it.quantity)
            }

        }

        return OrderSummaryPage(buy.values.reversed(), sell.values)
    }
}