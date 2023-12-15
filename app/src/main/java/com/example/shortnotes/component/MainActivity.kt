package com.example.shortnotes.component

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.shortnotes.ui.theme.ShortNotesTheme
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shortnotes.R
import com.example.shortnotes.viewmodel.EmailViewModel
import com.example.shortnotes.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val notesViewModel: NoteViewModel by viewModels()
    private val emailViewModel: EmailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShortNotesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "home") {
                        composable("home") {
                            HomeScreen(onNewClick = {
                                navController.navigate("new")
                            })
                        }
                        composable("new") {
                            NewNoteScreen(onShowListClick = {
                                navController.popBackStack()
                            })
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun HomeScreen(
        onNewClick: () -> Unit,
    ) {
        val context = LocalContext.current
        val emailTitle = stringResource(R.string.email_title)
        val emailChooseTitle = stringResource(R.string.email_choose_client)
        val email = emailViewModel.getEmail()
        val previousEmail = email?.name ?: String()

        val notes by notesViewModel.data.collectAsState(
            initial = emptyList()
        )

        Scaffold(
            topBar = {
                showTopAppBar(
                    previousEmail,
                    onSaveEmailClick = fun(value: String) {
                        emailViewModel.save(value)
                    },
                    onSendEmailClick = {
                        val notes = notesViewModel.getAllDataForEmail()
                        sendEmail(context, notes, email?.name ?: String(),
                            emailTitle, emailChooseTitle)
                    })
            },
            content = { padding ->
                showDataList(notes, padding)
                showAddButton(onNewClick)
            }
        )
    }

    @Composable
    fun NewNoteScreen(
        onShowListClick: () -> Unit,
    ) {
        val context = LocalContext.current
        val emailTitle = stringResource(R.string.email_title)
        val emailChooseTitle = stringResource(R.string.email_choose_client)
        val email = emailViewModel.getEmail()
        val previousEmail = email?.name ?: String()

        var text by remember { mutableStateOf("") }

        Scaffold(
            topBar = {
                showTopAppBar(
                    previousEmail,
                    onSaveEmailClick = fun(value: String) {
                        emailViewModel.save(value)
                    },
                    onSendEmailClick = {
                        val notes = notesViewModel.getAllDataForEmail()
                        sendEmail(context, notes, email?.name ?: String(),
                            emailTitle, emailChooseTitle)
                    })
            },
            content = { padding ->
                BasicTextField(
                    value = text,
                    onValueChange = {
                        text = it
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Bottom,
                ) {
                    showCreateNoteButton(
                        onSaveClick = {
                            val sdf = SimpleDateFormat("dd.MM.yyyy")
                            val currentDate = sdf.format(Date())
                            notesViewModel.save(text, currentDate)
                            onShowListClick()
                        })
                    showCancelButton(onShowListClick)
                }
            }
        )
    }
}