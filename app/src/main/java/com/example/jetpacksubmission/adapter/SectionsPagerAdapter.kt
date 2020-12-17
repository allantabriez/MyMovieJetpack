package com.example.jetpacksubmission.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.jetpacksubmission.R
import com.example.jetpacksubmission.view.PlaceholderFragment

private val TAB_TITLES = arrayOf(
        R.string.tab_text_1,
        R.string.tab_text_2
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment = PlaceholderFragment.newInstance(position + 1)
    override fun getPageTitle(position: Int): CharSequence? =  context.resources.getString(TAB_TITLES[position])
    override fun getCount(): Int = TAB_TITLES.size
}