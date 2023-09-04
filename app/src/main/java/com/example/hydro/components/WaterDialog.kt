import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WaterDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onFloatValueSelected: (Float) -> Unit
) {
    var floatValue by remember { mutableStateOf(0f) }
    val focusManager = LocalFocusManager.current

    if (showDialog) {
        Dialog(
            onDismissRequest = {
                focusManager.clearFocus()
                onDismiss()
            },
        ) {
            var keyboardHeight by remember { mutableStateOf(0.dp) }

            Column(
                modifier = Modifier
                    .padding(16.dp).background(Color.Blue)
            ) {
                Text(
                    text = "Informe um valor float:",
                    style = MaterialTheme.typography.labelLarge
                )

                OutlinedTextField(
                    value = floatValue.toString(),
                    onValueChange = {
                        floatValue = it.toFloatOrNull() ?: 0f
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    label = { Text("Valor float") },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = keyboardHeight)
                ) {
                    Button(
                        onClick = {
                            onFloatValueSelected(floatValue)
                            focusManager.clearFocus()
                            onDismiss()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        Text("Confirmar")
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Button(
                        onClick = {
                            focusManager.clearFocus()
                            onDismiss()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        Text("Cancelar")
                    }
                }
            }
        }
    }
}
