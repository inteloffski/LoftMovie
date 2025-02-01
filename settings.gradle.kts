rootProject.name = "LoftMovie"

val modules = listOf(
    ":asset",
    ":detail",
    ":splash",
    ":search",
    ":favorite",
    ":popular",
    ":main",
    ":core",
    ":app",
)

modules.forEach { module -> include(module) }