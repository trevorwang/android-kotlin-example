package mingsin.github.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class KeyValue(
        @PrimaryKey val id: Int = 0,
        val key: String,
        val value: String
)
