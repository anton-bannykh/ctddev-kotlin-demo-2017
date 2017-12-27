package extensions

fun <T> MutableList<T>.popBack() = this.removeAt(this.lastIndex)