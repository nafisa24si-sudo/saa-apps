package com.example.saa_apps.pertemuan_10

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TenthTabsAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabProfilFragment()
            1 -> TabVisiMisiFragment()
            2 -> TabGaleriFragment()
            else -> TabProfilFragment()
        }
    }
}