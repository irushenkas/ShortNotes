package com.example.shortnotes.component

import android.content.Context
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.example.shortnotes.R
import com.example.shortnotes.viewmodel.EmailViewModel
import com.example.shortnotes.viewmodel.NoteViewModel

@Composable
fun showTopAppBar(context: Context, noteViewModel: NoteViewModel, emailViewModel: EmailViewModel) {
    val emailTitle = stringResource(R.string.email_title)
    val emailChooseTitle = stringResource(R.string.email_choose_client)
    val previousEmail = getEmail(emailViewModel)

    val showDialog = remember { mutableStateOf(false) }
    var email = remember { mutableStateOf(previousEmail) }

    if(showDialog.value) {
        showEmailDialog(
            email,
            onDismissRequest = {
                showDialog.value = false
            },
            onConfirmation = {
                if(previousEmail != email.value) {
                    saveEmail(emailViewModel, email.value)
                }
                showDialog.value = false
            })
    }

    TopAppBar(
        title = {
            Text(stringResource(R.string.app_title))
        },
        actions = {
            IconButton(onClick = {
                showDialog.value = true
            }) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Localized description"
                )
            }
            IconButton(onClick = {
                sendEmail(context, emailTitle, emailChooseTitle, noteViewModel, emailViewModel)
            }) {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = "Localized description"
                )
            }
        },
    )
}