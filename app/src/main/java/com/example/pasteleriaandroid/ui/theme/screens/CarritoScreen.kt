@Composable
fun CarritoScreen(nav: NavController) {
    Scaffold(topBar = { TopAppBar(title = { Text("Carrito") }) }) { p ->
        Text("Tu carrito está vacío… por ahora 😉", Modifier.padding(p).padding(16.dp))
    }
}
