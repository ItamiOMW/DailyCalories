package com.example.dailycalories.presentation.screens.main.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.dailycalories.R
import com.example.dailycalories.presentation.screens.main.BottomNavItem


@Composable
fun AppBottomBar(
    navItems: List<BottomNavItem>,
    backStackEntry: NavBackStackEntry?,
    modifier: Modifier = Modifier,
    onNavItemClick: (BottomNavItem) -> Unit,
) {

    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.Transparent,
        contentColor = MaterialTheme.colors.onPrimary,
        elevation = 0.dp
    ) {
        navItems.forEach { navItem ->

            val selected = backStackEntry?.destination?.hierarchy
                ?.any { navDestination ->
                    navDestination.route == navItem.route
                } ?: false

            BottomNavigationItem(
                selected = selected,
                onClick = {
                    onNavItemClick(navItem)
                },
                selectedContentColor = MaterialTheme.colors.secondaryVariant,
                unselectedContentColor = MaterialTheme.colors.secondary,
                icon = {
                    Icon(
                        painter = painterResource(id = navItem.iconResId),
                        contentDescription = stringResource(R.string.bottom_bar_icon_desc),
                        modifier = Modifier.size(25.dp)
                    )
                }
            )
        }
    }

}