import kotlin.math.sign

data class Note(val title: String, val description: String, val type: Type){
    enum class Type {
        TEXT, AUDIO
    }
}

fun getList(quantity: Int = 10) : MutableList<Note>
{
    val noteList = mutableListOf<Note>()

    for (i in 1..quantity){
        val type = when {
            i % 3 == 0 -> Note.Type.AUDIO
            else -> Note.Type.TEXT
        }
        noteList.add(Note("t $i", "desc $i", type))
    }

    return noteList
}

fun main()
{
    val noteList = getList(15)
    println(noteList)
}