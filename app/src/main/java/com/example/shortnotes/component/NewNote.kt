package com.example.shortnotes.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shortnotes.R

@Composable
fun showCreateNoteButton(
    onSaveClick: () -> Unit
){
    Button(
        onClick = {
            onSaveClick()
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