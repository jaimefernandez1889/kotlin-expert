data class Note(val title: String, val description: String, val type: Type){
    enum class Type {
        TEXT, AUDIO, VIDEO
    }
}

val noteList = listOf(
    Note("t1", "d1", Note.Type.TEXT),
    Note("t2", "d2", Note.Type.AUDIO),
    Note("t3", "d3", Note.Type.TEXT),
    Note("t4", "d4", Note.Type.TEXT),
    Note("t5", "d5", Note.Type.AUDIO),
    Note("t6", "d6", Note.Type.TEXT),
    Note("t7", "d7", Note.Type.TEXT),
    Note("t8", "d8", Note.Type.AUDIO),
    Note("t9", "d9", Note.Type.TEXT),
    Note("t10", "d10", Note.Type.TEXT),
)