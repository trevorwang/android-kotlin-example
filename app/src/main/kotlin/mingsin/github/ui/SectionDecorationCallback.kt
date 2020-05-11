package mingsin.github.ui

interface SectionDecorationCallback {
    fun groupId(position: Int): Int
    fun groupFirstLine(position: Int): String

}