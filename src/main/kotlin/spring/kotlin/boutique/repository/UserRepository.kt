package spring.kotlin.boutique.repository

import spring.kotlin.boutique.domain.User


interface UserRepository {
    fun create(user: User): Result<User>
    fun list(): List<User>
    fun get(email: String): User?
    fun update(user: User): Result<User>
    fun delete(email: String): User?
}