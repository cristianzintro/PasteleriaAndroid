@Composable
fun CatalogoScreen(nav: NavController) {
    Scaffold(topBar = { TopAppBar(title = { Text("Catálogo") }) }) { p ->
        Text("Aquí listaremos los productos 🍰", Modifier.padding(p).padding(16.dp))
    }
}
