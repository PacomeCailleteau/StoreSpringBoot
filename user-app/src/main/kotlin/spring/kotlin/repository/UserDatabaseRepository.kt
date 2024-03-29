package spring.kotlin.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import spring.kotlin.domain.User
import spring.kotlin.repository.entity.UserEntity
import spring.kotlin.repository.entity.asEntity
import kotlin.jvm.optionals.getOrNull

@Repository
class UserDatabaseRepository(private val jpa: UserJpaRepository) : UserRepository {
    override fun create(user: User): Result<User> = if (jpa.findById(user.email).isPresent) {
        Result.failure(Exception("User already in DB"))
    } else {
        val saved = jpa.save(user.asEntity())
        Result.success(saved.asUser())
    }

    override fun list(): List<User> {
        return jpa.findAll().map { it.asUser() }
    }

    override fun get(email: String): User? {
        return jpa.findById(email)
                .map { it.asUser() }
                .getOrNull()
    }

    override fun update(user: User): Result<User> = if (jpa.findById(user.email).isPresent) {
        val saved = jpa.save(user.asEntity())
        Result.success(saved.asUser())
    } else {
        Result.failure(Exception("User not in DB"))
    }

    override fun delete(email: String): User? {
        return jpa.findById(email)
                .also { jpa.deleteById(email) }
                .map { it.asUser() }
                .getOrNull()
    }

}

interface UserJpaRepository : JpaRepository<UserEntity, String> {
}