@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Language(
    onBackPressed: () -> Unit,
    updateLocaleLanguage: (String) -> Unit
) {
    var locale by rememberSaveable { mutableStateOf(getLanguageNumber()) }

    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.language),
                        style = MaterialTheme.typography.headlineLarge,
                    )
                },
                navigationIcon = {
                    BackButton { onBackPressed() }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            item {
                PreferenceSingleChoiceItem(
                    text = stringResource(R.string.follow_system),
                    selected = locale == SYSTEM_DEFAULT
                ) { changeLanguage(SYSTEM_DEFAULT) }
            }
            items(languages) { language ->
                PreferenceSingleChoiceItem(
                    text = getLanguageDesc(language.key),
                    selected = locale == language.key
                ) {
                    changeLanguage(language.key)
                }
            }
        }
    }
}

private fun changeLanguage(index: Int) {
    locale = index
    Prefs[LANGUAGE] = locale
    updateLocaleLanguage(getLanguageConfig())
}
