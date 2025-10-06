package com.kazemieh.divar.core.category.entity

import jakarta.persistence.*

@Entity(name = "category")
data class Category(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    val icon: String = "",
    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = true)
    val parent: Category? = null,

// برای اینکه اگر دسته بندی رو حذف کردیم همه دسته بندی های داخلی اون هم حذف بشه
//    @OneToMany(
//        mappedBy = "parent",
//        cascade = [CascadeType.ALL],
//        orphanRemoval = true
//    )
    @OneToMany(mappedBy = "parent")
    val children: List<Category> = listOf()
)