import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import com.cemore.claudeclient.TextFieldValueSaver
import com.cemore.claudeclient.ui.theme.ClaudeClientTheme
import com.cemore.claudeclient.ui.theme.Composer
import dagger.hilt.android.AndroidEntryPoint

class ChatFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ClaudeClientTheme {
                    Box(modifier = Modifier
                        .background(color = Color.Cyan)
                        .fillMaxSize()
                    ) {
                        val textFieldValue =
                            rememberSaveable(stateSaver = TextFieldValueSaver) { mutableStateOf(
                                TextFieldValue()
                            ) }

                        Box(modifier = Modifier.align(Alignment.BottomCenter),) {
                            Composer(
                                textFieldValue = textFieldValue.value,
                                onTextChanged = { entry ->
                                    textFieldValue.value = entry
                                }
                            )
                        }
                    }
                }
            }
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
        ClaudeClientTheme {
            Greeting("Android")
        }
    }
}
