package com.iamcrypticcoder.jetpackcompose.screens.animations

import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iamcrypticcoder.jetpackcompose.R
import com.iamcrypticcoder.jetpackcompose.Routes
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data object FadeInFadeOut : Routes("FadeInFadeOut")
data object BackgroundColorTransition : Routes("BackgroundColorTransition")
data object BounceAnimation : Routes("BounceAnimation")
data object CircleAnimation : Routes("CircleAnimation")
data object RotationAnimation : Routes("RotationAnimation")
data object ShimmerAnimation : Routes("ShimmerAnimation")

val animationPages = listOf(
    FadeInFadeOut,
    BackgroundColorTransition,
    BounceAnimation,
    CircleAnimation,
    RotationAnimation,
    ShimmerAnimation)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun AnimationsScreen(navController: NavHostController = rememberNavController()) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val nestedNavController = rememberNavController()

    ModalNavigationDrawer(
        drawerContent = {
            DrawerContent(navController, nestedNavController, drawerState)
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Animations and Transitions") },
                    colors = TopAppBarDefaults.topAppBarColors(Color.White),
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            },
            content = { paddingValues ->
                NavHostContainer(navController, nestedNavController, paddingValues)
            }
        )
    }
}

@Composable
fun NavHostContainer(
    navController : NavHostController,
    nestedNavController : NavHostController,
    padding: PaddingValues
) {
    NavHost(
        navController = nestedNavController,
        startDestination = "FadeInFadeOut",
        modifier = Modifier.padding(paddingValues = padding),
        builder = {
            composable(FadeInFadeOut.route) { TextFadeAnimation() }
            composable(BackgroundColorTransition.route) { BackgroundColorTransitionAnimation() }
            composable(BounceAnimation.route) { BounceAnimation() }
            composable(CircleAnimation.route) { CircleAnimation() }
            composable(RotationAnimation.route) { RotationAnimation() }
            composable(ShimmerAnimation.route) { ShimmerAnimation() }
        }
    )
}

@Composable
fun DrawerContent(
    navController: NavHostController,
    nestedNavController: NavHostController,
    drawerState: DrawerState
) {
    ModalDrawerSheet {
        Spacer(modifier = Modifier.height(16.dp))

        val scope = rememberCoroutineScope()
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            animationPages.forEach { screen ->
                Text(
                    text = screen.route,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            nestedNavController.navigate(screen.route)
                            scope.launch { drawerState.close() }
                        }
                        .padding(12.dp)
                )
            }
        }
    }
}


@Composable
fun TextFadeAnimation() {
    var visibility by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            while (true) {

                // Toggle visibility
                visibility = !visibility

                // Delay to control the toggle frequency
                delay(2000)

                // Adjust delay as needed
            }
        }
    }

    Box(
        contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
    ) {
        AnimatedVisibility(
            visible = visibility,
            enter = fadeIn(animationSpec = tween(durationMillis = 1100)),
            exit = fadeOut(animationSpec = tween(durationMillis = 1100))
        ) {

            // Message text
            Text(text = "Hello, GeeksForGeeks!", fontSize = 24.sp)
        }
    }
}

@Composable
fun BackgroundColorTransitionAnimation() {
    // Animatable is a value holder that can animate the value as it is changed via animateTo
    val color = remember { Animatable(Color.Red) }
    var isAnimating by remember { mutableStateOf(false) }

    // LaunchedEffect composable to animate when start button is clicked
    LaunchedEffect(isAnimating) {
        while (isAnimating) {
            // each animation has a duration of 100 ms
            color.animateTo(Color.Red, animationSpec = tween(100))
            color.animateTo(Color.Green, animationSpec = tween(100))
            color.animateTo(Color.Blue, animationSpec = tween(100))
            color.animateTo(Color.Yellow, animationSpec = tween(100))
            color.animateTo(Color.Magenta, animationSpec = tween(100))
            color.animateTo(Color.Cyan, animationSpec = tween(100))
        }
    }

    // update the background color
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color.value),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Button to start/stop animation
        Button(
            onClick = { isAnimating = !isAnimating },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(Color.White)
        ) {
            Text(if (isAnimating) "Stop" else "Start", color = Color.Black)
        }
    }
}

enum class BounceState { Pressed, Released }
@Composable
fun BounceAnimation() {
    var currentState: BounceState by remember { mutableStateOf(BounceState.Released) }
    val transition = updateTransition(targetState = currentState, label = "animation")

    val scale: Float by transition.animateFloat(
        transitionSpec = { spring(stiffness = 900f) }, label = ""
    ) { state ->
        if (state == BounceState.Pressed) {
            0.8f
        } else {
            1f
        }
    }

    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .pointerInput(Unit) {
                    detectTapGestures(onPress = {
                        currentState = BounceState.Pressed
                        tryAwaitRelease()
                        currentState = BounceState.Released
                    })
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.gfg_logo),
                contentDescription = "gfg",
                modifier = Modifier
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                    }
                    .size(200.dp)
            )
        }
    }
}

@Composable
fun CircleAnimation() {
    val radius = 200f

    val animateFloat = remember { Animatable(0f) }
    LaunchedEffect(animateFloat) {
        animateFloat.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 3000,
                easing = LinearEasing
            )
        )
    }

    Row {
        Canvas(
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
        ) {
            drawArc(
                color = Color.Black,
                startAngle = 0f,
                sweepAngle = 360f * animateFloat.value,
                useCenter = true,
                size = Size(radius * 2, radius * 2),
                style = Stroke(2.0f)
            )
        }
    }

    Spacer(modifier = Modifier.height(100.dp))

    Row {
        Canvas(
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
        ) {
            drawArc(
                color = Color.Black,
                startAngle = 0f,
                sweepAngle = 360f * animateFloat.value,
                useCenter = false,
                size = Size(radius * 2, radius * 2),
                style = Stroke(2.0f)
            )
        }
    }
}

@Composable
fun RotationAnimation() {
    val infiniteTransition = rememberInfiniteTransition()

    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween<Float>(
                durationMillis = 3000,
                easing = FastOutLinearInEasing,
            ),
        )
    )

    Canvas(modifier = Modifier.size(250.dp)) {
        rotate(rotation) {
            drawRect(color = Color(50, 205, 50))
        }
    }
}

val ShimmerColorShades = listOf(
    Color.LightGray.copy(0.9f),
    Color.LightGray.copy(0.2f),
    Color.LightGray.copy(0.9f)
)
@Composable
fun ShimmerAnimation() {
    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1200, easing = FastOutSlowInEasing),
            RepeatMode.Reverse
        )
    )

    val brush = Brush.linearGradient(
        colors = ShimmerColorShades,
        start = Offset(10f, 10f),
        end = Offset(translateAnim, translateAnim)
    )
    ShimmerItem(brush = brush)
}

@Composable
fun ShimmerItem(
    brush: Brush
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(250.dp)
                .background(brush = brush)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .padding(vertical = 8.dp)
                .background(brush = brush)
        )
    }
}


