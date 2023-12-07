package spring.kotlin.repository.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import spring.kotlin.domain.ArticlePanier

@Entity
@Table(name = "articles_panier")
class ArticlePanierEntity(
        @Id val id: Int,
        val panierId: Int,
        val articleId: Int,
        val quantite: Int
) {
    fun asArticlePanier() = ArticlePanier(this.id, this.panierId, this.articleId, this.quantite)
}

fun ArticlePanier.asEntity() = ArticlePanierEntity(this.id, this.panierId, this.articleId, this.quantite)