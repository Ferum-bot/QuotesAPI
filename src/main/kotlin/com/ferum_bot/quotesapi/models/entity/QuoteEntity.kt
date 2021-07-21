package com.ferum_bot.quotesapi.models.entity

import com.fasterxml.jackson.annotation.JsonFormat
import org.hibernate.Hibernate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class QuoteEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    var id: Long? = null,

    var text: String,

    @ManyToOne
    @JoinColumn(name = "author_entity_id", referencedColumnName = "id")
    var author: AuthorEntity? = null,

    @ManyToOne
    @JoinColumn(name = "tag_entity_id", referencedColumnName = "id")
    var tag: TagEntity? = null,

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    var createdDate: LocalDateTime? = null,

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    var updatedDate: LocalDateTime? = null,
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

    @PrePersist
    fun onCreate() {
        createdDate = LocalDateTime.now()
        updatedDate = LocalDateTime.now()
    }

    @PreUpdate
    fun onUpdate() {
        updatedDate = LocalDateTime.now()
    }
}