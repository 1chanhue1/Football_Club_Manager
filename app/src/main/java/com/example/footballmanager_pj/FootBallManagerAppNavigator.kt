package com.example.footballmanager_pj

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.feature_joinclub.presentation.screen.ClubSearchScreen
import com.example.feature_joinclub.presentation.screen.JoinClubScreen
import com.example.feature_joinclub.presentation.viewmodel.ClubSearchViewModel
import com.example.feature_login.presentation.screen.LoginScreen
import com.example.feature_makeclub.presentation.screen.CompleteClubMakingScreen
import com.example.feature_makeclub.presentation.screen.EmblemSelectScreen
import com.example.feature_makeclub.presentation.screen.MakeClubScreen
import com.example.feature_mypage.presentation.screen.MyPageModifyScreen
import com.example.feature_mypage.presentation.screen.MyPageScreen
import com.example.feature_mypage.presentation.viewmodel.MyPageViewModel
import com.example.feature_navigation.Route
import com.example.feature_navigation.showBarList
import com.example.feature_squard.presentation.screen.SquadScreen
import com.example.presentation.screen.HomeScreen

@Composable
fun FootBallManagerAppNavigator(
    navHostController: NavHostController,
    uiRoute: State<String>,
    onNavigate: (String) -> Unit
) {
    val myPageViewModel: MyPageViewModel = hiltViewModel()
    val clubSearchViewModel: ClubSearchViewModel = hiltViewModel()
    NavHost(
        modifier = Modifier.padding(vertical = if (showBarList.contains(uiRoute.value)) 60.dp else 0.dp),
        navController = navHostController,
        startDestination = Route.LOGIN
    ) {
        composable(Route.HOME) {
            onNavigate(Route.HOME)
            HomeScreen(navHostController)
        }
        composable(Route.LOGIN) {
            onNavigate(Route.LOGIN)
            LoginScreen(navHostController)
        }
        composable(Route.SQUAD) {
            onNavigate(Route.SQUAD)
            SquadScreen()
        }
        composable(Route.SETTINGS) {
            onNavigate(Route.SETTINGS)
            MyPageScreen(
                navHostController,
                myPageViewModel,
                onNavigateToLogin = {
                    navHostController.navigate("LOGIN") {
                        popUpTo("SETTINGS") { inclusive = true }
                    }
                },
                onNavigateToMyPageModify = { navHostController.navigate("MYPAGE_MODIFY") }
            )
        }
        composable(Route.MAKE_CLUB) {
            onNavigate(Route.MAKE_CLUB)
            MakeClubScreen()
        }
        composable(Route.EMBLEM_SELECT) {
            onNavigate(Route.EMBLEM_SELECT)
            EmblemSelectScreen()
        }
        composable(Route.COMPLETE_CLUB_MAKING) {
            onNavigate(Route.COMPLETE_CLUB_MAKING)
            CompleteClubMakingScreen()
        }
        composable(Route.MYPAGE_MODIFY) {
            onNavigate(Route.MYPAGE_MODIFY)
            MyPageModifyScreen(
                navHostController,
                myPageViewModel,
                onNavigateToMyPage = {
                    navHostController.navigate("SETTINGS") {
                        popUpTo("MYPAGE_MODIFY")
                    }
                })
        }
        composable(Route.JOIN_CLUB) {
            onNavigate(Route.JOIN_CLUB)
            JoinClubScreen(
                viewModel = clubSearchViewModel,
                onNavigateToClubSearch = {
                    navHostController.navigate("CLUB_SEARCH")
                }
            )
        }
        composable(Route.CLUB_SEARCH) {
            onNavigate(Route.CLUB_SEARCH)
            ClubSearchScreen(viewModel = clubSearchViewModel)
        }
    }
}