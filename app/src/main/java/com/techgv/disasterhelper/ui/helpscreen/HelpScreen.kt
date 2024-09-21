@file:OptIn(ExperimentalMaterial3Api::class)

package com.techgv.disasterhelper.ui.helpscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.techgv.disasterhelper.R
import com.techgv.disasterhelper.sampledata.helpLineList
import com.techgv.disasterhelper.ui.components.HorizontalSpacer

@Composable
fun HelpScreenRoute(
    onBackClick: () -> Unit
) {
    HelpScreen(onBackClick)
}

@Composable
fun HelpScreen(onBackClick: () -> Unit) {
    Scaffold(modifier = Modifier.fillMaxWidth(), topBar = {
        TopAppBar(navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.go_back)
                )
            }
        }, title = {
            Text(
                text = "HelpLine Details"
            )
        })
    }, content = { paddingValues: PaddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            items(items = helpLineList) {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Text(it.name)
                        HorizontalSpacer(10)
                        Text("Number: " + it.number)
                    }
                }
            }
        }
    })
}