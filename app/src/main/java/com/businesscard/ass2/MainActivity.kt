package com.businesscard.ass2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi

import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity

import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.LocalWindowInfo

import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.LocalWindowInfo

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.businesscard.ass2.ui.theme.Ass2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Ass2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtworkApp()
                }
            }
        }
    }
}

@Composable
fun ArtworkApp() {
    // Replace with your actual resource IDs
    val artworkResources = listOf(
        R.drawable.art1,
        R.drawable.art2,
        R.drawable.art3
    )

    val artworks = listOf(
        ArtworkDetails("Street Art", "John Doe", "1999"),
        ArtworkDetails("Pastel Art", "Kanyam Sue", "1983"),
        ArtworkDetails("Fiction", "Azel Arohi", "2000")
    )

    var currentPage by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Use ArtworkSection with details from the current page
        ArtworkSection(
            page = currentPage,
            title = artworks[currentPage].title,
            artist = artworks[currentPage].artist,
            year = artworks[currentPage].year
        )

        Spacer(modifier = Modifier.height(16.dp))

        NavigationButtons(
            onPreviousClick = {
                if (currentPage > 0) {
                    currentPage--
                }
            },
            onNextClick = {
                if (currentPage < artworks.size - 1) {
                    currentPage++
                }
            }
        )
    }
}

data class ArtworkDetails(val title: String, val artist: String, val year: String)


@Composable
fun ArtworkSection(
    page: Int,
    title: String,
    artist: String,
    year: String
) {
    // Replace with your actual resource IDs
    val artworkResources = listOf(
        R.drawable.art1,
        R.drawable.art2,
        R.drawable.art3
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = artworkResources[page]),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .clip(shape = MaterialTheme.shapes.medium)
                .background(MaterialTheme.colorScheme.primary)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Display artwork title, artist name, and year dynamically
        Text(title, style = MaterialTheme.typography.headlineMedium)
        Text("$artist, $year", style = MaterialTheme.typography.headlineMedium)
    }
}

@Composable
fun NavigationButtons(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Use buttons instead of icons
        Button(onClick = onPreviousClick) {
            Text("Previous")
        }

        Button(onClick = onNextClick) {
            Text("Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewArtworkApp() {
    Ass2Theme {
        ArtworkApp()
    }
}