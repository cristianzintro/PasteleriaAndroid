@Composable
fun RegistroScreen(nav: NavController) {
    Scaffold(topBar = { TopAppBar(title = { Text("Registro de cliente") }) }) { p ->
        Text("Formulario de registro (con validaciones)", Modifier.padding(p).padding(16.dp))
    }
}
