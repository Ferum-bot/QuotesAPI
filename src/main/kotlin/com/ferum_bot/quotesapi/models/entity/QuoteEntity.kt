package com.ferum_bot.quotesapi.models.entity

import org.hibernate.Hibernate
import java.time.LocalDateTime
import javax.annotation.processing.Generated
import javax.persistence.*

@Entity
data class QuoteEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    var id: Long? = null,

    var text: String,

    @Column(name = "author")
    @ManyToOne
    @JoinColumn(name = "author_entity_id", referencedColumnName = "id")
    var author: AuthorEntity? = null,

    @Column(name = "tag")
    @ManyToOne
    @JoinColumn(name = "tag_entity_id", referencedColumnName = "id")
    var tag: TagEntity? = null,

    var createdDate: LocalDateTime,

    var updatedDate: LocalDateTime,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) {
            return false
        }
        other as QuoteEntity

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 916666166

    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }

    fun assignTag(tag: TagEntity) {
        this.tag = tag
    }

    fun assignAuthor(author: AuthorEntity) {
        this.author = author
    }
}