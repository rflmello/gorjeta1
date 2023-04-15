package com.example.gorjeta

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.gorjeta.ui.theme.GorjetaTheme
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GorjetaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .padding(30.dp),

            verticalArrangement = Arrangement.Top
        ) {
            Conteudo()
        }

    }
}

@Composable
fun Conteudo() {
    val valor = remember {
        mutableStateOf("")
    }

    val percentual = remember {
        mutableStateOf(18f)
    }
    var total15 = 0.0
    var valorCustomizado = 0.0
    var valor15 = 0.0
    var totalCustomizado = 0.0

    try {
        val valor_conta = valor.value
        val porcentagem_variavel = percentual.value
        var porcentagem_fixa: Double = 0.15

        valor15 = valor_conta.toFloat() * porcentagem_fixa
        valorCustomizado = valor_conta.toDouble() * porcentagem_variavel/100
        total15 = valor_conta.toFloat() + valor15
        totalCustomizado = valor_conta.toDouble() + valorCustomizado
    }
    catch (e : java.lang.Exception) {
        Log.i("Erro", e.message ?: "")
    }

    Text(
        text = stringResource(id = com.example.gorjeta.R.string.titulo),
        style = MaterialTheme.typography.h4,
        textAlign = TextAlign.Center,
        color = Color.Blue,
    )

    Text(
        text = stringResource(id = com.example.gorjeta.R.string.valor),
        style = MaterialTheme.typography.h6,
        textAlign = TextAlign.Left,
        color = Color.Blue,
    )

    OutlinedTextField(
        value = valor.value,
        onValueChange = {
            valor.value = it
        },
        modifier = Modifier.width(500.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )

    Text(
        text = "Selecione um valor ${percentual.value}%",
        style = MaterialTheme.typography.h6,
        textAlign = TextAlign.Left,
        color = Color.Blue,
    )

    Row() {
        SeekBarDemo(percentual.value, onChange = {
            percentual.value = it.toFloat()
        })

    }

    Row() {
        Text(
            text = "",
            textAlign = TextAlign.End,
            color = Color.Red,
            modifier = Modifier
                .weight(1f)
        )

        Text(
            text = "15%",
            textAlign = TextAlign.Center,
            color = Color.Green,
            modifier = Modifier
                .weight(1f)
        )

        Text(
            text = "${percentual.value}%",
            textAlign = TextAlign.Center,
            color = Color.Red,
            modifier = Modifier
                .weight(1f)
        )
    }

    Row() {
        Text(
            text = stringResource(id = com.example.gorjeta.R.string.tarifa),
            textAlign = TextAlign.End,
            color = Color.Blue,
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        )

        Text(
            text = "$valor15",
            modifier = Modifier
                .background(Color.Gray)
                .weight(1f)
                .padding(8.dp)
            ,
            textAlign = TextAlign.Center
        )

        Text(
            text = "$valorCustomizado",
            modifier = Modifier
                .background(Color.Gray)
                .weight(1f)
                .padding(8.dp),
            textAlign = TextAlign.Center
        )
    }

    Row() {
        Text(
            text = stringResource(id = com.example.gorjeta.R.string.total),
            textAlign = TextAlign.End,
            color = Color.Blue,
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        )

        Text(
            text = "$total15",
            modifier = Modifier
                .background(Color.LightGray)
                .weight(1f)
                .padding(8.dp),
            textAlign = TextAlign.Center
        )

        Text(
            text = "$totalCustomizado",
            modifier = Modifier
                .background(Color.LightGray)
                .weight(1f)
                .padding(8.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun SeekBarDemo(progress: Float, onChange: (value: Int) -> Unit) {
    Slider(
        value = progress,
        onValueChange = { value ->
            onChange(value.toInt())
        },
        modifier = Modifier.width(500.dp),
        valueRange = 0f..30f,
        steps = 1
    )
}

/*@Composable
fun Calculador(porcentagem_variavel: Float, valor_conta: Float) {
var valor_conta: Float
var porcentagem_fixa: Double = 0.15
var porcentagem_variavel: Float

/*val valor15 = valor_conta * porcentagem_fixa
val valorCustomizado = valor_conta * porcentagem_variavel
val total15 = valor_conta + valor15
val totalCustomizado = valor_conta + valorCustomizado
*/
}*/

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    GorjetaTheme {
        MyApp()
    }
}