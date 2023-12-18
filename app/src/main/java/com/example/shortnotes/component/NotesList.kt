package com.example.shortnotes.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shortnotes.dto.Note
import com.example.shortnotes.R

@Composable
fun showDataList(
    notes: List<Note>,
    onRemove: (id: Long) -> Unit,
    padding: PaddingValues) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
    ){
        items(items = notes, itemContent = { note ->
            showNote(note = note, onRemove)
        })
    }
}

@Composable
fun showNote(
    note: Note,
    onRemove: (id: Long) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 0.dp, 10.dp, 0.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(all = 0.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = note.date,
                modifier = Modifier
                    .padding(0.dp, 12.dp, 0.dp, 0.dp),
                textAlign = TextAlign.Left,
            )
        }
        Column(
            modifier = Modifier
                .padding(all = 0.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.End
        ) {
            IconButton(onClick = {
                onRemove(note.id)
            }) {
                Icon(
                    Icons.Filled.Delete,
                    contentDescription = stringResource(R.string.remove_note),
                    tint = Color.Gray,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 0.dp, 10.dp, 20.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(all = 0.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = note.text,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun showAddButton(
    onNewClick: () -> Unit){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.End
    ) {
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
}