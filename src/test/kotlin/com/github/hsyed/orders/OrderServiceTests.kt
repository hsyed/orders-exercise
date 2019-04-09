package com.github.hsyed.orders

import org.junit.Assert
import org.junit.Test

class OrderServiceTests {
    private val repo = OrderService(InMemoryOrderRepo())

    private fun setupBasicOrders(type: OrderType) {
        repo.place(Order("user1", 3.5, 306.0, type))
        repo.place(Order("user2", 1.2, 310.0, type))
        repo.place(Order("user3", 1.5, 307.0, type))
        repo.place(Order("user4", 2.0, 306.0, type))
    }

    @Test
    fun testPlace() {
        setupBasicOrders(OrderType.SELL)
        Assert.assertSame(repo.listAll().size, 4)
    }


    @Test
    fun testCancel() {
        setupBasicOrders(OrderType.SELL)
        repo.cancel(Order("user3", 1.5, 307.0, OrderType.SELL))
        Assert.assertSame(repo.listAll().size, 3)
    }


    @Test
    fun summarize() {
        setupBasicOrders(OrderType.SELL)
        setupBasicOrders(OrderType.BUY)

        val summary = repo.summarizeAll()

        // validate the entries are correctly summarized as well as a basic check of ordering.
        Assert.assertSame(summary.buy.size, 3)
        Assert.assertEquals(summary.buy.elementAt(2), OrderSummaryPageLine(5.5, 306.0))

        Assert.assertSame(summary.sell.size, 3)
        Assert.assertEquals(summary.sell.elementAt(0), OrderSummaryPageLine(5.5, 306.0))
    }
}