package com.iamcrypticcoder.jetpackcompose.screens.customlazycolumn

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.iamcrypticcoder.jetpackcompose.R

private const val TAG = "CustomLazyColumnScreen"

@Composable
@Preview(showBackground = true)
fun CustomLazyColumnScreen(navController: NavHostController = rememberNavController()) {
    CustomLazyColumnContent()
}

@Composable
fun CustomLazyColumnContent() {
    val context = LocalContext.current
    val list = ArrayList<Model>()

    list.add(Model("Android", R.drawable.gfg_logo))
    list.add(Model("JavaScript", R.drawable.gfg_logo))
    list.add(Model("Python", R.drawable.gfg_logo))
    list.add(Model("C++", R.drawable.gfg_logo))
    list.add(Model("C#", R.drawable.gfg_logo))
    list.add(Model("Java", R.drawable.gfg_logo))
    list.add(Model("Node Js", R.drawable.gfg_logo))
    list.add(Model("Kotlin", R.drawable.gfg_logo))
    list.add(Model("Web Development", R.drawable.gfg_logo))
    list.add(Model("Machine Learning", R.drawable.gfg_logo))
    list.add(Model("Rust", R.drawable.gfg_logo))
    list.add(Model("Go", R.drawable.gfg_logo))
    list.add(Model("PHP", R.drawable.gfg_logo))
    list.add(Model("Flutter", R.drawable.gfg_logo))

    LazyColumn {
        itemsIndexed(list) { index, _ ->
            Card(
                onClick = {
                    Toast.makeText(context, list[index].name + " selected.. ", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.padding(start = 24.dp, top = 24.dp, end = 24.dp),
                elevation = CardDefaults.cardElevation(12.dp),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onPrimary)
            ) {
                Row(modifier = Modifier.padding(8.dp).fillMaxWidth()) {
                    Spacer(modifier = Modifier.width(5.dp))
                    Image(
                        painter = painterResource(id = list[index].image),
                        contentDescription = null,
                        modifier = Modifier
                            .height(80.dp)
                            .width(80.dp)
                            .padding(16.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = list[index].name,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .align(Alignment.CenterVertically),
                        color = Color.Black, textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}