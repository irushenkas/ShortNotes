package com.example.shortnotes.component

import android.app.Application
import android.content.Context
import android.content.Intent
import com.example.shortnotes.viewmodel.EmailViewModel
import com.example.shortnotes.viewmodel.NoteViewModel

fun sendEmail(context: Context, emailTitle: String, emailChooseTitle: String) {
    val notesViewModel = NoteViewModel(context.applicationContext as Application)
    val notes = notesViewModel.getAllDataForEmail()

    val emailText = StringBuilder()
    for(note in notes) {
        emailText.appendLine(note.date)
        emailText.appendLine(note.text)
    }

    val i = Intent(Intent.ACTION_SEND)

    val email = getEmail(context)
    if(email.isEmpty()) {
        return
    }
    val emailAddress = arrayOf(email)
    i.putExtra(Intent.EXTRA_EMAIL,emailAddress)
    i.putExtra(Intent.EXTRA_SUBJECT, emailTitle)
    i.putExtra(Intent.EXTRA_TEXT, emailText.toString())

    i.type = "message/rfc822"

    context.startActivity(Intent.createChooser(i, emailChooseTitle))
}

fun saveEmail(context: Context, email: String) {
    val emailViewModel = EmailViewModel(context.applicationContext as Application)
    emailViewModel.save(email)
}

fun getEmail(context: Context): String {
    val emailViewModel = EmailViewModel(context.applicationContext as Application)
    val email = emailViewModel.getEmail()
    return email?.name ?: String()
}