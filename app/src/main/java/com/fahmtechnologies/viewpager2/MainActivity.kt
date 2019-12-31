package com.fahmtechnologies.viewpager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.fahmtechnologies.viewpager2.Fragment.OneFragment
import com.fahmtechnologies.viewpager2.Fragment.ThreeFragment
import com.fahmtechnologies.viewpager2.Fragment.TwoFragment
import com.fahmtechnologies.viewpager2.ViewPagerIndicator.ZoomOutPageTransformer
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity  : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        val fragmentList : ArrayList<Fragment>  = arrayListOf(
            OneFragment.newInstance(),TwoFragment.newInstance(),  ThreeFragment.newInstance())

        val pagerAdapter = ScreenSlidePagerAdapter(this,fragmentList)
        viewPager2.adapter = pagerAdapter


        val zoomOutPageTransformer = ZoomOutPageTransformer()
        viewPager2.setPageTransformer { page, position ->
            zoomOutPageTransformer.transformPage(page, position)
        }

        springDotsIndicator.setViewPager2(viewPager2)

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
               Log.e("=> ","View pager selected position")
            }
        })
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