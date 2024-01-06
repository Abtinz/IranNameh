package com.android.iranname.setting.ui.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContactMail
import androidx.compose.material.icons.filled.ContactPage
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Menu
import androidx.compose.ui.graphics.vector.ImageVector

sealed class SettingScreens(val route: String, val icon: ImageVector, val title:String) {
    object MainMenu : SettingScreens(route = "setting_main_menu", icon = Icons.Default.Menu, title = "Main Menu")
    object AboutUs : SettingScreens(route = "about_us_setting", icon = Icons.Default.ContactPage, title = "About Us")
    object ContactUs : SettingScreens(route = "contact_us_setting", icon = Icons.Default.ContactMail, title = "Contact Us")
    object Help : SettingScreens(route = "help_setting", icon = Icons.Default.Help, title = "Help")
}