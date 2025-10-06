package com.kazemieh.divar.core.adds.entity

import com.kazemieh.divar.core.category.entity.Category
import com.kazemieh.divar.core.image.entity.Image
import com.kazemieh.divar.core.location.entity.Neighborhood
import com.kazemieh.divar.core.parameter.entity.ParameterAnswer
import com.kazemieh.divar.core.user.entity.User
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.Instant

@Entity(name = "adds")
data class Ads(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val title: String,
    val description: String,
    val price: String,

    @ManyToOne
    @JoinColumn(name = "neighborhood_id")
    val neighborhood: Neighborhood,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: User,

    @ManyToOne
    @JoinColumn(name = "category_id")
    val category: Category,

    @OneToMany(mappedBy = "ads")
    val images: List<Image> = listOf(),

    @OneToMany(mappedBy = "ads")
    val answer: List<ParameterAnswer> = listOf()
)