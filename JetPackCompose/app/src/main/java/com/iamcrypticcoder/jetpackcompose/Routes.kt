package com.iamcrypticcoder.jetpackcompose


open class Routes(val route: String) {
    data object Home : Routes("home")
    data object BasicUIComponents : Routes("basic_ui_components")
    data object BottomNavigationBar : Routes("bottom_navigation_bar")
    data object CustomLazyColumn : Routes("custom_lazy_column")
    data object NavigationDrawer : Routes("navigation_drawer")
    data object AnimationScreen : Routes("animation_screen")
    data object Material3Components : Routes("material_ui_components")
    data object SampleLoginScreen1 : Routes("sample_login_screen_1")
    data object SampleSignupScreen1 : Routes("sample_signup_screen_1")
}