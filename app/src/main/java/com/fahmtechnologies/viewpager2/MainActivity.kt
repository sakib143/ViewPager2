package com.fahmtechnologies.viewpager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fahmtechnologies.viewpager2.Fragment.OneFragment
import com.fahmtechnologies.viewpager2.Fragment.ThreeFragment
import com.fahmtechnologies.viewpager2.Fragment.TwoFragment
import com.fahmtechnologies.viewpager2.ViewPagerIndicator.ZoomOutPageTransformer
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity  : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentList : ArrayList<Fragment>  = arrayListOf(
            OneFragment.newInstance(),TwoFragment.newInstance(),  ThreeFragment.newInstance())

        val pagerAdapter = ScreenSlidePagerAdapter(this,fragmentList)

        val zoomOutPageTransformer = ZoomOutPageTransformer()
        viewPager2.setPageTransformer { page, position ->
            zoomOutPageTransformer.transformPage(page, position)
        }


        viewPager2.adapter = pagerAdapter
    }

    override fun onBackPressed() {
        if (viewPager2.currentItem == 0) {
            super.onBackPressed()
        } else {
            viewPager2.currentItem = viewPager2.currentItem - 1
        }
    }

    private inner class ScreenSlidePagerAdapter( fa : FragmentActivity , private val fragments:ArrayList<Fragment>)
        : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = fragments.size
        override fun createFragment(position: Int): Fragment = fragments[position]
    }
}