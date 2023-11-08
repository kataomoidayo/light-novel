package com.putu.lightnovel.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.putu.lightnovel.R
import com.putu.lightnovel.ui.theme.LightNovelTheme

@Composable
fun AboutScreen() {
    Column(modifier = Modifier) {
        TopAppBar(backgroundColor = MaterialTheme.colors.primary) {
            Text(
                text = stringResource(R.string.menu_about),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h6.copy(
                    color = MaterialTheme.colors.onSecondary
                )
            )
        }
        Column( modifier = Modifier
            .verticalScroll(rememberScrollState())
            .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ){
                Image(
                    painter = painterResource(R.drawable.boku),
                    contentDescription = stringResource(R.string.name),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(200.dp)
                        .clip(CircleShape)
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.name),
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = stringResource(R.string.email),
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
fun DetailLightNovelPreview() {
    LightNovelTheme {
        AboutScreen()
    }
}