package com.example.shortnotes

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.shortnotes.ui.theme.ShortNotesTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shortnotes.dto.Note
import com.example.shortnotes.viewmodel.NoteViewModel
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
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
}

@Composable
fun HomeScreen(
    onNewClick: () -> Unit,
) {
    val context = LocalContext.current
    val notesViewModel = NoteViewModel(context.applicationContext as Application)
    val notes by notesViewModel.data.collectAsState(
        initial = emptyList()
    )

    showDataList(notes)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
        showAddButton(onNewClick)
        showSendButton(context)
    }
}

@Composable
fun NewNoteScreen(
    onShowListClick: () -> Unit,
) {
    val context = LocalContext.current
    val notesViewModel = NoteViewModel(context.applicationContext as Application)

    var text by remember { mutableStateOf("") }

    BasicTextField(
        value = text,
        onValueChange = {
            text = it
        },
        modifier = Modifier.padding(10.dp))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Bottom,
    ) {
        showCreateNoteButton(onShowListClick, text, notesViewModel)
        showCancelButton(onShowListClick)
    }
}

@Composable
fun showDataList(notes: List<Note>){
    LazyColumn {
        items(items = notes, itemContent = { note ->
            showText(note = note)
        })
    }
}

@Composable
fun showText(note: Note){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = note.date,
            modifier = Modifier
                .padding(0.dp, 4.dp, 0.dp, 0.dp),
            textAlign = TextAlign.Left,
        )
        Text(
            text = note.text,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(0.dp, 4.dp, 0.dp, 0.dp),
        )
    }
}

@Composable
fun showAddButton(
    onNewClick: () -> Unit){
    Button(
        onClick = {
            onNewClick()
        },
        border = BorderStroke(1.dp, Color.Transparent),
        shape = RoundedCornerShape(50),
        modifier = Modifier.padding(
            bottom = 6.dp,
            end = 12.dp)
    )
    { Text(
        text = stringResource(R.string.add_note),
        fontSize = 14.sp
    ) }
}

@Composable
fun showSendButton(context: Context){
    Button(
        onClick = {
            val notesViewModel = NoteViewModel(context.applicationContext as Application)
            val notes = notesViewModel.getAllDataForEmail()

            val emailText = StringBuilder()
            for(note in notes) {
                emailText.appendLine(note.date)
                emailText.appendLine(note.text)
            }

            val i = Intent(Intent.ACTION_SEND)

            val emailAddress = arrayOf("")
            i.putExtra(Intent.EXTRA_EMAIL,emailAddress)
            i.putExtra(Intent.EXTRA_SUBJECT,"Text from Short Notes application")
            i.putExtra(Intent.EXTRA_TEXT, emailText.toString())

            i.type = "message/rfc822"

            context.startActivity(Intent.createChooser(i,"Choose an Email client : "))
        },
        border = BorderStroke(1.dp, Color.Transparent),
        shape = RoundedCornerShape(50),
        modifier = Modifier.padding(
            bottom = 6.dp,
            end = 12.dp)
    )
    { Text(
        text = stringResource(R.string.send_email),
        fontSize = 14.sp
    ) }
}

@Composable
fun showCreateNoteButton(
    onShowListClick: () -> Unit,
    text: String,
    noteViewModel: NoteViewModel){
    Button(
        onClick = {
            val sdf = SimpleDateFormat("dd.MM.yyyy")
            val currentDate = sdf.format(Date())
            noteViewModel.save(text, currentDate)
            onShowListClick()
        },
        border = BorderStroke(1.dp, Color.Transparent),
        shape = RoundedCornerShape(50),
        modifier = Modifier.padding(
            bottom = 6.dp,
            end = 12.dp)
    )
    { Text(
        text = stringResource(R.string.create),
        fontSize = 14.sp
    ) }
}

@Composable
fun showCancelButton(
    onShowListClick: () -> Unit){
    Button(
        onClick = {
            onShowListClick()
        },
        border = BorderStroke(1.dp, Color.Transparent),
        shape = RoundedCornerShape(50),
        modifier = Modifier.padding(
            bottom = 6.dp,
            end = 12.dp)
    )
    { Text(
        text = stringResource(R.string.cancel),
        fontSize = 14.sp
    ) }
}
