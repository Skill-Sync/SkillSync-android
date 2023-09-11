package com.ss.skillsync.navigation.component

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ss.skillsync.commonandroid.theme.NeutralGray
import com.ss.skillsync.commonandroid.theme.SkillSyncTheme
import com.ss.skillsync.commonandroid.theme.White
import com.ss.skillsync.navigation.NavGraphs

/**
 * Created by Muhammed Salman email(mahmadslman@gmail.com) on 9/10/2023.
 */
@Composable
fun SSBottomNavigation(
    navigator: NavController,
    navGraphs: NavGraphs = NavGraphs.get(),
) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val navItems = navGraphs.getBottomNavRoutes()

    val backStackEntry by navigator.currentBackStackEntryAsState()
    if (shouldShowBottomNav(backStackEntry)) {
        NavigationBar(
            modifier = Modifier,
            containerColor = MaterialTheme.colorScheme.background,
            tonalElevation = 5.dp,
        ) {
            navItems.forEachIndexed { index, navItem ->
                NavigationBarItem(
                    selected = index == selectedItem,
                    icon = {
                        Icon(
                            painter = painterResource(id = navItem.second),
                            contentDescription = navItem.first.route
                        )
                    },
                    label = {
                        Text(
                            text = navItem.first.route,
                            color = if (index == selectedItem) White else NeutralGray
                        )
                    },
                    onClick = {
                        selectedItem = index
                        navigator.navigate(navItem.first.route)
                    },
                    colors = NavigationBarItemDefaults.colors(
                        indicatorColor = MaterialTheme.colorScheme.background.copy(),
                        selectedIconColor = White,
                        unselectedIconColor = NeutralGray,
                    ),
                )
            }
        }
    }
}

private fun shouldShowBottomNav(
    backStackEntry: NavBackStackEntry?,
): Boolean {
    val route = backStackEntry?.destination?.route ?: return false
    val expectedRoutes = NavGraphs.get().getBottomNavRoutes().map { it.first.route }
    return route in expectedRoutes
}

@Preview
@Composable
fun BottomNavBarPreview() {
    SkillSyncTheme {
        SSBottomNavigation(navigator = NavController(LocalContext.current))
    }
}