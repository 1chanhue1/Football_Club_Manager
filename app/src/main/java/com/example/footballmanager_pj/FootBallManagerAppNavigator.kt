package com.example.footballmanager_pj

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.feature_join.presentation.screen.JoinScreen
import com.example.feature_join.presentation.screen.JoinSuccessScreen
import com.example.feature_join.presentation.screen.ProfileSettingScreen
import com.example.feature_join.presentation.viewmodel.JoinViewModel
import com.example.feature_joinclub.presentation.screen.ClubSearchScreen
import com.example.feature_joinclub.presentation.screen.JoinClubScreen
import com.example.feature_joinclub.presentation.viewmodel.ClubSearchViewModel
import com.example.feature_login.presentation.screen.LoginScreen
import com.example.feature_makeclub.presentation.screen.CompleteClubMakingScreen
import com.example.feature_makeclub.presentation.screen.EmblemSelectScreen
import com.example.feature_makeclub.presentation.screen.MakeClubScreen
import com.example.feature_makeclub.presentation.viewmodel.MakeClubViewModel
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
    val joinViewModel: JoinViewModel = hiltViewModel()
    val clubSearchViewModel: ClubSearchViewModel = hiltViewModel()
    val makeClubViewModel: MakeClubViewModel = hiltViewModel()
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
        composable(Route.JOIN) {
            JoinScreen(
                navHostController, joinViewModel,
                onNavigateToProfileSettingScreen = {
                    navHostController.navigate("PROFILE_SETTING")
                },
            )
        }
        composable(Route.PROFILE_SETTING) {
            onNavigate(Route.PROFILE_SETTING)
            ProfileSettingScreen(
                joinViewModel,
                onNavigateToJoinSuccessScreen = { navHostController.navigate("JOIN_SUCCESS") })
        }
        composable(Route.JOIN_SUCCESS) {
            onNavigate(Route.JOIN_SUCCESS)
            JoinSuccessScreen(onNavigateToLoginScreen = {
                navHostController.navigate("LOGIN")
            })
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
            MakeClubScreen(makeClubViewModel, onNavigateToEmblemSelect = {
                navHostController.navigate("EMBLEM_SELECT")
            })
        }
        composable(Route.EMBLEM_SELECT) {
            onNavigate(Route.EMBLEM_SELECT)
            EmblemSelectScreen(
                makeClubViewModel,
                onNavigateToCompleteClubMake = {
                    navHostController.navigate("COMPLETE_CLUB_MAKING")
                },
                onNavigateToMakeClub = {
                    navHostController.navigate("MAKE_CLUB") {
                        popUpTo("EMBLEM_SELECT") { inclusive = true }
                    }
                }
            )
        }
        composable(Route.COMPLETE_CLUB_MAKING) {
            onNavigate(Route.COMPLETE_CLUB_MAKING)
            CompleteClubMakingScreen(onNavigateToJoinClub = {
                navHostController.navigate("HOME") {
                    popUpTo("HOME") { inclusive = true }
                    popUpTo("EMBLEM_SELECT") { inclusive = true }
                    popUpTo("MAKE_CLUB") { inclusive = true }
                }
            })
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
                onSearchIconClick = {
                    clubSearchViewModel.searchClub(it)
                },
                onNavigateToMakeClub = {
                    navHostController.navigate("MAKE_CLUB")
                },
                onNavigateToClubSearch = {
                    navHostController.navigate("CLUB_SEARCH")
                }
            )
        }
        composable(Route.CLUB_SEARCH) {
            onNavigate(Route.CLUB_SEARCH)
            ClubSearchScreen(clubSearchViewModel.searchedClub.collectAsState(), clubSearchViewModel.searchValue.value)
        }
    }
}