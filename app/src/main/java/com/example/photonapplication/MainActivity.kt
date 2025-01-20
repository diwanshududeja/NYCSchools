package com.example.photonapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.photonapplication.data.SchoolScreenState
import com.example.photonapplication.data.vo.SchoolData
import com.example.photonapplication.ui.theme.dp_16
import com.example.photonapplication.ui.theme.dp_8
import com.example.photonapplication.ui.theme.font_size_17
import com.example.photonapplication.ui.ui.SchoolsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

     val viewModel: SchoolsViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreenContent()
        }
        init()
    }

    @Composable
    private fun MainScreenContent() {
        val state = viewModel.state.collectAsState().value
        when(state) {
            is SchoolScreenState.Loading -> ShowLoader()
            is SchoolScreenState.Success -> SchoolsListUI(onItemClick = this::onSchoolItemClick, data = state.data)
            is SchoolScreenState.Error -> ShowErrorContent(state.message)
        }


    }

    @Composable
    private fun SchoolsListUI(onItemClick: () -> Unit, data: List<SchoolData>) {
        Surface(modifier = Modifier.fillMaxSize(), color = colorResource(R.color.white)) {
            Column(modifier = Modifier.fillMaxHeight().padding(dp_8)) {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(dp_8)) {
                    items(data.size) {
                        SchoolListItem(data = data[it], onItemClick = onItemClick)
                    }
                }
            }
        }
    }

    @Composable
    private fun SchoolListItem(data: SchoolData, onItemClick: () -> Unit) {
        Card {
            Column(modifier = Modifier.fillMaxWidth().padding(dp_16), horizontalAlignment = Alignment.Start) {
                Text(text = data.school_name.toString(), color = Color.Black, fontSize = font_size_17)
                Spacer(modifier = Modifier.height(dp_8))
                Button(onClick = { onItemClick() }) {
                    Text(text = stringResource(R.string.text_show_details))
                }
            }
        }
    }

    private fun onSchoolItemClick() {
        // start new activity and pass the required data to get complete school detail
        Toast.makeText(this, getString(R.string.complete_school_details), Toast.LENGTH_SHORT).show()
    }

    @Composable
    private fun ShowLoader() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }


    @Composable
    private fun ShowErrorContent(message: String) {
        Column {
            Text(text = message)
        }
    }

    private fun init() {
        // check for network connection first
        if(true){
            viewModel.getSchoolsList()
        } else {
            Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_SHORT).show()
            finish()
        }

    }
}


