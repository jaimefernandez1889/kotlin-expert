data class Note(val title: String, val description: String, val type: Type){
    enum class Type {
        TEXT, AUDIO
    }
}

fun getNotes(quantity: Int = 10, callback: (List<Note>) -> Unit): Unit {
    Thread.sleep(1000)
    val notes: List<Note> = (1..quantity).map {
        Note(
            "Título $it",
            "Descripción $it",
            if(it % 3 == 0) Note.Type.AUDIO else Note.Type.TEXT
        )
    }

    callback(notes)
}
