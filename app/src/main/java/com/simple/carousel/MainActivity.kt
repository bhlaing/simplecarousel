package com.simple.carousel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private val images = listOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image4,
        R.drawable.image5
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = initialiseViewPager()

        val tabLayout = findViewById<TabLayout>(R.id.indicatorTabLayout)

        TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()
    }

    private fun initialiseViewPager() = findViewById<ViewPager2>(R.id.viewPagerShow).apply {
        adapter = ImagePagerAdapter(context).apply {
            items = images
            notifyDataSetChanged()
        }
    }
}
