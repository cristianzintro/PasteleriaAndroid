@Composable
fun DetalleProductoScreen(nav: NavController, id: String) {
    Scaffold(topBar = { TopAppBar(title = { Text("Detalle $id") }) }) { p ->
        Text("Detalle del producto $id", Modifier.padding(p).padding(16.dp))
    }
}
