package com.aben.debounceclickdemo

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.aben.click
import com.aben.debounceclickdemo.ui.theme.DebounceClickDemoTheme
import com.aben.onDebounceClick

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)
//        setContent {
//            DebounceClickDemoTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
//            }
//        }

        val button: View = findViewById<View>(R.id.button)
        button.click {
            Toast.makeText(
                this, "button 我被点击了",
                Toast.LENGTH_SHORT
            ).show()
        }

        val button2: View = findViewById<View>(R.id.button2)
        button2.setOnClickListener() {
            button2Clcik()
        }

        val button3: View = findViewById<View>(R.id.button3)
        button3.onDebounceClick {
            Toast.makeText(
                this@MainActivity,
                "button3 我被点击了",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    //    @DebounceClick
    fun button2Clcik() {
        Toast.makeText(this, "button2 我被点击了", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DebounceClickDemoTheme {
        Greeting("Android")
    }
}