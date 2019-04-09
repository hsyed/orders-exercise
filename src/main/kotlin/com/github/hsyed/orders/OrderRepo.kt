package com.github.hsyed.orders

/**
 * OrderRepo is the basic interface that a database should implement.
 */
interface OrderRepo {
    /**
     * Add an order to the system.
     */
    fun place(order: Order)
    /**
     * Cancel one order from the repo.
     */
    fun cancel(order: Order)
    /**
     * Retrieve all orders currently active in the system
     */
    fun listAll(): List<Order>
}