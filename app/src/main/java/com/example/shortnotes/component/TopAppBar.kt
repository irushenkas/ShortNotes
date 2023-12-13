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

@Composable
fun showTopAppBar(context: Context) {
    val emailTitle = stringResource(R.string.email_title)
    val previousEmail = getEmail(context)

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
                    saveEmail(context, email.value)
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
                sendEmail(context, emailTitle)
            }) {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = "Localized description"
                )
            }
        },
    )
}