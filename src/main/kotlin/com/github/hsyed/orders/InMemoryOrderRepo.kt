package com.github.hsyed.orders

/**
 * InMemoryOrderRepo is a thread-safe in memory version of the OrderRepo.
 */
class InMemoryOrderRepo(private val repo: MutableList<Order> = mutableListOf()) : OrderRepo {
    override fun place(order: Order) {
        synchronized(repo) {
            repo += order
        }
    }

    override fun cancel(order: Order) {
        synchronized(repo) {
            repo.remove(order)
        }
    }

    override fun listAll(): List<Order> {
        synchronized(repo) {
            // return a clone of the repo
            return repo.toList()
        }
    }
}