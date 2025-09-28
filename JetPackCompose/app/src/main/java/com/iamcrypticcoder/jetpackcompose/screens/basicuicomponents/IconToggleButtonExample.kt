package com.iamcrypticcoder.jetpackcompose.screens.basicuicomponents

import android.annotation.SuppressLint
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
@Preview
fun IconToggleButtonExample() {
    // set the state of our checkbox.
    val checkedState = remember { mutableStateOf(false) }

    Column(
        Modifier.Companion.fillMaxHeight(),
        horizontalAlignment = Alignment.Companion.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = if (checkedState.value) "Like" else "Dislike",

            modifier = Modifier.Companion
                .fillMaxWidth()
                .padding(20.dp),
            textAlign = TextAlign.Companion.Center,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.Companion.height(8.dp))

        // create a toggle button with a custom icon
        IconToggleButton(
            // set default check state
            checked = checkedState.value,
            // set on check change
            onCheckedChange = {
                checkedState.value = !checkedState.value
            },
            modifier = Modifier.Companion.padding(10.dp)
        ) {
            // initialize transition
            val transition = updateTransition(checkedState.value)

            // set color of icon
            val tint by transition.animateColor(label = "iconColor") { isChecked ->
                if (isChecked) Color.Companion.Red else Color.Companion.Black
            }

            // define transition
            val size by transition.animateDp(
                transitionSpec = {
                    // on below line we are specifying transition
                    if (false isTransitioningTo true) {
                        // on below line we are specifying key frames
                        keyframes {
                            // on below line we are specifying animation duration
                            durationMillis = 250
                            // on below line we are specifying animations.
                            30.dp at 0 using LinearOutSlowInEasing // for 0-15 ms
                            35.dp at 15 using FastOutLinearInEasing // for 15-75 ms
                            40.dp at 75 // ms
                            35.dp at 150 // ms
                        }
                    } else {
                        spring(stiffness = Spring.StiffnessVeryLow)
                    }
                },
                label = "Size"
            ) { 30.dp }

            // create icon for toggle button.
            Icon(
                imageVector = if (checkedState.value) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                contentDescription = "Icon",
                tint = tint,
                modifier = Modifier.size(size)
            )
        }
    }
}