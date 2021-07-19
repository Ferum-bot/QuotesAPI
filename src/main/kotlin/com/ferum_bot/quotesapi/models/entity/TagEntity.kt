package com.ferum_bot.quotesapi.models.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.Hibernate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class TagEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,

    var tagName: String,

    @JsonIgnore
    @OneToMany(mappedBy = "tag")
    val quotes: MutableList<QuoteEntity> = mutableListOf(),

    var createdDate: LocalDateTime? = null,

    var updatedDate: LocalDateTime? = null,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) {
            return false
        }
        other as TagEntity

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 326744510

    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }

    fun enrollQuote(quote: QuoteEntity) {
        quotes.add(quote)
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
