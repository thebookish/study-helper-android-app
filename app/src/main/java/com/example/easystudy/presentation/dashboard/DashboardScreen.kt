package com.example.easystudy.presentation.dashboard
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.easystudy.R
import com.example.easystudy.domain.model.Subject
import com.example.easystudy.presentation.component.CountCard
import com.example.easystudy.presentation.component.SubjectCard

@Composable
fun DashboardScreen() {

    val subjects = listOf(
        Subject(name = "Math", goalHours = 15f, colors = Subject.subjectCardColors[0]  ),
        Subject(name = "Cryptography", goalHours = 15f, colors = Subject.subjectCardColors[1]  ),
        Subject(name = "Biology", goalHours = 15f, colors = Subject.subjectCardColors[2]  ),
        Subject(name = "Networking", goalHours = 15f, colors = Subject.subjectCardColors[3]  )
    )

Scaffold(topBar = { DashboardTopBar() }) {
paddingValues ->  LazyColumn (
    modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)
){
item {
    CountCardSection(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        subjectCount = 5,
        studiedHours = "10",
        goalHours = "15"
    )

     SubjectCardSection(modifier = Modifier.fillMaxWidth(), subjectList = subjects)


}
}
}
}

@kotlin.OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
private fun DashboardTopBar(){
    CenterAlignedTopAppBar(title = {
        Text(text = "StudyHelper",
            style = MaterialTheme.typography.headlineMedium
            )
    })
}

@Composable
private fun CountCardSection(
    modifier: Modifier,
    subjectCount: Int,
    studiedHours: String,
    goalHours: String
){
    Row(modifier=modifier) {
        CountCard(
            modifier = Modifier.weight(1f),
          headingText = "Subject Count",
            count = "${subjectCount}"
        )
        Spacer(modifier = Modifier.width(10.dp))
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Studied Hours",
            count = "${studiedHours}"
        )
        Spacer(modifier = Modifier.width(10.dp))
        CountCard(
            modifier = Modifier.weight(1f),
            headingText = "Goal Study Hours",
            count = "${goalHours}"
        )
    }
}

@Composable
private fun SubjectCardSection(
    modifier: Modifier,
    subjectList: List<Subject>,
    emptyListText: String = "You don't have any subjects.\nClick the + button to add new subjects."
){
    Column(modifier = modifier) {
        Row (modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween){
            Text(text = "SUBJECTS",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 12.dp)
                )
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Subject")
            }
        }
   if(subjectList.isEmpty()){
       Image(modifier = Modifier
           .size(120.dp)
           .align(Alignment.CenterHorizontally),painter = painterResource(R.drawable.img_books), contentDescription =emptyListText )

       Text(
           modifier = Modifier.fillMaxWidth(),
           text = emptyListText,
           style = MaterialTheme.typography.bodySmall,
           color = Color.Gray,
           textAlign = TextAlign.Center
       )
   }
        LazyRow(
           horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(start = 12.dp, end = 12.dp)
        ) {
            items(subjectList) { subject ->
            SubjectCard(
                subjectName = subject.name,
                gradientColors = subject.colors,
                onClick = {}
            )
        }
    }
    }
}