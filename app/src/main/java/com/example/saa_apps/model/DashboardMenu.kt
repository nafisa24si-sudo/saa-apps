package com.example.saa_apps.model

import com.example.saa_apps.R

data class DashboardMenu(
    val id: Int,
    val title: String,
    val iconRes: Int,
    val route: String
)

object DashboardMenuData {
    val items = listOf(
        DashboardMenu(1, "Pertemuan 1", R.drawable.ic_info, "p1"),
        DashboardMenu(2, "Pertemuan 2", R.drawable.ic_layanan, "p2"),
        DashboardMenu(3, "Pertemuan 3", R.drawable.ic_person, "p3"),
        DashboardMenu(4, "Pertemuan 4", R.drawable.ic_settings, "p4"),
        DashboardMenu(5, "Pertemuan 5", R.drawable.ic_navigation, "p5"),
        DashboardMenu(6, "Pertemuan 6", R.drawable.ic_calculate, "p6"),
        DashboardMenu(7, "Pertemuan 7", R.drawable.ic_edit, "p7"),
        DashboardMenu(8, "Pertemuan 8", R.drawable.ic_help, "p8"),
        DashboardMenu(9, "Pertemuan 9", R.drawable.ic_agenda, "p9"),
        DashboardMenu(10, "Pertemuan 10", R.drawable.ic_message, "p10"),
        DashboardMenu(11, "Pertemuan 11", R.drawable.ic_layanan, "p11"),
        DashboardMenu(12, "Pertemuan 12", R.drawable.ic_note, "p12"),
        DashboardMenu(13, "Pertemuan 13", R.drawable.ic_schedule, "p13"),
        DashboardMenu(14, "Pertemuan 14", R.drawable.ic_notification, "p14")
    )
}