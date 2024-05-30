package com.gemini.mypromptai.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gemini.mypromptai.ui.components.BottomBar
import com.gemini.mypromptai.ui.components.CardMessage
import com.gemini.mypromptai.ui.data.ResultState
import com.gemini.mypromptai.ui.theme.MyPromptAITheme
import com.gemini.mypromptai.ui.theme.primaryBackground
import com.gemini.mypromptai.ui.theme.textColor

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyPromptAITheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomBar(
                            onSend = { prompt ->
                                viewModel.storeUserPrompt(prompt)
                                viewModel.sendPrompt(prompt)
                            }
                        )
                    },
                    contentColor = textColor,
                    containerColor = primaryBackground
                ) { innerPadding ->

                    viewModel.response.collectAsState().value.let {
                        data ->
                        when (data) {
                            is ResultState.Success -> {
                                LazyColumn(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding)
                                        .padding(horizontal = 12.dp),
                                    verticalArrangement = Arrangement.Bottom

                                ) {
                                    items(data.data){
                                        CardMessage(text = it.message!!, isUser = it.isUser)
                                    }
                                }
                            }

                            is ResultState.Loading -> {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding)
                                        .padding(horizontal = 12.dp),
                                    verticalArrangement = Arrangement.Bottom

                                ) {
                                    Text(text = "Loading...")
                                }
                            }

                            is ResultState.Error -> {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(innerPadding)
                                        .padding(horizontal = 12.dp),
                                    verticalArrangement = Arrangement.Bottom

                                ) {
                                    Text(text = "Error: ${data.error}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}



