package com.example.hanumanchalisa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.hanumanchalisa.ui.theme.HanumanChalisaTheme
import com.example.hanumanchalisa.ui.theme.screen.GayatriMantraScreen
import com.example.hanumanchalisa.ui.theme.screen.HomeScreen
import com.example.hanumanchalisa.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HanumanChalisaTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.background,
                        MaterialTheme.colorScheme.surfaceVariant
                    )
                )
            )
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,
            bottomBar = {
                EnhancedBottomNavigationBar(navController)
            }
        ) { innerPadding ->
            NavigationHost(
                navController = navController, 
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun EnhancedBottomNavigationBar(navController: NavHostController) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        BottomNavigation(
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            modifier = Modifier
                .clip(RoundedCornerShape(24.dp))
                .padding(vertical = 8.dp)
        ) {
            BottomNavigationItem(
                label = { 
                    Text(
                        "Hanuman Chalisa",
                        style = MaterialTheme.typography.labelMedium,
                        color = if (currentDestination == "home") 
                            MaterialTheme.colorScheme.primary 
                        else 
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                icon = { 
                    val scale by animateFloatAsState(
                        targetValue = if (currentDestination == "home") 1.2f else 1.0f,
                        animationSpec = tween(300), label = ""
                    )
                    Icon(
                        Icons.Default.Home, 
                        contentDescription = "Hanuman Chalisa",
                        modifier = Modifier.scale(scale),
                        tint = if (currentDestination == "home") 
                            MaterialTheme.colorScheme.primary 
                        else 
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                selected = currentDestination == "home",
                onClick = {
                    if (currentDestination != "home") {
                        navController.navigate("home")
                    }
                },
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
            BottomNavigationItem(
                label = { 
                    Text(
                        "Gayatri Mantra",
                        style = MaterialTheme.typography.labelMedium,
                        color = if (currentDestination == "gayatri_mantra") 
                            MaterialTheme.colorScheme.primary 
                        else 
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                icon = { 
                    val scale by animateFloatAsState(
                        targetValue = if (currentDestination == "gayatri_mantra") 1.2f else 1.0f,
                        animationSpec = tween(300), label = ""
                    )
                    Icon(
                        Icons.Default.Favorite, 
                        contentDescription = "Gayatri Mantra",
                        modifier = Modifier.scale(scale),
                        tint = if (currentDestination == "gayatri_mantra") 
                            MaterialTheme.colorScheme.primary 
                        else 
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                selected = currentDestination == "gayatri_mantra",
                onClick = {
                    if (currentDestination != "gayatri_mantra") {
                        navController.navigate("gayatri_mantra")
                    }
                },
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "home", modifier = modifier) {
        composable("home") { HomeScreen() }
        composable("gayatri_mantra") { GayatriMantraScreen() }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    HanumanChalisaTheme {
        MainScreen()
    }
}