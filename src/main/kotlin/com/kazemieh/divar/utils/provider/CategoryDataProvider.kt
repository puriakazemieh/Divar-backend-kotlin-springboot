package com.kazemieh.divar.utils.provider

import com.kazemieh.divar.core.category.entity.Category


object CategoryDataProvider {

    private const val IconFolder = "category_icons"

    fun getData(): List<Category> {
        val list: MutableList<Category> = mutableListOf()
        val c1 = Category(name = "املاک", icon = "${IconFolder}/ic_real_state.svg").apply { list.add(this) }
        val c2 = Category(name = "وسایل نقلیه", icon = "${IconFolder}/ic_vehicles.svg").apply { list.add(this) }
        val c3 = Category(name = "کالای دیجیتال", icon = "${IconFolder}/ic_digital_goods.svg").apply { list.add(this) }
        val c4 = Category(name = "خدمات", icon = "${IconFolder}/ic_services.svg").apply { list.add(this) }
        val c5 = Category(name = "وسایل شخصی", icon = "${IconFolder}/ic_personal_items.svg").apply { list.add(this) }

        Category(name = "فروش مسکونی", parent = c1).apply {
            list.add(this)
            Category(name = "آپارتمان", parent = this).apply { list.add(this) }
            Category(name = "خانه و ویلا", parent = this).apply { list.add(this) }
            Category(name = "زمین کلنگی", parent = this).apply { list.add(this) }
        }

        Category( name = "اجاره مسکونی", parent = c1).apply {
            list.add(this)
            Category( name = "آپارتمان", parent = this).apply { list.add(this) }
            Category( name = "خانه و ویلا", parent = this).apply { list.add(this) }
        }

        Category( name = "اجاره اداری و تجاری", parent = c1).apply {
            list.add(this)
            Category( name = "دفتر کار، اتاق اداری و مطب", parent = this).apply { list.add(this) }
            Category( name = "مغازه و غرفه", parent = this).apply { list.add(this) }
            Category( name = "صنعتی، کشاورزی و تجاری", parent = this).apply { list.add(this) }
        }

        Category( name = "اجاره کوتاه مدت", parent = c1).apply {
            list.add(this)
            Category( name = "آپارتمان و سوئیت", parent = this).apply { list.add(this) }
            Category( name = "ویلا و باغ", parent = this).apply { list.add(this) }
            Category( name = "دفتر کار و فضای آموزشی", parent = this).apply { list.add(this) }
        }

        Category( name = "پروژه‌های ساخت و ساز", parent = c1).apply {
            list.add(this)
            Category( name = "مشارکت در ساخت", parent = this).apply { list.add(this) }
            Category( name = "پیش‌فروش", parent = this).apply { list.add(this) }
        }

        Category( name = "فروش اداری و تجاری", parent = c1).apply {
            list.add(this)
            Category( name = "دفتر کار، اتاق اداری و مطب", parent = this).apply { list.add(this) }
            Category( name = "مغازه و غرفه", parent = this).apply { list.add(this) }
            Category( name = "صنعتی، کشاورزی و تجاری", parent = this).apply { list.add(this) }
        }

        // New categories based on the second image
        Category( name = "موبایل و تبلت", parent = c3).apply {
            list.add(this)
            Category( name = "گوشی موبایل", parent = this).apply { list.add(this) }
            Category( name = "تبلت", parent = this).apply { list.add(this) }
            Category( name = "لوازم جانبی موبایل و تبلت", parent = this).apply { list.add(this) }
            Category( name = "سیم کارت", parent = this).apply { list.add(this) }
        }

        Category( name = "رایانه", parent = c3).apply {
            list.add(this)
            Category( name = "رایانه همراه", parent = this).apply { list.add(this) }
            Category( name = "رایانه رومیزی", parent = this).apply { list.add(this) }
            Category( name = "قطعات و لوازم جانبی", parent = this).apply { list.add(this) }
            Category( name = "مودم و تجهیزات شبکه", parent = this).apply { list.add(this) }
            Category( name = "پرینتر، اسکنر، کپی، فکس", parent = this).apply { list.add(this) }
        }

        Category( name = "صوتی و تصویری", parent = c3).apply {
            list.add(this)
            Category( name = "فیلم و موسیقی", parent = this).apply { list.add(this) }
            Category( name = "دوربین عکاسی و فیلمبرداری", parent = this).apply { list.add(this) }
            Category( name = "پخش کننده همراه", parent = this).apply { list.add(this) }
            Category( name = "سیستم صوتی خانگی", parent = this).apply { list.add(this) }
            Category( name = "ویدئو و پخش کننده DVD", parent = this).apply { list.add(this) }
            Category( name = "تلویزیون و پروژکتور", parent = this).apply { list.add(this) }
            Category( name = "دوربین مداربسته", parent = this).apply { list.add(this) }
        }

        Category( name = "تلفن رومیزی", parent = c3).apply { list.add(this) }
        Category( name = "کنسول، بازی ویدیویی و آنلاین", parent = c3).apply { list.add(this) }

        return list
    }


}