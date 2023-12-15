package com.example.shortnotes.component

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.shortnotes.R
import com.example.shortnotes.dto.Email
import com.example.shortnotes.dto.Note
import com.example.shortnotes.viewmodel.EmailViewModel
import com.example.shortnotes.viewmodel.NoteViewModel

fun sendEmail(
    context: Context,
    notes: List<Note>,
    email: String,
    emailTitle: String,
    emailChooseTitle: String) {

    if(email.isEmpty()) {
        return
    }

    val emailText = StringBuilder()
    for(note in notes) {
        emailText.appendLine(note.date)
        emailText.appendLine(note.text)
    }

    val emailAddress = arrayOf(email)
    val i = Intent(Intent.ACTION_SEND)
    i.putExtra(Intent.EXTRA_EMAIL,emailAddress)
    i.putExtra(Intent.EXTRA_SUBJECT, emailTitle)
    i.putExtra(Intent.EXTRA_TEXT, emailText.toString())

    i.type = "message/rfc822"

    context.startActivity(Intent.createChooser(i, emailChooseTitle))
}