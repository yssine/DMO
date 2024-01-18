package com.tc.metext.Presentation

sealed class Screen(val rout: String) {
    object Home : Screen("home")
    object Summarize : Screen("sum")
    object Extra : Screen("extr")
    object DTT : Screen("dtt")
}
