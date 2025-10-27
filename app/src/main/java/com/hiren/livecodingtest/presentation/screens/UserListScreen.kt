package com.hiren.livecodingtest.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hiren.livecodingtest.data.model.UserModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersScreen(viewModel: UsersViewModel = hiltViewModel()) {

    val state by viewModel.uiState.collectAsState()
    val users by viewModel.users.collectAsState()


    LazyColumn {
        itemsIndexed(users) { index, item ->
            ItemCard(item)
        }
    }

}


    @Composable
    fun ItemCard(userModel: UserModel) {
        Card(modifier = Modifier.padding(8.dp)) {
            Column {
                Text(text = userModel.name)
            }
        }
    }


