package com.tc.fuzzbear

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import com.tc.fuzzbear.ui.theme.FuzzbearTheme
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp




val lcolors = mapOf<String,Color>(
    "nav" to Color(red = 239, green = 239, blue = 242),
    "foot" to Color(red = 236, green = 38, blue = 42),
    "body" to Color(red = 239, green = 239, blue = 242),
    "tna" to Color(red = 198, green = 80, blue = 80),
    "tfoo" to Color(red = 200, green = 248, blue = 236),
    "text" to Color(red = 198, green = 80, blue = 80)
)
val dcolors = mapOf<String,Color>(
    "nav" to Color(red = 166, green = 166, blue = 166),
    "body" to Color(red = 61, green = 61, blue = 61),
    "foot" to Color(red = 40, green = 46, blue = 84),
    "tna" to Color(red = 247, green = 241, blue = 227),
    "tfoo" to Color(red = 248, green = 236, blue = 200),
    "text" to Color(red = 209, green = 204, blue = 192)
)




val fonts = FontFamily(Font(R.font.kalnia, weight = FontWeight.Normal))


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var colors by remember {
                mutableStateOf(lcolors)
            }
            FuzzbearTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Column (
                        modifier = Modifier
                    ){
                        Banner(colors){
                            colors=it
                        }

                        Body(colors)

                    }
                }
            }
        }
    }
}


@Composable
fun Banner(colors: Map<String, Color>, modifier : Modifier = Modifier,
        Up: (Map<String, Color>) -> Unit

) {
    var checked by remember { mutableStateOf(false) }
    Up(
        if (checked) dcolors else lcolors
    )

        Row (
            modifier = Modifier
                .fillMaxWidth()
//                .background(Color.Yellow),
                .background(colors["nav"]!!),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
            )
        {
            Button(
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors["nav"]!!),
                onClick = {},
                modifier = Modifier
                    .padding(0.dp)
                    .wrapContentSize()
            ){
                Image(
                    painter = if (checked) painterResource(R.drawable.dmenu) else painterResource(R.drawable.lmenu),
                    contentDescription = "burger",
                    modifier = Modifier
                        .height(45.dp)
                        .width(45.dp)
//                    .fillMaxSize()
                )
            }
            Image(painter = painterResource(R.drawable.trendt), contentDescription = "logo",
//                modifier = Modifier
////                    .height(50.dp)
////                    .width(50.dp)
//                    .fillMaxSize()
            )
            Text(text = "Me Text",
                style = TextStyle(
                    color = colors["tna"]!!,
                    fontSize = 24.sp,
                    fontFamily = fonts,
//                    background = colors["nav"]!!,
                )
            )
            Column (
                modifier=Modifier.padding(horizontal = 2.dp)
            ) {
                Switch(
                    checked = checked,
                    onCheckedChange = {
                        checked = it

                    },
                    thumbContent = {
                        Image(
                            painter = if (checked) painterResource(R.drawable.moon) else painterResource(R.drawable.sun),
                            contentDescription = null,
                            )
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = colors["nav"]!!,
                        uncheckedThumbColor = colors["nav"]!!,
                        checkedTrackColor = colors["tna"]!!,
                        uncheckedTrackColor = colors["body"]!!,
//                        uncheckedIconColor = coloror = Color.Red.copy(alpha = ContentAlpha.disabled),
            )

                    )
            }

        }

}





@Composable
fun Body(colors: Map<String, Color>, modifier: Modifier = Modifier){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(colors["body"]!!),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    )
    {
        Button(onClick = {},
            shape = RectangleShape,
            modifier = Modifier
                .fillMaxWidth()
            ){
            Text(text = "Summerize")
        }
        Button(onClick = {}){
            Text(text = "Extract emotions")
        }
       Button(onClick = {}){
                Text(text = "Summerize")
            }
        }
}

